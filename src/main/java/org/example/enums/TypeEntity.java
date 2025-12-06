package org.example.enums;

import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

public enum TypeEntity {
    HERBIVORE(Herbivore.class),
    PREDATOR(Predator.class),
    GRASS(Grass.class),
    ROCK(Rock.class),
    TREE(Tree.class);

    private final Class<?> classEntity;

    TypeEntity(Class<?> classEntity) {
        this.classEntity = classEntity;
    }

    public Class<?> getClassEntity() {
        return classEntity;
    }
}
