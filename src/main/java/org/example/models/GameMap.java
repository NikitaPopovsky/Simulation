package org.example.models;

import org.example.dto.Coordinate;

import java.util.*;

//Игровая карта с координатами объектов
public class GameMap {
    private static GameMap instance;
    private Set<Coordinate> coordinates = new HashSet<>();
    private int width;
    private int height;

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

    //Удаление сущности с карты
    public void removeEntity(int position) {
        Coordinate removeCoordinate = null;
        for (Coordinate coordinate : coordinates) {
            if (coordinate.getPosition() == position) {
                removeCoordinate = coordinate;
            }
        }

        if (removeCoordinate != null) {
            coordinates.remove(removeCoordinate);
        }
    }
}


