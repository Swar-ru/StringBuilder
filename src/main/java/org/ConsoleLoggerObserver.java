package org;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Простой наблюдатель, который выводит изменения в консоль
 */
class ConsoleLoggerObserver implements ChangeObserver {
    private String name;
    private SimpleDateFormat timeFormat;

    public ConsoleLoggerObserver(String name) {
        this.name = name;
        this.timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");
    }

    @Override
    public void onChange(StringBuilderChangeEvent event) {
        System.out.printf("[%s][%s] %s:%n", timeFormat.format(new Date(event.getTimestamp())), name, event.getChangeType());
        System.out.printf("  Было: '%s' (длина: %d)%n", event.getOldValue(), event.getOldValue().length());
        System.out.printf("  Стало: '%s' (длина: %d)%n", event.getNewValue(), event.getNewValue().length());

        if (event.getChangeContext() != null) {
            System.out.printf("  Контекст: %s%n", event.getChangeContext());
        }
        System.out.println();
    }
}
