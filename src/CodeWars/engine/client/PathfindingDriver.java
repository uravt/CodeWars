package CodeWars.engine.client;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class PathfindingDriver
{
    public static void main(String[] args) throws IOException
    {
        UI ui = new UI();
        Map map = new Map(ui);
        /*
        if you want to load a map immediately after the program starts do it like this:
        --------------------------------------------------------------------------------
        UI ui = new UI();
        Map map = new Map(ui);
        map.loadFile("devious.plmap");
        --------------------------------------------------------------------------------
        */

    }

    public static List<Point> shortestPath(Map map, Point initial, Point destination)
    {
        PriorityQueue<Node> unvisitedNodes = new PriorityQueue<>();
        HashSet<Node> visitedNodes = new HashSet<>();
        HashMap<Point, Integer> distances = new HashMap<>();

        Node currentNode = new Node(initial, 0, null);
        unvisitedNodes.add(currentNode);

        for(int x = 0; x < map.getSize(); x++)
        {
            for(int y = 0; y < map.getSize(); y++)
            {
                distances.put(new Point(x,y), Integer.MAX_VALUE);
            }
        }

        while(!unvisitedNodes.isEmpty() && !currentNode.position.equals(destination))
        {
            currentNode = unvisitedNodes.remove();
            visitedNodes.add(currentNode);
            Node north = new Node(
                    new Point(currentNode.position.x, currentNode.position.y + 1),
                    currentNode.cost + 1,
                    currentNode);
            Node east = new Node(
                    new Point(currentNode.position.x + 1, currentNode.position.y),
                    currentNode.cost + 1,
                    currentNode);
            Node south = new Node(
                    new Point(currentNode.position.x, currentNode.position.y - 1),
                    currentNode.cost + 1,
                    currentNode);
            Node west = new Node(
                    new Point(currentNode.position.x - 1, currentNode.position.y),
                    currentNode.cost + 1,
                    currentNode);
            if(!visitedNodes.contains(north) && map.onTheMap(north.position) && map.isTraversable(north.position))
            {
                if(currentNode.cost + 1 < distances.get(north.position))
                {
                    distances.put(north.position, currentNode.cost + 1);
                    unvisitedNodes.add(new Node(north.position, currentNode.cost + 1, currentNode));
                }
            }
            if(!visitedNodes.contains(east) && map.onTheMap(east.position) && map.isTraversable(east.position))
            {
                if(currentNode.cost + 1 < distances.get(east.position))
                {
                    distances.put(east.position, currentNode.cost + 1);
                    unvisitedNodes.add(new Node(east.position, currentNode.cost + 1, currentNode));
                }
            }
            if(!visitedNodes.contains(south) && map.onTheMap(south.position) && map.isTraversable(south.position))
            {
                if(currentNode.cost + 1 < distances.get(south.position))
                {
                    distances.put(south.position, currentNode.cost + 1);
                    unvisitedNodes.add(new Node(south.position, currentNode.cost + 1, currentNode));
                }
            }
            if(!visitedNodes.contains(west) && map.onTheMap(west.position) && map.isTraversable(west.position))
            {
                if(currentNode.cost + 1 < distances.get(west.position))
                {
                    distances.put(west.position, currentNode.cost + 1);
                    unvisitedNodes.add(new Node(west.position, currentNode.cost + 1, currentNode));
                }
            }
        }
        
        ArrayList<Point> points = new ArrayList<>();
        
        Node tempNode = currentNode;
        while (tempNode.previous != null)
        {
            points.add(tempNode.position);
            tempNode = tempNode.previous;
        }
        return points;
    }


    static class Node implements Comparable
    {
        public Point position;
        public int cost;
        public Node previous;

        public Node(Point position, int cost, Node previous)
        {
            this.position = position;
            this.cost = cost;
            this.previous = previous;
        }

        @Override
        public boolean equals(Object obj)
        {
            Node other = (Node) obj;
            return other.position.equals(position);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(position);
        }

        @Override
        public int compareTo(Object o)
        {
            Node node = (Node) o;
            return Integer.compare(cost, node.cost);
        }
    }
}

