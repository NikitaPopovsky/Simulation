package org.example.models.creatures;

public class Herbivore extends Creature {
    private int health;

    public Herbivore(int speed, int health) {
        super(speed);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void getDamage (int power) {
        int totalHealth = health - power;
        health = Math.max(0,totalHealth);
    }
}


