package org.example;

//хищник - "🐅"
//травоядное - "🦌"
//камень - "⛰"
//трава - "🌿"
//дерево - "🌳"
//пусто - "_"
public enum DrawEntity {
    HERBIVORE ("\uD83E\uDD8C"),
    PREDATOR ("\uD83D\uDC05"),
    GRASS ("\uD83C\uDF3F"),
    ROCK ("⛰"),
    TREE ("\uD83C\uDF33"),
    EMPTY ("﹎");

    private final String value;

    DrawEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
