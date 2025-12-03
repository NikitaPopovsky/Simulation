package org.example;

import org.example.enums.DrawEntity;
import org.example.dto.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

import java.util.Arrays;
import java.util.Set;

//Рендер, отрисовывающий объекты в консоль
public class Render {

    //Отрисовывает карту и сущности на ней
    public static void printMap (GameMap gameMap, int countStep) {
        String [][] gameMapString =  gameMapToString(gameMap);
        drawPreliminaryData(countStep, gameMap.getWidth());
        addDrawEntities(gameMapString, gameMap.getCoordinates());
        for (String [] lineMap : gameMapString) {
            System.out.println(Arrays.deepToString(lineMap));
        }
    }

    //Выводит разделитель
    private static void drawPreliminaryData(int countStep, int width) {
        String separatorLine = ("_").repeat(width*5);
        System.out.println(separatorLine);
        System.out.println("Step: " + countStep);
        System.out.println(separatorLine);

    }


    private static void addDrawEntities(String[][] gameMapString, Set<Coordinate> coordinates) {
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

    //Получаем рисунок для сущности
    private static String getDrawEntity(Entity entity) {
        return switch (entity) {
            case Herbivore herbivore -> DrawEntity.HERBIVORE.getValue();
            case Predator predator -> DrawEntity.PREDATOR.getValue();
            case Grass grass -> DrawEntity.GRASS.getValue();
            case Rock rock -> DrawEntity.ROCK.getValue();
            case Tree tree -> DrawEntity.TREE.getValue();
            case null, default -> "";
        };
    }


}
