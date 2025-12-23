package org.example.utils;

import org.example.models.Coordinate;
import org.example.models.GameMap;

import java.util.*;

public final class BreadthFirstSearch {

    public static Deque<Coordinate> find (Coordinate start, Set<Coordinate> targets, GameMap gameMap) {
        Set<Coordinate> visitedOrBlocked = new HashSet<>();
        addBlocked(visitedOrBlocked, gameMap);

        Queue<Node> queue = new LinkedList<>();
        queue.addAll(getNeighbors(new Node(start, null), gameMap));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (checkTarget (current, targets)) {
                return backtrack(current);
            }

            if (visitedOrBlocked.contains(current.coordinate())){
                continue;
            }

            visitedOrBlocked.add(current.coordinate());
            queue.addAll(getNeighbors(current, gameMap));
        }

        return new LinkedList<>();

    }

    private static Deque<Coordinate> backtrack(Node finalNode) {
        Deque<Coordinate> path = new LinkedList<>();
        Node current = finalNode;

        while (current.parent() != null) {
            path.add(current.coordinate());
            current = current.parent();
        }
        path.removeFirst();
        return path;

    }

    private static Set<Node> getNeighbors(Node current, GameMap gameMap) {
        Set<Node> neighborsNode = new HashSet<>();
        for (Coordinate neighbor : GameMapUtil.getNeighbors(current.coordinate())) {
            neighborsNode.add(new Node(neighbor, current));
        }

        validateNode(neighborsNode, gameMap);

        return neighborsNode;
    }

    private static void validateNode(Set<Node> nodes, GameMap gameMap) {
        List<Node> removeNodes = new ArrayList<>();
        for (Node node: nodes) {
            if (!checkValidation(node, gameMap)) {
                removeNodes.add(node);
            }
        }

        for (Node removed : removeNodes) {
            nodes.remove(removed);
        }
    }

    private static boolean checkValidation(Node node, GameMap gameMap) {
        int width = gameMap.getWidth();
        int height = gameMap.getHeight();

        int x = node.coordinate().x();
        int y = node.coordinate().y();
        return x >= 1 && x <= width && y >= 1 && y <= height;
    }

    private static boolean checkTarget(Node current, Set<Coordinate> targets) {
        return targets.contains(current.coordinate());
    }

    private static void addBlocked(Set<Coordinate> visited, GameMap gameMap) {
        visited.addAll(gameMap.getAllCoordinates());

    }
}
