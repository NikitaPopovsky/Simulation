package org.example.models;

import org.example.dto.Coordinate;
import org.example.enums.Constants;

import java.util.*;

//Игровая карта с координатами объектов
public class GameMap {
    private static GameMap instance;
    private Set<Coordinate> coordinates;
    private final int width;
    private final int height;

    public static GameMap getInstance() {
        if (instance == null) {
            instance = new GameMap();
        }
        return instance;
    }

    public GameMap() {
        this.width = Constants.WIDTH_MAP.getValue();
        this.height = Constants.HEIGHT_MAP.getValue();
        this.coordinates = new HashSet<>();
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

    public int getHeight() {
        return height;
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

    //Чистим статусы занятости
    public void clearBusyStatus() {
        for (Coordinate coordinate: coordinates) {
            coordinate.setBusy(false);
        }
    }
}


