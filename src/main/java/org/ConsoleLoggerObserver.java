package org;

/**
 * Простой наблюдатель, который выводит изменения в консоль
 */
public class ConsoleLoggerObserver implements ChangeObserver {
    private String name;

    public ConsoleLoggerObserver(String name) {
        this.name = name;
    }

    @Override
    public void onChange(String oldValue, String newValue) {
        System.out.println("[" + name + "] Строка изменена:");
        System.out.println("  Было: '" + oldValue + "'");
        System.out.println("  Стало: '" + newValue + "'");
        System.out.println("  Длина: " + newValue.length());
        System.out.println();
    }
}
