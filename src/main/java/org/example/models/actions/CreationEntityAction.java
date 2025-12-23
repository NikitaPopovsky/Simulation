package org.example.models.actions;

import org.example.enums.Constants;
import org.example.models.GameMap;
import org.example.models.creatures.Herbivore;
import org.example.models.textures.Grass;
import org.example.utils.ActionUtil;

public class CreationEntityAction implements Action {

    public CreationEntityAction() {
    }

    @Override
    public void make(GameMap gameMap) {
        int MinCountEntity = Constants.COUNT_ENTITY.getValue();

        int difference = MinCountEntity - gameMap.getCountEntityByClass(Grass.class);
        if (difference != 0) {
            ActionUtil.spawn(Grass.class, gameMap, difference);
        }

        difference = MinCountEntity - gameMap.getCountEntityByClass(Herbivore.class);
        if (difference != 0) {
            ActionUtil.spawn(Herbivore.class, gameMap, difference);
        }
    }

}
