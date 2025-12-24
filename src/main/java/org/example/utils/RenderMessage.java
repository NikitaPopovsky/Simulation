package org.example.utils;

import org.example.enums.Commands;
import org.example.enums.Constants;
import org.example.models.GameMap;

public class RenderMessage {

    public static void printStartMessage(GameMap gameMap) {
        RenderUtil.printSplitLine();
        System.out.println("Добро пожаловать в симуляцию!");
        RenderUtil.printSplitLine();
        System.out.printf("Размер поля %sх%s. Количество каждого существа на карте - %s шт. \n",
                gameMap.getWidth(), gameMap.getHeight(), Constants.COUNT_ENTITY);
        System.out.println("Каждый ход добавляются новые существа и трава, если их меньше необходимого количества");
        System.out.println("Это сделано для баланса. Вы всегда можете самостоятельно поменять в константе данные параметры.");
        RenderUtil.printSplitLine();
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
