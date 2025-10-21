package org;

import java.util.EventObject;

class StringBuilderChangeEvent extends EventObject {
    private final String oldValue;
    private final String newValue;
    private final ChangeType changeType;
    private final long timestamp;
    private final Object changeContext;

    public enum ChangeType {
        APPEND, INSERT, DELETE, REPLACE, REVERSE, SET_LENGTH, SET_CHAR, UNDO
    }

    public StringBuilderChangeEvent(Object source, String oldValue, String newValue,
                                    ChangeType changeType, Object changeContext) {
        super(source);
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changeType = changeType;
        this.timestamp = System.currentTimeMillis();
        this.changeContext = changeContext;
    }

    // Геттеры
    public String getOldValue() { return oldValue; }
    public String getNewValue() { return newValue; }
    public ChangeType getChangeType() { return changeType; }
    public long getTimestamp() { return timestamp; }
    public Object getChangeContext() { return changeContext; }

    @Override
    public String toString() {
        return String.format("ChangeEvent{type=%s, old='%s', new='%s', time=%tT}",
                changeType, oldValue, newValue, timestamp);
    }
}
