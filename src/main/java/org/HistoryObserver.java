package org;

import java.util.ArrayList;
import java.util.List;

/**
 * Наблюдатель, который сохраняет историю изменений
 */
public class HistoryObserver implements ChangeObserver {
    private java.util.List<String> history = new ArrayList<>();

    @Override
    public void onChange(String oldValue, String newValue) {
        history.add("Изменение: '" + oldValue + "' -> '" + newValue + "'");
    }

    public void printHistory() {
        System.out.println("=== История изменений ===");
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
        System.out.println();
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
}
