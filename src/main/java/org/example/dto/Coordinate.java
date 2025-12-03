package org.example.dto;

import org.example.enums.TypeEntity;
import org.example.models.Entity;
import org.example.models.GameMap;

//ДТО хранящий позиции сущностей на карте
public class Coordinate {
    private final Entity entity;
    private int x;
    private int y;
    private int position;

    public Coordinate(TypeEntity type, int widthMap, int numPosition) {
        this.entity = Entity.createEntity(type);
        this.x = calculateX(widthMap, numPosition);
        this.y = calculateY(widthMap, numPosition);
        this.position = numPosition;
    }

    public Coordinate(Class <?> entityClass, int widthMap, int numPosition) {
        this.entity =Entity.createEntity(entityClass);
        this.x = calculateX(widthMap, numPosition);
        this.y = calculateY(widthMap, numPosition);
        this.position = numPosition;
    }

    private int calculateX(int widthMap, int numPosition) {
        if (numPosition % widthMap == 0) {
            return widthMap;
        } else {
            return numPosition % widthMap;
        }

    }

    private int calculateY(int widthMap, int numPosition) {
        if (numPosition % widthMap == 0) {
            return numPosition / widthMap;
        } else {
            return numPosition / widthMap + 1;
        }

    }

    public Entity getEntity() {
        return entity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        int widthMap = GameMap.getInstance().getWidth();
        this.position = position;
        this.x = calculateX(widthMap, position);
        this.y = calculateY(widthMap, position);
    }
}
