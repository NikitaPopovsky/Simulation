package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.enums.Constants;
import org.example.enums.TypeEntity;
import org.example.models.GameMap;

import java.util.*;

//Класс инициализирует существ на карте при создании симуляции
public class InitializationCreatures extends Action {
    private static InitializationCreatures instance;
    private final int width;
    private final int height;
    private final int countEachEntity;

    public static InitializationCreatures getInstance() {
        if (instance == null) {
            instance = new InitializationCreatures();
        }
        return instance;
    }

    public InitializationCreatures() {
        GameMap gameMap = GameMap.getInstance();
        width = gameMap.getWidth();
        height = gameMap.getHeight();
        countEachEntity = Constants.countEntity.getValue();
    }

    @Override
    public void make() {
        GameMap gameMap = GameMap.getInstance();
        Set<Coordinate> coordinates = generateCoordinates();
        gameMap.setCoordinates(coordinates);
    }

    //Генерирует координаты Х и У каждой сущности
    public Set<Coordinate> generateCoordinates() {
        Set<Coordinate> coordinates = new HashSet<>();
        Map<Integer, TypeEntity> numsEntity = createNumsEntity();

        for(Map.Entry <Integer, TypeEntity> numEntity: numsEntity.entrySet()) {
            Coordinate coordinate = new Coordinate(numEntity.getValue(), width, numEntity.getKey());
            coordinates.add(coordinate);
        }
        return coordinates;

    }

    //Создает таблицу с номерами и типами сущностей
    private Map<Integer, TypeEntity> createNumsEntity() {
        int count = countEachEntity * TypeEntity.values().length;
        Set<Integer> nums = new HashSet<>();

        Set<Integer> generateNums = generateNums(count, nums);

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
    //count - количество номеров которое надо сгенерировать
    //nums - список номеров которые уже есть
    static Set<Integer> generateNums (int count, Set<Integer> nums) {
        Random random = new Random();
        GameMap gameMap = GameMap.getInstance();
        Set<Integer> newNums = new HashSet<>();
        int countFields = gameMap.getWidth() * gameMap.getHeight();


        while (count != 0) {
            int num = getRandomNum(random, countFields);
            if (!nums.contains(num) && !newNums.contains(num)) {
                newNums.add(num);
                count--;
            }
        }
        return  newNums;
    }

    //Получает рандомный номер
    static int getRandomNum(Random random, int countFields) {
        return random.nextInt(1,countFields + 1);
    }
}
