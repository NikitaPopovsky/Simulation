package org.example.models;

import org.example.dto.Coordinate;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.models.Entity.DistributeCoordinatesByType;

public class Action {

    public void createActionsForStep (List<Coordinate> coordinates) {
        Map<String, List<Coordinate>> entityCoordinates = DistributeCoordinatesByType(coordinates);
        createActionHerbivore(entityCoordinates);
    }

    //Создаем действия для  Herbivore (травоядных)
    private void createActionHerbivore(Map<String, List<Coordinate>> entityCoordinates) {
        for (Coordinate coordinate : entityCoordinates.get("herbivore")) {
            findResource(coordinate, entityCoordinates);
        }
    }

    //Производится поиск ближайшего ресурса
    private void findResource(Coordinate coordinate, Map<String, List<Coordinate>> entityCoordinates) {
        Set<Integer> targetPositions = convertCoordinateToSet(entityCoordinates.get("grass"));
        int startPosition = coordinate.getPosition();
        BreadthFirstSearch bfs = new BreadthFirstSearch(startPosition, targetPositions);
        bfs.addStaticEntityToVisited(entityCoordinates.get("staticEntity"));
        //bfs.findNeighbors(startPosition);
        bfs.searchEndPosition();
        int positionForNextStep = bfs.positionForNextStep();
    }

    //Преобразовываем список координат в список позиций
    private Set<Integer> convertCoordinateToSet(List<Coordinate> coordinates) {
        return coordinates.stream().map(Coordinate::getPosition).collect(Collectors.toSet());
    }

    private class BreadthFirstSearch {
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

        private void checkValidationPosition( List<Integer> neighbors) {
            for (int i = 0; i < neighbors.size(); i ++) {
                int position = neighbors.get(i);
                if (position <= 0 || visitedWithParent.containsKey(position)){
                    neighbors.remove(position);
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
        public int positionForNextStep() {
            int currentPosition = endPosition;
            int parentCurrentPosition = visitedWithParent.get(currentPosition);
            while (parentCurrentPosition != startPosition) {
                currentPosition = parentCurrentPosition;
                parentCurrentPosition = visitedWithParent.get(currentPosition);
            }
            return currentPosition;
        }
    }


}
