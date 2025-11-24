package org.example.dto;

import org.example.TypeEntity;
import org.example.models.Entity;
import org.example.models.creatures.Creature;

public class Coordinate {
    private final Entity entity;
    private int x;
    private int y;
    private int position;

    public Coordinate(TypeEntity type, int widthMap, int heightMap, int numPosition) {
        this.entity = Entity.createEntity(type);
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

    public Creature getCreature() {
        return (Creature) entity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
