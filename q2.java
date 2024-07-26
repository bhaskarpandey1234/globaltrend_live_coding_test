
import java.util.*;

public class Dijkastra {

    public static Map<Integer, Integer> dijkstra(Map<Integer, Map<Integer, Integer>> graph, int source) {
        // Initialize distances map with infinity for all vertices except the source
        Map<Integer, Integer> distances = new HashMap<>();
        for (Integer vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        // Priority queue to select the vertex with the smallest distance
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
        pq.offer(new AbstractMap.SimpleEntry<>(source, 0));

        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> current = pq.poll();
            int currentVertex = current.getKey();
            int currentDistance = current.getValue();

            if (currentDistance > distances.get(currentVertex)) {
                continue; // Skip if we already found a better path
            }

            // Explore neighbors
            Map<Integer, Integer> neighbors = graph.get(currentVertex);
            for (Map.Entry<Integer, Integer> neighbor : neighbors.entrySet()) {
                int neighborVertex = neighbor.getKey();
                int edgeWeight = neighbor.getValue();

                int distance = currentDistance + edgeWeight;

                // If a shorter path to neighborVertex is found
                if (distance < distances.get(neighborVertex)) {
                    distances.put(neighborVertex, distance);
                    pq.offer(new AbstractMap.SimpleEntry<>(neighborVertex, distance));
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        // Define the graph
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        graph.put(0, new HashMap<>(Map.of(1, 4, 2, 1)));
        graph.put(1, new HashMap<>(Map.of(3, 1)));
        graph.put(2, new HashMap<>(Map.of(1, 2, 3, 5)));
        graph.put(3, new HashMap<>());

        int source = 0;

        // Find shortest paths from the source
        Map<Integer, Integer> shortestPaths = dijkstra(graph, source);

        // Print the shortest paths
        System.out.println("Shortest paths from source " + source + ": " + shortestPaths);
    }
}

