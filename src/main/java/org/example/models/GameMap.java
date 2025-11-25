package org.example.models;

import org.example.enums.TypeEntity;
import org.example.dto.Coordinate;

import java.util.*;

public class GameMap {
    private static GameMap instance;
    private Set<Coordinate> coordinates = new HashSet<>();
    private int width;
    private int height;
    private int countEachEntity;

    public static GameMap getInstance() {
        if (instance == null) {
            instance = new GameMap();
        }
        return instance;
    }

    public GameMap() {
    }

    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Set<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCountEachEntity() {
        return countEachEntity;
    }

    public void setCountEachEntity(int countEachEntity) {
        this.countEachEntity = countEachEntity;
    }



}


