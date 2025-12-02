package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.models.GameMap;
import org.example.utils.BreadthFirstSearch;

import java.util.Set;

//import static org.example.models.Entity.DistributeCoordinatesByType;

public class InteractToTarget extends Action {

    private static InteractToTarget instance;

    public static InteractToTarget getInstance() {
        if (instance == null) {
            instance = new InteractToTarget();
        }
        return instance;
    }

    @Override
    public void make() {
        makeStepCreature(instance);
    }

    public void interact(Coordinate coordinate, Set<Integer> targetPositions) {
        Set<Integer> neighbors = BreadthFirstSearch.getNeighborsPosition(coordinate.getPosition());
        int interactPosition = getInteractPosition(neighbors, targetPositions);
        if (interactPosition != 0){
            GameMap.getInstance().removeEntity(interactPosition);
        }
    }

    private int getInteractPosition(Set<Integer> neighbors, Set<Integer> targetPositions) {
        for (int neighbor: neighbors) {
            if (targetPositions.contains(neighbor)) {
                return neighbor;
            };
        }
        return 0;
    }

}

