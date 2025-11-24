package org.example.models;

import org.example.dto.Coordinate;
import org.example.models.creatures.Creature;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.models.Entity.DistributeCoordinatesByType;

public class Action {
    public static void initActions() {
        GameMap.getInstance().generateCoordinates();
    }

    public void createActionsForStep (List<Coordinate> coordinates) {
        Map<String, List<Coordinate>> entityCoordinates = DistributeCoordinatesByType(coordinates);
        createActionHerbivore(entityCoordinates);
    }

    //Создаем действия для  Herbivore (травоядных)
    private void createActionHerbivore(Map<String, List<Coordinate>> entityCoordinates) {
        List <Coordinate> staticEntity = entityCoordinates.get("staticEntity");
        Set<Integer> targetPositionsForHerbivore = convertCoordinateToSet(entityCoordinates.get("grass"));
        Set<Integer> targetPositionsForPredator = convertCoordinateToSet(entityCoordinates.get("creature").stream()
                .filter(creature-> creature.getEntity() instanceof Herbivore).toList());

        for (Coordinate coordinate : entityCoordinates.get("creature")) {
            Creature creature = coordinate.getCreature();
            if (creature instanceof Herbivore) {
                creature.makeMove(coordinate, staticEntity, targetPositionsForHerbivore);
            } else if (creature instanceof Predator) {
                creature.makeMove(coordinate, staticEntity, targetPositionsForPredator);
            }

        }
    }

    //Производится поиск ближайшего ресурса
    public void findResource(Coordinate coordinate, List <Coordinate> staticEntity,
                              Set<Integer> targetPositions) {

        int startPosition = coordinate.getPosition();
        BreadthFirstSearch bfs = new BreadthFirstSearch(startPosition, targetPositions);
        bfs.addStaticEntityToVisited(staticEntity);
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
