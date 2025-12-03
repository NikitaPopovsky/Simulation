package org.example.models.actions;

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
        create();
    }

    private void create() {

    }
}
