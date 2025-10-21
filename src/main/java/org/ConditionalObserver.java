package org;
/**
 * Наблюдатель для проверки определенных условий
 */
class ConditionalObserver implements ChangeObserver {
    private String targetWord;
    private int triggerCount;

    public ConditionalObserver(String targetWord) {
        this.targetWord = targetWord;
    }

    @Override
    public void onChange(StringBuilderChangeEvent event) {
        if (event.getNewValue().contains(targetWord)) {
            triggerCount++;
            System.out.println("!!! УСЛОВИЕ ВЫПОЛНЕНО (#" + triggerCount + ") !!!");
            System.out.println("Строка теперь содержит слово: '" + targetWord + "'");
            System.out.println("Полная строка: '" + event.getNewValue() + "'");
            System.out.println("Тип изменения: " + event.getChangeType());
            System.out.println();
        }
    }

    public int getTriggerCount() {
        return triggerCount;
    }
}

