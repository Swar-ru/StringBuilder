package org;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс StringBuilder с возможностью оповещения наблюдателей об изменениях
 */
public class ObservableStringBuilder {
    private StringBuilder stringBuilder;
    private List<ChangeObserver> observers;

    public ObservableStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.observers = new ArrayList<>();
    }

    public ObservableStringBuilder(String str) {
        this.stringBuilder = new StringBuilder(str);
        this.observers = new ArrayList<>();
    }

    public ObservableStringBuilder(int capacity) {
        this.stringBuilder = new StringBuilder(capacity);
        this.observers = new ArrayList<>();
    }

    /**
     * Добавляет наблюдателя
     */
    public void addObserver(ChangeObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Удаляет наблюдателя
     */
    public void removeObserver(ChangeObserver observer) {
        observers.remove(observer);
    }

    /**
     * Уведомляет всех наблюдателей об изменении
     */
    private void notifyObservers(String oldValue, String newValue) {
        for (ChangeObserver observer : observers) {
            observer.onChange(oldValue, newValue);
        }
    }

    // Делегированные методы StringBuilder с уведомлением наблюдателей

    public ObservableStringBuilder append(String str) {
        String oldValue = stringBuilder.toString();
        stringBuilder.append(str);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    public ObservableStringBuilder append(StringBuffer sb) {
        return append(sb.toString());
    }

    public ObservableStringBuilder append(CharSequence s) {
        return append(s.toString());
    }

    public ObservableStringBuilder append(CharSequence s, int start, int end) {
        String oldValue = stringBuilder.toString();
        stringBuilder.append(s, start, end);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder append(char[] str) {
        return append(new String(str));
    }

    public ObservableStringBuilder append(char[] str, int offset, int len) {
        String oldValue = stringBuilder.toString();
        stringBuilder.append(str, offset, len);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder append(boolean b) {
        return append(String.valueOf(b));
    }

    public ObservableStringBuilder append(char c) {
        String oldValue = stringBuilder.toString();
        stringBuilder.append(c);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder append(int i) {
        return append(String.valueOf(i));
    }

    public ObservableStringBuilder append(long lng) {
        return append(String.valueOf(lng));
    }

    public ObservableStringBuilder append(float f) {
        return append(String.valueOf(f));
    }

    public ObservableStringBuilder append(double d) {
        return append(String.valueOf(d));
    }

    public ObservableStringBuilder insert(int index, char[] str, int offset, int len) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(index, str, offset, len);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int offset, Object obj) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(offset, obj);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int offset, String str) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(offset, str);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int offset, char[] str) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(offset, str);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int dstOffset, CharSequence s) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(dstOffset, s);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(dstOffset, s, start, end);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int offset, boolean b) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(offset, b);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int offset, char c) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(offset, c);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int offset, int i) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(offset, i);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int offset, long l) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(offset, l);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int offset, float f) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(offset, f);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder insert(int offset, double d) {
        String oldValue = stringBuilder.toString();
        stringBuilder.insert(offset, d);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder delete(int start, int end) {
        String oldValue = stringBuilder.toString();
        stringBuilder.delete(start, end);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder deleteCharAt(int index) {
        String oldValue = stringBuilder.toString();
        stringBuilder.deleteCharAt(index);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder replace(int start, int end, String str) {
        String oldValue = stringBuilder.toString();
        stringBuilder.replace(start, end, str);
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public ObservableStringBuilder reverse() {
        String oldValue = stringBuilder.toString();
        stringBuilder.reverse();
        notifyObservers(oldValue, stringBuilder.toString());
        return this;
    }

    public void setLength(int newLength) {
        String oldValue = stringBuilder.toString();
        stringBuilder.setLength(newLength);
        notifyObservers(oldValue, stringBuilder.toString());
    }

    public void setCharAt(int index, char ch) {
        String oldValue = stringBuilder.toString();
        stringBuilder.setCharAt(index, ch);
        notifyObservers(oldValue, stringBuilder.toString());
    }

    // Методы без изменения состояния (не уведомляют наблюдателей)

    public int length() {
        return stringBuilder.length();
    }

    public int capacity() {
        return stringBuilder.capacity();
    }

    public void ensureCapacity(int minimumCapacity) {
        stringBuilder.ensureCapacity(minimumCapacity);
    }

    public void trimToSize() {
        stringBuilder.trimToSize();
    }

    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    public int codePointAt(int index) {
        return stringBuilder.codePointAt(index);
    }

    public int codePointBefore(int index) {
        return stringBuilder.codePointBefore(index);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return stringBuilder.codePointCount(beginIndex, endIndex);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return stringBuilder.offsetByCodePoints(index, codePointOffset);
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        stringBuilder.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    public int indexOf(String str) {
        return stringBuilder.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return stringBuilder.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return stringBuilder.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return stringBuilder.lastIndexOf(str, fromIndex);
    }

    public CharSequence subSequence(int start, int end) {
        return stringBuilder.subSequence(start, end);
    }

    public String substring(int start) {
        return stringBuilder.substring(start);
    }

    public String substring(int start, int end) {
        return stringBuilder.substring(start, end);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
