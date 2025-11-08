package org.example.dto;

import org.example.TypeEntity;
import org.example.models.Entity;

public class Cordinate {
    private final Entity entity;
    private int x;
    private int y;

    public Cordinate(TypeEntity type, int widthMap, int heightMap, int numPosition) {
        this.entity = Entity.createEntity(type);
        this.x = calculateX(widthMap, numPosition);
        this.y = calculateY(widthMap, numPosition);
    }

    private int calculateY(int widthMap, int numPosition) {
        return numPosition % widthMap;
    }

    private int calculateX(int widthMap, int numPosition) {
        return numPosition / widthMap;
    }
}
