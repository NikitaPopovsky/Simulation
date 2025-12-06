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
    private boolean busy;

    public Coordinate(TypeEntity type, int widthMap, int numPosition) {
        this.entity = Entity.createEntity(type.getClassEntity());
        this.x = calculateX(widthMap, numPosition);
        this.y = calculateY(widthMap, numPosition);
        this.position = numPosition;
        this.busy = false;
    }

    public Coordinate(Class <?> entityClass, int widthMap, int numPosition) {
        this.entity =Entity.createEntity(entityClass);
        this.x = calculateX(widthMap, numPosition);
        this.y = calculateY(widthMap, numPosition);
        this.position = numPosition;
        this.busy = false;
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

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void setBusy() {
        this.busy = true;
    }
}
