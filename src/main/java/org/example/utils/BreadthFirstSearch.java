package org.example.utils;

import org.example.dto.Coordinate;
import org.example.models.GameMap;

import java.util.*;
import java.util.stream.Collectors;

public class BreadthFirstSearch {
    //private final Set<Integer> visited = new HashSet<>();
    private final Map<Integer, Integer> visitedWithParent = new HashMap<>();
    private final Queue<Integer> queuePosition = new LinkedList<>();
    private final int startPosition;
    private int endPosition;
    private final Set<Integer> targetPositions;

    public BreadthFirstSearch(int startPosition, Set<Integer> targetPositions) {
        this.startPosition = startPosition;
        this.targetPositions = targetPositions;

        visitedWithParent.put(startPosition, 0);
        findNeighbors(startPosition);
    }

    private void findNeighbors(int position) {
        int width = GameMap.getInstance().getWidth();
        List<Integer> neighbors =  getNeighborsPosition(position);
        checkValidationPosition(neighbors);
        addPositionToQueue(neighbors);
    }

    private void addPositionToQueue(List<Integer> neighbors) {
        queuePosition.addAll(neighbors);
    }

    private void checkValidationPosition(List<Integer> neighbors) {
        for (int i = 0; i < neighbors.size(); i ++) {
            int position = neighbors.get(i);
            if (position <= 0 || visitedWithParent.containsKey(position)){
                neighbors.remove(i);
            }
        }
    }

    private List<Integer> getNeighborsPosition(int curPosition) {
        int width = GameMap.getInstance().getWidth();
        List <Integer> neighbors = new ArrayList<>();
        neighbors.add(curPosition++);
        neighbors.add(curPosition--);
        neighbors.add(curPosition + width);
        neighbors.add(curPosition - width);

        return neighbors;
    }

    public void addStaticEntityToVisited(List<Coordinate> staticEntity) {
        //visitedWithParent.putAll();
        visitedWithParent.putAll(staticEntity.stream()
                .collect(Collectors.toMap(Coordinate::getPosition, element-> 0)));
    }

    public void searchEndPosition(){
        while (!queuePosition.isEmpty()) {
            int nextPosition = queuePosition.poll();

            if (checkEndPosition (nextPosition)) {
                endPosition = nextPosition;
                break;
            }

            findNeighbors(nextPosition);
        }
    }

    private boolean checkEndPosition(int nextPosition) {
        return targetPositions.contains(nextPosition);
    }


    //Ищем самого верхнего родителя
    public Deque<Integer> positionsForNextStep() {
        Deque<Integer> steps = new LinkedList<>();
        int currentPosition = endPosition;
        int parentCurrentPosition = visitedWithParent.get(currentPosition);
        while (parentCurrentPosition != startPosition) {
            steps.add(currentPosition);
            currentPosition = parentCurrentPosition;
            parentCurrentPosition = visitedWithParent.get(currentPosition);
        }
        return steps;
    }
}
