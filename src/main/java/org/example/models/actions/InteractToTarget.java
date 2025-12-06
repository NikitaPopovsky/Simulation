package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.models.GameMap;
import org.example.models.creatures.Creature;
import org.example.utils.BreadthFirstSearch;

import java.util.Set;
import java.util.stream.Collectors;

//Класс отвечает за взаимодействие с целями
//Пример : хищник атакует жертву / травоядное ест траву
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
        Set<Coordinate> coordinates = getCoordinates();
        Set<Coordinate> creatures = coordinates.stream()
                .filter(coordinate -> coordinate.getEntity() instanceof Creature).collect(Collectors.toSet());

        for (Coordinate coordinate : creatures) {
            Set<Integer> targetPositions = getTargets(coordinate.getEntity(), coordinates);

            interact(coordinate, targetPositions);
        }
        setCoordinates(coordinates);
    }

    //Получаем соседние клетки, и проверяем на наличие целей
    //Если находим - удаляем цель
    public void interact(Coordinate coordinate, Set<Integer> targetPositions) {
        Set<Integer> neighbors = BreadthFirstSearch.getNeighborsPosition(coordinate.getPosition());
        int interactPosition = getInteractPosition(neighbors, targetPositions);
        if (interactPosition != 0){
            coordinate.setBusy();
            GameMap.getInstance().removeEntity(interactPosition);
        }
    }

    //Проверяем соседние клетки
    private int getInteractPosition(Set<Integer> neighbors, Set<Integer> targetPositions) {
        for (int neighbor: neighbors) {
            if (targetPositions.contains(neighbor)) {
                return neighbor;
            }
        }
        return 0;
    }

}

