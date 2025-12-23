package org.example.models.actions;

import java.util.List;

public final class ActionFactory {
    public List<Action> createTurn() {
        return List.of(new CreationEntityAction()
                , new InteractToTargetAction()
                , new MovingToTargetAction()
                , new ClearBusyStatus());
    };

    public List<Action> createInit(){
        return List.of(new InitializationCreaturesAction());

    };
}
