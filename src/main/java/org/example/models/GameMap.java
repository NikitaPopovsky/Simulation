package org.example.models;

import org.example.TypeEntity;
import org.example.dto.Cordinate;

import java.util.*;

public class GameMap {
    private Set<Cordinate> coordinates = new HashSet<>();
    private int width;
    private int height;
    private int countEachEntity;

    public GameMap(int width, int height, int countEachEntity) {
        this.width = width;
        this.height = height;
        this.countEachEntity = countEachEntity;
    }

    //Генерирует координаты Х и У каждой сущности
    public void generateCoordinates() {
        Map<Integer, TypeEntity> numsEntity = createNumsEntity();

        for(Map.Entry <Integer, TypeEntity> numEntity: numsEntity.entrySet()) {
            Cordinate cordinate = new Cordinate(numEntity.getValue(), width, height, numEntity.getKey());
            coordinates.add(cordinate);
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
    public Set<Integer> generateNums () {
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
    public int getRandomNum(Random random, int countFields) {
        return random.nextInt(countFields + 1);
    }

}


