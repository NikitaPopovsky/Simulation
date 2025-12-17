package org.example.utils;

import org.example.models.Coordinates;
import org.example.models.GameMap;
import org.example.models.GameMapUtil;

import java.util.*;

public final class BreadthFirstSearch {

    public static Deque<Coordinates> find (Coordinates start, Set<Coordinates> targets, GameMap gameMap) {
        Set<Coordinates> visitedOrBlocked = new HashSet<>();
        addBlocked(visitedOrBlocked, gameMap);

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, null));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (checkTarget (current, targets)) {
                return backtrack(current);
            }

            if (visitedOrBlocked.contains(current.coordinates())){
                continue;
            }

            visitedOrBlocked.add(current.coordinates());
            queue.addAll(getNeighbors(current, gameMap));
        }

        return new LinkedList<>();

    }

    private static Deque<Coordinates> backtrack(Node finalNode) {
        Deque<Coordinates> path = new LinkedList<>();
        Node current = finalNode;

        while (current.parent() != null) {
            path.add(current.coordinates());
            current = current.parent();
        }

        return path;

    }

    private static Set<Node> getNeighbors(Node current, GameMap gameMap) {
        Set<Node> neighborsNode = new HashSet<>();
        for (Coordinates neighbor : GameMapUtil.getNeighbors(current.coordinates())) {
            neighborsNode.add(new Node(neighbor, current));
        }

        validateNode(neighborsNode, gameMap);

        return neighborsNode;
    }

    private static void validateNode(Set<Node> nodes, GameMap gameMap) {
        List<Node> removeNodes = new ArrayList<>();
        for (Node node: nodes) {
            if (checkValidation(node, gameMap)) {
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

        int x = node.coordinates().x();
        int y = node.coordinates().y();
        return x >= 1 && x <= width && y >= 1 && y <= height;
    }
    private static boolean checkTarget(Node current, Set<Coordinates> targets) {
        return targets.contains(current.coordinates());
    }

    private static void addBlocked(Set<Coordinates> visited, GameMap gameMap) {
        visited.addAll(gameMap.getAllCoordinates());

    }
}
