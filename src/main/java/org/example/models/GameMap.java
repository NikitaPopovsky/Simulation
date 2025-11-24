package org.example.models;

import org.example.TypeEntity;
import org.example.dto.Coordinate;

import java.util.*;

public class GameMap {
    private static GameMap instance;
    private Set<Coordinate> coordinates = new HashSet<>();
    private int width;
    private int height;
    private int countEachEntity;

    public static GameMap getInstance() {
        if (instance == null) {
            instance = new GameMap();
        }
        return instance;
    }

    public GameMap() {
    }

    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Set<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCountEachEntity() {
        return countEachEntity;
    }

    public void setCountEachEntity(int countEachEntity) {
        this.countEachEntity = countEachEntity;
    }

    //Генерирует координаты Х и У каждой сущности
    public void generateCoordinates() {
        Map<Integer, TypeEntity> numsEntity = createNumsEntity();

        for(Map.Entry <Integer, TypeEntity> numEntity: numsEntity.entrySet()) {
            Coordinate coordinate = new Coordinate(numEntity.getValue(), width, height, numEntity.getKey());
            coordinates.add(coordinate);
        }

    }

    //Создает таблицу с номерами и типами сущностей
    private Map<Integer, TypeEntity> createNumsEntity() {
        Set<Integer> generateNums = generateNums();
        List<TypeEntity> typeEntities = createTypeEntities();
        return connectNumsAndEntity(generateNums, typeEntities);
    }

    //Создает массив типов сущностей
    private List<TypeEntity> createTypeEntities() {
        return Arrays.asList(TypeEntity.values());
    }

    //Соединяет сгенерированные число и типы сущностей
    private Map <Integer, TypeEntity> connectNumsAndEntity(Set<Integer> generateNums, List<TypeEntity> typeEntities) {
        java.util.Map<Integer, TypeEntity> numsEntity = new HashMap<>();
        int count = 0;

        for (int num: generateNums) {
            TypeEntity type = typeEntities.get(count / countEachEntity);
            numsEntity.put(num,type);
            count++;
        }
        return numsEntity;
    }

    //Генерирует номера, проверяя уникальность
    private Set<Integer> generateNums () {
        Random random = new Random();
        Set<Integer> nums = new HashSet<>();
        int countFields = width * height;
        int count = countEachEntity * TypeEntity.values().length;

        while (count != 0) {
            int num = getRandomNum(random, countFields);
            if (nums.add(num)) {
                count--;
            }
        }
        return  nums;
    }

    //Получает рандомный номер
    private int getRandomNum(Random random, int countFields) {
        return random.nextInt(1,countFields + 1);
    }

}


