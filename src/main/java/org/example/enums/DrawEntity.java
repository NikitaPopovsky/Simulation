package org.example.enums;

public enum DrawEntity {
    HERBIVORE ("\uD83D\uDC07"),
    PREDATOR ("\uD83E\uDD8A"),
    GRASS ("\uD83C\uDF31"),
    ROCK ("\uD83D\uDDFB"),
    TREE ("\uD83C\uDFDD\uFE0F"),
    EMPTY ("\u2B1B");

    private final String value;

    DrawEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
