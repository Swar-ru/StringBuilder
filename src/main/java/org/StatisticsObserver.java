package org;

import java.util.*;
import java.util.Map.Entry;

public class StatisticsObserver implements ChangeObserver {
    private final Map<StringBuilderChangeEvent.ChangeType, Integer> operationCount = new HashMap<>();
    private final long startTime = System.currentTimeMillis();
    private int totalChanges = 0;

    @Override
    public void onChange(StringBuilderChangeEvent event) {
        StringBuilderChangeEvent.ChangeType type = event.getChangeType();
        operationCount.merge(type, 1, Integer::sum);
        totalChanges++;
    }

    public void printStatistics() {
        System.out.println("=== Статистика операций ===");
        System.out.println("Всего изменений: " + totalChanges);
        System.out.println("Время работы: " + (System.currentTimeMillis() - startTime) + " мс");

        System.out.println("\nОперации по типам:");

        // Используем Entry.comparingByValue с обратным порядком
        operationCount.entrySet().stream()
                .sorted(Entry.<StringBuilderChangeEvent.ChangeType, Integer>comparingByValue().reversed())
                .forEach(entry ->
                        System.out.printf("  %s: %d раз(а)\n", entry.getKey(), entry.getValue())
                );
    }
}