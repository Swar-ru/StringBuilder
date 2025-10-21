package org;

/**
 * Интерфейс наблюдателя за изменениями ObservableStringBuilder
 */
public interface ChangeObserver {
    /**
     * Вызывается при изменении состояния ObservableStringBuilder
     * @param oldValue предыдущее значение строки
     * @param newValue новое значение строки
     */
    void onChange(String oldValue, String newValue);
}
