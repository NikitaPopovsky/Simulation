package org.example.models.actions;

import org.example.models.Action;

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

    }
}

