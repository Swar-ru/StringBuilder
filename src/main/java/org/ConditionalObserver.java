package org;
/**
 * Наблюдатель для проверки определенных условий
 */
public class ConditionalObserver implements ChangeObserver {
    private String targetWord;

    public ConditionalObserver(String targetWord) {
        this.targetWord = targetWord;
    }

    @Override
    public void onChange(String oldValue, String newValue) {
        if (newValue.contains(targetWord)) {
            System.out.println("!!! УСЛОВИЕ ВЫПОЛНЕНО !!!");
            System.out.println("Строка теперь содержит слово: '" + targetWord + "'");
            System.out.println("Полная строка: '" + newValue + "'");
            System.out.println();
        }
    }
}
