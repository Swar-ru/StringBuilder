package org;

/**
 * Интерфейс наблюдателя за изменениями ObservableStringBuilder
 */
@FunctionalInterface
public interface ChangeObserver {
    /**
     * Вызывается при изменении состояния ObservableStringBuilder
     * @param event объект события с детальной информацией об изменении
     */
    void onChange(StringBuilderChangeEvent event);
}