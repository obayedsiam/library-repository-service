package com.example.library.enums;

public enum RecordStatus {
    ACTIVE("ACTIVE"),
    DELETED("DELETED");

    private final String level;

    RecordStatus(String level) {
        this.level = level;
    }

    private String getLevel() {
        return level;
    }
}
