package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Action {

    //Выполняем действие
    public abstract void make ();

    //Добавляем первоначальные действия
    public static void addInitActions(Queue<Action> Actions) {
        Actions.add(InitializationCreatures.getInstance());
    }

    //Добавляем действия, повторяющиеся каждый ход
    public static void addTurnActions (Queue<Action> Actions) {
        Actions.add(InteractToTarget.getInstance());
        Actions.add(MovingToTarget.getInstance());
        Actions.add(CreationEntity.getInstance());
    }

    //Получаем текущие координаты
    protected Set<Coordinate> getCoordinates() {
        GameMap gameMap = GameMap.getInstance();
        return gameMap.getCoordinates();
    }

    //Устанавливаем новые координаты после действия
    protected void setCoordinates(Set<Coordinate> coordinates) {
        GameMap gameMap = GameMap.getInstance();
        gameMap.setCoordinates(coordinates);
    }

    //Получаем цели для действия
    //Пример: для травоядного - трава; для хищника - травоядное
    protected Set<Integer> getTargets(Entity entity, Set<Coordinate> coordinates) {
        if (entity instanceof Herbivore) {
            return coordinates.stream()
                    .filter(coordinate -> coordinate.getEntity() instanceof Grass)
                    .map(Coordinate::getPosition)
                    .collect(Collectors.toSet());
        } else if (entity instanceof Predator) {
            return coordinates.stream()
                    .filter(coordinate -> coordinate.getEntity() instanceof Herbivore)
                    .map(Coordinate::getPosition)
                    .collect(Collectors.toSet());
        } else {
            return Set.of();
        }
    }

}
