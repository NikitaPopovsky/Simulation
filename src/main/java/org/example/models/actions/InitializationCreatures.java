package org.example.models.actions;

import org.example.EntityFactory;
import org.example.models.Coordinates;
import org.example.enums.Constants;
import org.example.enums.TypeEntity;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

import java.util.*;

public class InitializationCreatures extends Action {
    private static InitializationCreatures instance;

    public static InitializationCreatures getInstance() {
        if (instance == null) {
            instance = new InitializationCreatures();
        }
        return instance;
    }

    public InitializationCreatures() {
    }

    @Override
    public void make(GameMap gameMap) {
        int countEachEntity = Constants.COUNT_ENTITY.getValue();
        ActionUtil.spawn(Predator.class, gameMap, countEachEntity);
        ActionUtil.spawn(Herbivore.class, gameMap, countEachEntity);
        ActionUtil.spawn(Grass.class, gameMap, countEachEntity);
        ActionUtil.spawn(Tree.class, gameMap, countEachEntity);
        ActionUtil.spawn(Rock.class, gameMap, countEachEntity);
    }

}
