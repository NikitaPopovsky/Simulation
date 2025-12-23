package org.example.utils;

import org.example.models.Coordinate;

import java.util.HashSet;
import java.util.Set;

public final class GameMapUtil {
    public static Set<Coordinate> getNeighbors(Coordinate current) {
        Set<Coordinate> neighbors = new HashSet<>();
        neighbors.add(new Coordinate(current.x() + 1, current.y()));
        neighbors.add(new Coordinate(current.x() - 1, current.y()));
        neighbors.add(new Coordinate(current.x(), current.y() + 1));
        neighbors.add(new Coordinate(current.x(), current.y() - 1));
        return neighbors;
    }
}
