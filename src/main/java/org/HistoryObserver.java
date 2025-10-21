package org;

import java.util.*;
import java.util.Comparator;

public class HistoryObserver implements ChangeObserver {
    private final List<StringBuilderChangeEvent> history;
    private final int maxSize;

    public HistoryObserver(int maxSize) {
        this.maxSize = maxSize;
        this.history = new ArrayList<>();
    }

    @Override
    public void onChange(StringBuilderChangeEvent event) {
        history.add(event);
        if (history.size() > maxSize) {
            history.remove(0);
        }
    }

    public List<StringBuilderChangeEvent> getHistory() {
        return new ArrayList<>(history);
    }

    public void printHistory() {
        System.out.println("=== История изменений (последние " + history.size() + ") ===");

        List<StringBuilderChangeEvent> sortedHistory = new ArrayList<>(history);
        sortedHistory.sort(Comparator.comparingLong(StringBuilderChangeEvent::getTimestamp));

        for (int i = 0; i < sortedHistory.size(); i++) {
            StringBuilderChangeEvent event = sortedHistory.get(i);
            System.out.printf("%2d. [%s] %s -> %s\n",
                    i + 1, event.getChangeType(),
                    truncate(event.getOldValue()),
                    truncate(event.getNewValue()));
        }
    }

    private String truncate(String value) {
        if (value == null) return "null";
        if (value.length() > 30) {
            return value.substring(0, 27) + "...";
        }
        return value;
    }
}