package org.example.models;

import org.example.DrawEntity;
import org.example.dto.Coordinate;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

import java.util.Arrays;
import java.util.Set;

public class Render {

    public static void printMap (GameMap gameMap) {
        String [][] gameMapString =  gameMapToString(gameMap);
        addDrawEntitie(gameMapString, gameMap.getCoordinates());

        for (String [] lineMap : gameMapString) {
            System.out.println(Arrays.deepToString(lineMap));
        }
    }

    private static void addDrawEntitie(String[][] gameMapString, Set<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            int x = coordinate.getX()-1;
            int y = coordinate.getY()-1;
            gameMapString[x][y] = getDrawEntity (coordinate.getEntity());
        }
    }

    private static String [][] gameMapToString (GameMap gameMap) {
        int width = gameMap.getWidth();
        int height = gameMap.getHeight();
        String[][] gameMapString = new String[width][height];

        return fillStartValues(gameMapString);
    }

    private static String[][] fillStartValues(String[][] gameMapString) {
        for (String[] strings : gameMapString) {
            Arrays.fill(strings, DrawEntity.EMPTY.getValue());
        }
        return gameMapString;
    }

    private static String getDrawEntity(Entity entity) {
        if (entity instanceof Herbivore) {
            return DrawEntity.HERBIVORE.getValue();
        } else if (entity instanceof Predator) {
            return DrawEntity.PREDATOR.getValue();
        } else if (entity instanceof Grass) {
            return DrawEntity.GRASS.getValue();
        } else if (entity instanceof Rock) {
            return  DrawEntity.ROCK.getValue();
        } else if (entity instanceof Tree) {
            return  DrawEntity.TREE.getValue();
        } else  {
            return  "";
        }
    }


}
