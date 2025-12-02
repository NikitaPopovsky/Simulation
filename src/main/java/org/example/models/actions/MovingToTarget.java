package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.utils.BreadthFirstSearch;

import java.util.*;

public class MovingToTarget extends Action {
    private static MovingToTarget instance;

    public static MovingToTarget getInstance() {
        if (instance == null) {
            instance = new MovingToTarget();
        }
        return instance;
    }

    @Override
    public void make() {
        makeStepCreature(instance);
    }

    //Получаем список следующих шагов
    public Deque<Integer> nextSteps(int startPosition, Set<Integer> allEntity,
                                    Set<Integer> targetPositions) {

        BreadthFirstSearch bfs = new BreadthFirstSearch(startPosition, targetPositions, allEntity);
        bfs.searchEndPosition();
        return bfs.positionsForNextStep();
    }


    public void move(Coordinate coordinate, Set<Integer> allEntity, Set<Integer> targetPositions) {
        if (!targetPositions.isEmpty()) {
            Deque<Integer> nextSteps = nextSteps(coordinate.getPosition(), allEntity, targetPositions);
            if (!nextSteps.isEmpty()) {
                coordinate.setPosition(nextSteps.pollLast());
            }
        }


    }
}

