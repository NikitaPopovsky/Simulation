package org.example.utils;

import org.example.models.GameMap;

import java.util.*;

//Класс отвечает за реализацию обхода в ширину
public class BreadthFirstSearch {
    //Коллекция хранит всю историю посещение с родителем(клетка с которой перешли)
    private final Map<Integer, Integer> positionWithParent = new HashMap<>();
    private final Set<Integer> visited = new HashSet<>();
    private final Queue<Integer> queuePosition = new LinkedList<>();
    //Начальная позиция для удобства отладки
    private final int startPosition;
    private int endPosition;
    private final Set<Integer> targetPositions;
    private final int width = GameMap.getInstance().getWidth();
    private final int height = GameMap.getInstance().getHeight();

    public BreadthFirstSearch(int startPosition, Set<Integer> targetPositions, Set<Integer> occupiedPosition) {
        this.startPosition = startPosition;
        this.targetPositions = targetPositions;

        visited.add(startPosition);
        positionWithParent.put(startPosition,0);
        addStaticEntityToVisited(occupiedPosition);
        findNeighbors(startPosition);
    }

    //Ищем соседние позиции и добавляем корректные в очередь
    private void findNeighbors(int position) {
        Set<Integer> neighbors =  getNeighborsPosition(position);
        checkValidationPosition(neighbors, position);
        addToPositionWithParent(neighbors, position);
        addPositionToQueue(neighbors);
    }

    private void addToPositionWithParent(Set<Integer> positions, int parent) {
        for(int pos:positions) {
            positionWithParent.put(pos,parent);
        }
    }

    private void addPositionToQueue(Set<Integer> neighbors) {
        queuePosition.addAll(neighbors);
    }

    //Проверка валидации
    private void checkValidationPosition(Set<Integer> positions, int parent) {
        List<Integer> removeNums = new ArrayList<>();
        for (int position: positions) {
            //int position = neighbors.get(i);
            //если позиция за границей карты сверху или снизу
            if (position <= 0 ||  position > width*height
                //или если посещена/занята и не целевая
                || (visited.contains(position) && !targetPositions.contains(position))
                //если родитель на краю карты справа, а позиция за границей карты
                || (parent % width == 0 && position % width == 1 )
                //если родитель на краю карты слева, а позиция за границей карты
                || (parent % width == 1 && position % width == 0 )) {
                removeNums.add(position);
            }
        }

        for (int num : removeNums) {
            positions.remove(num);
        }
    }

    //Добавляем клетку сверху, снизу , справа, слева
    public static Set<Integer> getNeighborsPosition(int curPosition) {
        int width = GameMap.getInstance().getWidth();
        Set <Integer> neighbors = new HashSet<>();
        neighbors.add(curPosition+1);
        neighbors.add(curPosition-1);
        neighbors.add(curPosition + width);
        neighbors.add(curPosition - width);

        return neighbors;
    }

    public void addStaticEntityToVisited(Set<Integer> staticEntity) {
        visited.addAll(staticEntity);
    }

    //Делаем обход в ширину, пока не наступим на целевую позицию
    public void searchEndPosition(){
        while (!queuePosition.isEmpty()) {
            int nextPosition = queuePosition.poll();

            if (checkEndPosition (nextPosition)) {
                endPosition = nextPosition;
                break;
            }
            visited.add(nextPosition);

            findNeighbors(nextPosition);
        }
    }

    private boolean checkEndPosition(int nextPosition) {
        return targetPositions.contains(nextPosition);
    }


    //Когда мы нашли конечную позицию, нам нужно понять по каким клеткам мы сюда дошли
    //для этого перебираем коллекцию позиций с родителями (клетка с которой перешли)
    public Deque<Integer> positionsForNextStep() {
        Deque<Integer> steps = new LinkedList<>();
        if (endPosition == 0) {
            return steps;
        }
        int currentPosition = endPosition;
        int parentCurrentPosition = positionWithParent.get(currentPosition);
        while (parentCurrentPosition != 0) {
            if(currentPosition != endPosition) {
                steps.add(currentPosition);
            }
            currentPosition = parentCurrentPosition;
            parentCurrentPosition = positionWithParent.get(currentPosition);
        }

        return steps;
    }
}
