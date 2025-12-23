package org.example.utils;

import org.example.enums.Commands;
import org.example.enums.Constants;
import org.example.enums.DrawEntity;
import org.example.models.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class Render {

    public static void printMap (GameMap gameMap, int countStep) {
        String [][] gameMapString =  gameMapToString();
        printPreliminaryData(countStep);
        addDrawEntities(gameMapString, gameMap.getEntityByClass(Entity.class));
        for (String [] lineMap : gameMapString) {
            System.out.println(Arrays.deepToString(lineMap));
        }
        printCommands();
    }

    private static void printPreliminaryData(int countStep) {
        printSplitLine();
        System.out.println("Step: " + countStep);
        printSplitLine();

    }

    private static void printSplitLine() {
        System.out.println(("_").repeat(Constants.WIDTH_MAP.getValue()*5));
    }


    private static void addDrawEntities(String[][] gameMapString, Map<Coordinate, Entity> entities) {
        for (Map.Entry <Coordinate, Entity> entity : entities.entrySet()) {
            int x = entity.getKey().x()-1;
            int y = entity.getKey().y()-1;
            gameMapString[x][y] = getDrawEntity (entity.getValue());
        }
    }

    private static String [][] gameMapToString () {
        int width = Constants.WIDTH_MAP.getValue();
        int height = Constants.HEIGHT_MAP.getValue();
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
        return switch (entity) {
            case Herbivore _ -> DrawEntity.HERBIVORE.getValue();
            case Predator _ -> DrawEntity.PREDATOR.getValue();
            case Grass _ -> DrawEntity.GRASS.getValue();
            case Rock _ -> DrawEntity.ROCK.getValue();
            case Tree _ -> DrawEntity.TREE.getValue();
            case null, default -> "";
        };
    }



    public static void printStartMessage() {
        printSplitLine();
        System.out.println("Добро пожаловать в симуляцию!");
        printSplitLine();
        System.out.println("Размер поля 10х10. Количество каждого существа на карте - 5 шт.");
        System.out.println("Каждый ход добавляются новые существа и трава, если их меньше необходимого количества");
        System.out.println("Это сделано для баланса. Вы всегда можете самостоятельно поменять в константе данные параметры.");
        printSplitLine();
    }

    public static void printCommands() {
        System.out.printf("Доступные команды : Старт симуляции - %s, пауза - %s, выход - %s  \n",
                Commands.START.getValue(),
                Commands.PAUSE.getValue(),
                Commands.EXIT.getValue());
    }


    public static void printIncorrectCommand() {
        System.out.println("Неверная команда");
    }

    public static void printPause() {
        System.out.println("Симуляция приостановлена");
    }
}
