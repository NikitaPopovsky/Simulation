package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.enums.Constants;
import org.example.models.GameMap;
import org.example.models.creatures.Herbivore;
import org.example.models.textures.Grass;

import java.util.Set;
import java.util.stream.Collectors;

//Класс отвечает за создание новых сущностей
public class CreationEntity extends Action {
    private static CreationEntity instance;

    public static CreationEntity getInstance() {
        if (instance == null) {
            instance = new CreationEntity();
        }
        return instance;
    }

    @Override
    public void make() {
        Set<Coordinate> coordinates = getCoordinates();
        int MinCountEntity = Constants.COUNT_ENTITY.getValue();

        create(Grass.class, coordinates, MinCountEntity);
        create(Herbivore.class, coordinates, MinCountEntity);

        setCoordinates(coordinates);
    }

    //Создает определенное количество объектов , если их меньше минимального
    private void create(Class<?> entityClass, Set<Coordinate> coordinates, int minCountEntity) {
        int widthMap = GameMap.getInstance().getWidth();
        int countEntity = (int) coordinates.stream()
                .filter(coordinate->entityClass.isInstance(coordinate.getEntity()))
                .count();
        int count = minCountEntity - countEntity;

        if (count <= 0){
            return;
        }

        Set<Integer> existNums = coordinates.stream().map(Coordinate::getPosition).collect(Collectors.toSet());
        Set<Integer> generateNums = InitializationCreatures.generateNums(count, existNums);

        for (int num: generateNums) {
            Coordinate coordinate = new Coordinate(entityClass, widthMap, num);
            coordinates.add(coordinate);
        }

    }
}
