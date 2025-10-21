package org;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация ObservableStringBuilder ===\n");

        // Создаем наблюдаемый StringBuilder
        ObservableStringBuilder observableSB = new ObservableStringBuilder();

        // Создаем и регистрируем наблюдателей
        ConsoleLoggerObserver consoleLogger = new ConsoleLoggerObserver("ConsoleLogger");
        HistoryObserver historyTracker = new HistoryObserver();
        ConditionalObserver wordChecker = new ConditionalObserver("Java");

        observableSB.addObserver(consoleLogger);
        observableSB.addObserver(historyTracker);
        observableSB.addObserver(wordChecker);

        // Демонстрируем различные операции
        System.out.println("1. Добавление строк:");
        observableSB.append("Hello");
        observableSB.append(" ");
        observableSB.append("World");

        System.out.println("2. Вставка в середину:");
        observableSB.insert(5, " Beautiful");

        System.out.println("3. Замена части строки:");
        observableSB.replace(0, 5, "Hi");

        System.out.println("4. Добавление чисел и других типов:");
        observableSB.append(". Number: ").append(42);
        observableSB.append(". Boolean: ").append(true);

        System.out.println("5. Удаление части строки:");
        observableSB.delete(2, 13);

        System.out.println("6. Проверка условия (добавление 'Java'):");
        observableSB.append(" Learning Java programming");

        System.out.println("7. Реверс строки:");
        observableSB.reverse();

        // Выводим финальный результат
        System.out.println("Финальная строка: " + observableSB.toString());
        System.out.println();

        // Показываем историю изменений
        historyTracker.printHistory();

        // Демонстрация удаления наблюдателя
        System.out.println("8. Демонстрация удаления наблюдателя:");
        observableSB.removeObserver(consoleLogger);
        observableSB.append(" (без логов в консоли)");
        System.out.println("Изменение выполнено, но ConsoleLogger не получил уведомление");

        // Показываем финальную историю
        System.out.println("\nФинальная история изменений:");
        historyTracker.printHistory();
    }
}
