package org.example.utils;

import org.example.enums.Constants;
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
        String [][] gameMapString =  gameMapToString();
        drawPreliminaryData(countStep);
        addDrawEntities(gameMapString, gameMap.getCoordinates());
        for (String [] lineMap : gameMapString) {
            System.out.println(Arrays.deepToString(lineMap));
        }
        showCommands();
    }

    //Выводит разделитель
    private static void drawPreliminaryData(int countStep) {
        showSplitLine();
        System.out.println("Step: " + countStep);
        showSplitLine();

    }

    private static void showSplitLine () {
        System.out.println(("_").repeat(Constants.WIDTH_MAP.getValue()*5));
    }


    private static void addDrawEntities(String[][] gameMapString, Set<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            int x = coordinate.getX()-1;
            int y = coordinate.getY()-1;
            gameMapString[x][y] = getDrawEntity (coordinate.getEntity());
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

    //Получаем рисунок для сущности
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



    public static void showStartMessage() {
        showSplitLine();
        System.out.println("Добро пожаловать в симуляцию!");
        showSplitLine();
        System.out.println("Размер поля 10х10. Количество каждого существа на карте - 5 шт.");
        System.out.println("Каждый ход добавляются новые существа и трава, если их меньше необходимого количества");
        System.out.println("Это сделано для баланса. Вы всегда можете самостоятельно поменять в константе данные параметры.");
        showSplitLine();
    }

    public static void showCommands () {
        System.out.println("Доступные команды : Старт симуляции - S, пауза - P, выход - E");
    }


    public static void showIncorrectCommand() {
        System.out.println("Неверная команда");
    }

    public static void showPause() {
        System.out.println("Симуляция приостановлена");
    }
}
