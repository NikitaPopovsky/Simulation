package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.models.creatures.Creature;
import org.example.utils.BreadthFirstSearch;

import java.util.*;
import java.util.stream.Collectors;

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
        Set<Coordinate> coordinates = getCoordinates();
        Set<Coordinate> creatures = coordinates.stream()
                .filter(coordinate -> coordinate.getEntity() instanceof Creature).collect(Collectors.toSet());

        for (Coordinate coordinate : creatures) {
            Set<Integer> allEntity = coordinates.stream().map(Coordinate::getPosition).collect(Collectors.toSet());
            Set<Integer> targetPositions = getTargets(coordinate.getEntity(), coordinates);

            move(coordinate, allEntity, targetPositions);
        }
        setCoordinates(coordinates);
    }



    //Получаем список следующих шагов
    public Deque<Integer> nextSteps(int startPosition, Set<Integer> allEntity,
                                    Set<Integer> targetPositions) {

        BreadthFirstSearch bfs = new BreadthFirstSearch(startPosition, targetPositions, allEntity);
        bfs.searchEndPosition();
        return bfs.positionsForNextStep();
    }

    //делаем шаги к цели
    public void move(Coordinate coordinate, Set<Integer> allEntity, Set<Integer> targetPositions) {
        if (!targetPositions.isEmpty()) {
            Deque<Integer> nextSteps = nextSteps(coordinate.getPosition(), allEntity, targetPositions);
            if (!nextSteps.isEmpty()) {
                coordinate.setPosition(nextSteps.pollLast());
            }
        }


    }
}

