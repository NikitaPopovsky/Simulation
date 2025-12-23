package org.example.enums;

public enum Constants {
    WIDTH_MAP(10),
    HEIGHT_MAP(10),
    COUNT_ENTITY(5),
    HEALTH(2),
    SPEED(2),
    POWER(1);

    private final int value;

    Constants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
