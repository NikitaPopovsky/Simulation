package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.models.Action;
import org.example.models.GameMap;
import org.example.models.creatures.Creature;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.utils.BreadthFirstSearch;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.models.Entity.DistributeCoordinatesByType;

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
        Set<Coordinate> coordinates = GameMap.getInstance().getCoordinates();
        Map<String, List<Coordinate>> entityCoordinates = DistributeCoordinatesByType(coordinates);

        List <Coordinate> staticEntity = entityCoordinates.get("staticEntity");
        Set<Integer> targetPositionsForHerbivore = convertCoordinateToSet(entityCoordinates.get("grass"));
        Set<Integer> targetPositionsForPredator = convertCoordinateToSet(entityCoordinates.get("creature").stream()
                .filter(creature-> creature.getEntity() instanceof Herbivore).toList());

        for (Coordinate coordinate : entityCoordinates.get("creature")) {
            Creature creature = coordinate.getCreature();
            Set<Integer> targetPositions = null;
            if (creature instanceof Herbivore) {
                targetPositions = targetPositionsForHerbivore;
            } else if (creature instanceof Predator) {
                targetPositions = targetPositionsForPredator;
            }

            Deque<Integer> nextSteps = nextSteps(coordinate.getPosition(), staticEntity, targetPositions);
            if (!nextSteps.isEmpty()) {
                coordinate.setPosition(nextSteps.pollLast());
            }

        }
    }

    //Получаем список следующих шагов
    public Deque<Integer> nextSteps(int startPosition, List <Coordinate> staticEntity,
                             Set<Integer> targetPositions) {

        BreadthFirstSearch bfs = new BreadthFirstSearch(startPosition, targetPositions, staticEntity);
        //bfs.addStaticEntityToVisited(staticEntity);
        bfs.searchEndPosition();
        return bfs.positionsForNextStep();
    }

    //Преобразовываем список координат в список позиций
    private Set<Integer> convertCoordinateToSet(List<Coordinate> coordinates) {
        return coordinates.stream().map(Coordinate::getPosition).collect(Collectors.toSet());
    }

}

