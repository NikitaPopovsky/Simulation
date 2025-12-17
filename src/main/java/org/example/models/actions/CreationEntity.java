package org.example.models.actions;

import org.example.models.Coordinates;
import org.example.enums.Constants;
import org.example.models.GameMap;
import org.example.models.creatures.Herbivore;
import org.example.models.textures.Grass;

import java.util.Set;
import java.util.stream.Collectors;

public class CreationEntity extends Action {
    private static CreationEntity instance;

    public static CreationEntity getInstance() {
        if (instance == null) {
            instance = new CreationEntity();
        }
        return instance;
    }

    @Override
    public void make(GameMap gameMap) {
        int MinCountEntity = Constants.COUNT_ENTITY.getValue();

        int difference = gameMap.getCountEntityByClass(Grass.class) - MinCountEntity;
        if (difference != 0) {
            ActionUtil.spawn(Grass.class, gameMap, difference);
        }

        difference = gameMap.getCountEntityByClass(Herbivore.class) - MinCountEntity;
        if (difference != 0) {
            ActionUtil.spawn(Herbivore.class, gameMap, difference);
        }

    }

}
