package org;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация улучшенного ObservableStringBuilder ===\n");

        ObservableStringBuilder sb = new ObservableStringBuilder("Начало", true, 10);

        // Создаем наблюдателей
        ConsoleLoggerObserver consoleLogger = new ConsoleLoggerObserver("MainLogger");
        HistoryObserver historyTracker = new HistoryObserver(20);
        ConditionalObserver wordChecker = new ConditionalObserver("Java");
        StatisticsObserver statsCollector = new StatisticsObserver();

        // Именованный наблюдатель
        sb.addObserver("console", consoleLogger);

        // Цепочка наблюдателей
        ChangeObserver chainedObserver = event -> {
            historyTracker.onChange(event);
            wordChecker.onChange(event);
            statsCollector.onChange(event);
        };
        sb.addObserver("chain", chainedObserver);

        // Одноразовый наблюдатель
        sb.addOneTimeObserver(event ->
                System.out.println(">>> Первое изменение выполнено! <<<\n")
        );

        // Лямбда-наблюдатель
        sb.addObserver("lengthWatcher", event -> {
            if (event.getNewValue().length() > 20) {
                System.out.println("⚠️  Длина строки превысила 20 символов!\n");
            }
        });

        // Демонстрируем различные операции
        System.out.println("1. Добавление строк:");
        sb.append(" Hello");
        sb.append(" World");

        System.out.println("2. Вставка в середину:");
        sb.insert(6, " Красивый");

        System.out.println("3. Замена части строки:");
        sb.replace(0, 5, "Привет");

        System.out.println("4. Добавление разных типов данных:");
        sb.append(". Число: ");
        sb.append(42);
        sb.append(". Булево: ");
        sb.append(true);
        sb.append(". Символ: ");
        sb.append('!');

        System.out.println("5. Удаление части строки:");
        sb.delete(7, 16);

        System.out.println("6. Проверка условия (добавление 'Java'):");
        sb.append(" Изучаем Java программирование");

        System.out.println("7. Работа с массивом символов:");
        char[] chars = {' ', 'c', 'h', 'a', 'r', 's'};
        sb.append(new String(chars));

        System.out.println("8. Реверс строки:");
        sb.reverse();

        // Демонстрация отмены
        System.out.println("9. Демонстрация отмены операций:");
        System.out.println("До отмены: " + sb);
        sb.undo();
        System.out.println("После одной отмены: " + sb);
        sb.undo();
        System.out.println("После двух отмен: " + sb);

        // Выводим финальный результат
        System.out.println("\nФинальная строка: " + sb);
        System.out.println("Длина: " + sb.length());
        System.out.println("Размер истории: " + sb.getHistorySize());
        System.out.println("Активных наблюдателей: " + sb.getObserverCount());

        // Показываем собранную статистику
        System.out.println();
        historyTracker.printHistory();
        statsCollector.printStatistics();

        // Демонстрация удаления наблюдателя
        System.out.println("10. Демонстрация удаления наблюдателя:");
        sb.removeObserver("console");
        sb.append(" (логирование отключено)");
        System.out.println("Изменение выполнено, но ConsoleLogger не получил уведомление");

        // Финальная статистика
        System.out.println("\n=== Финальная статистика ===");
        System.out.println("Всего событий в истории: " + historyTracker.getHistory().size());
        System.out.println("Срабатываний ConditionalObserver: " + wordChecker.getTriggerCount());
    }
}