package org.example.enums;

//Данный класс содержит в себе переменные для баланса симуляции
public enum Constants {
    //Количество каждого существа на карте - 5% от количества клеток
    WIDTH_MAP(10),
    HEIGHT_MAP(10),
    COUNT_ENTITY(setCountEntity());


    private final int value;

    Constants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private static int setCountEntity() {
        int count = WIDTH_MAP.value * HEIGHT_MAP.value / 20;
        return Math.max(count, 1);
    }




}
