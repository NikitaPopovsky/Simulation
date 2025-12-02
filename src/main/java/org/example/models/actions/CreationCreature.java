package org.example.models.actions;

public class CreationCreature extends Action {
    private static CreationCreature instance;

    public static CreationCreature getInstance() {
        if (instance == null) {
            instance = new CreationCreature();
        }
        return instance;
    }

    @Override
    public void make() {

    }
}
