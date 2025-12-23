package org.example.models.actions;

import org.example.models.GameMap;

public class ClearBusyStatus implements Action{

    @Override
    public void make(GameMap gameMap) {
        gameMap.clearBusyStatus();
    }
}
