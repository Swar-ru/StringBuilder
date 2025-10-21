package org;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class ObservableStringBuilder {
    private final StringBuilder delegate;
    private final Map<String, ChangeObserver> observers = new ConcurrentHashMap<>();
    private final Lock lock;
    private Deque<String> history;
    private final int maxHistorySize;

    public ObservableStringBuilder(String initial, boolean threadSafe, int historySize) {
        this.delegate = new StringBuilder(initial);
        this.lock = threadSafe ? new ReentrantLock() : new NoOpLock();
        this.maxHistorySize = historySize;

        if (historySize > 0) {
            this.history = new ArrayDeque<>();
            saveToHistory(initial);
        }
    }

    public void addObserver(String name, ChangeObserver observer) {
        if (name == null || observer == null) return;
        observers.put(name, observer);
    }

    public void addOneTimeObserver(ChangeObserver observer) {
        if (observer != null) {
            String tempName = "onetime_" + System.currentTimeMillis();
            addObserver(tempName, event -> {
                try {
                    observer.onChange(event);
                } finally {
                    removeObserver(tempName);
                }
            });
        }
    }

    public void removeObserver(String name) {
        observers.remove(name);
    }

    public int getObserverCount() {
        return observers.size();
    }

    public int getHistorySize() {
        return history != null ? history.size() : 0;
    }

    public void append(String str) {
        if (str == null) return;

        lock.lock();
        try {
            String oldValue = delegate.toString();
            delegate.append(str);
            handleChange(oldValue, StringBuilderChangeEvent.ChangeType.APPEND, str);
        } finally {
            lock.unlock();
        }
    }

    public void append(Object obj) {
        append(String.valueOf(obj));
    }

    public void append(int value) {
        append(String.valueOf(value));
    }

    public void append(char c) {
        lock.lock();
        try {
            String oldValue = delegate.toString();
            delegate.append(c);
            handleChange(oldValue, StringBuilderChangeEvent.ChangeType.APPEND, c);
        } finally {
            lock.unlock();
        }
    }

    public void delete(int start, int end) {
        lock.lock();
        try {
            String oldValue = delegate.toString();
            String deleted = oldValue.substring(start, end);
            delegate.delete(start, end);

            Map<String, Object> context = new HashMap<>();
            context.put("start", start);
            context.put("end", end);
            context.put("deleted", deleted);

            handleChange(oldValue, StringBuilderChangeEvent.ChangeType.DELETE, context);
        } finally {
            lock.unlock();
        }
    }

    public void replace(int start, int end, String replacement) {
        lock.lock();
        try {
            String oldValue = delegate.toString();
            String original = delegate.substring(start, end);
            delegate.replace(start, end, replacement);

            Map<String, Object> context = new HashMap<>();
            context.put("start", start);
            context.put("end", end);
            context.put("original", original);
            context.put("replacement", replacement);

            handleChange(oldValue, StringBuilderChangeEvent.ChangeType.REPLACE, context);
        } finally {
            lock.unlock();
        }
    }

    public void undo() {
        if (history == null || history.size() <= 1) return;

        lock.lock();
        try {
            String current = delegate.toString();
            history.pop();
            String previous = history.peek();

            if (!current.equals(previous)) {
                delegate.setLength(0);
                delegate.append(previous);
                notifyObservers(current, previous, StringBuilderChangeEvent.ChangeType.UNDO, null);
            }
        } finally {
            lock.unlock();
        }
    }

    public void insert(int index, String str) {
        lock.lock();
        try {
            String oldValue = delegate.toString();
            delegate.insert(index, str);

            Map<String, Object> context = new HashMap<>();
            context.put("index", index);
            context.put("value", str);

            handleChange(oldValue, StringBuilderChangeEvent.ChangeType.INSERT, context);
        } finally {
            lock.unlock();
        }
    }

    public void reverse() {
        lock.lock();
        try {
            String oldValue = delegate.toString();
            delegate.reverse();
            handleChange(oldValue, StringBuilderChangeEvent.ChangeType.REVERSE, null);
        } finally {
            lock.unlock();
        }
    }

    private void handleChange(String oldValue, StringBuilderChangeEvent.ChangeType type, Object context) {
        String newValue = delegate.toString();

        if (oldValue.equals(newValue)) return;

        if (history != null) {
            saveToHistory(newValue);
        }

        notifyObservers(oldValue, newValue, type, context);
    }

    private void saveToHistory(String state) {
        history.push(state);
        while (history.size() > maxHistorySize) {
            history.removeLast();
        }
    }

    private void notifyObservers(String oldValue, String newValue,
                                 StringBuilderChangeEvent.ChangeType changeType,
                                 Object changeContext) {
        if (observers.isEmpty()) return;

        StringBuilderChangeEvent event = new StringBuilderChangeEvent(
                this, oldValue, newValue, changeType, changeContext);

        for (ChangeObserver observer : observers.values()) {
            try {
                observer.onChange(event);
            } catch (Exception e) {
                System.err.println("Observer error: " + e.getMessage());
            }
        }
    }

    public int length() {
        return delegate.length();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    private static class NoOpLock implements Lock {
        @Override public void lock() {}
        @Override public void lockInterruptibly() {}
        @Override public boolean tryLock() { return true; }
        @Override public boolean tryLock(long time, TimeUnit unit) { return true; }
        @Override public void unlock() {}
        @Override public Condition newCondition() {
            throw new UnsupportedOperationException("No conditions for no-op lock");
        }
    }
}