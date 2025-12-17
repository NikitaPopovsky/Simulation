package org.example.models;

import java.util.HashSet;
import java.util.Set;

public final class GameMapUtil {
    public static Set<Coordinates> getNeighbors(Coordinates current) {
        Set<Coordinates> neighbors = new HashSet<>();
        neighbors.add(new Coordinates(current.x() + 1, current.y()));
        neighbors.add(new Coordinates(current.x() - 1, current.y()));
        neighbors.add(new Coordinates(current.x(), current.y() + 1));
        neighbors.add(new Coordinates(current.x(), current.y() - 1));
        return neighbors;
    }
}
