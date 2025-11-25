package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.enums.TypeEntity;
import org.example.models.GameMap;

import java.util.*;

public class InitializationCreatures extends Action{
    @Override
    public void make() {
        generateCoordinates();
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
