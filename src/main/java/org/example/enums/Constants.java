package org.example.enums;

import org.example.models.GameMap;

//Данный класс содержит в себе переменные для баланса симуляции
public enum Constants {
    //Количество каждого существа на карте - 5% от количества клеток
    countEntity (setCountEntity());

    private final int value;

    Constants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private static int setCountEntity() {
        GameMap gameMap = GameMap.getInstance();
        int count = gameMap.getHeight() * gameMap.getWidth() / 20;
        return Math.max(count, 1);
    }


}
