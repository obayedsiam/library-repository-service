package com.example.library.enums;

public enum Language {
    BANGLA("BANGLE"),
    ENGLISH("ENGLISH"),
    ARABIC("ARABIC");

    private final String language;

    Language(String language) {
        this.language = language;
    }

    private String getLanguage() {
        return language;
    }
}
