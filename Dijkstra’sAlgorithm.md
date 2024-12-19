Here’s the easiest and most optimized solution for implementing **Dijkstra's Algorithm** in Java to find the shortest path from a source vertex to all other vertices in the graph.

### Code Explanation

This solution uses:
- **Priority Queue** for efficiently finding the vertex with the smallest tentative distance.
- **Adjacency List** to represent the graph for efficient edge traversal.

Here’s the complete code:

```java
import java.util.*;

public class DijkstraAlgorithm {
    // A helper class to represent an edge with a destination and weight
    static class Edge {
        int destination, weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Dijkstra's algorithm implementation
    public static void dijkstra(int src, int vertices, List<List<Edge>> graph) {
        int[] distances = new int[vertices]; // Stores shortest distances from src
        Arrays.fill(distances, Integer.MAX_VALUE); // Initialize distances to infinity
        distances[src] = 0;

        // PriorityQueue to get the vertex with the smallest distance
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        pq.offer(new Edge(src, 0)); // Add the source vertex to the queue

        while (!pq.isEmpty()) {
            Edge current = pq.poll(); // Get the vertex with the smallest distance
            int currentVertex = current.destination;
            int currentDistance = current.weight;

            // Explore all adjacent vertices
            for (Edge neighbor : graph.get(currentVertex)) {
                int newDist = currentDistance + neighbor.weight;
                if (newDist < distances[neighbor.destination]) {
                    distances[neighbor.destination] = newDist; // Update distance
                    pq.offer(new Edge(neighbor.destination, newDist)); // Add to queue
                }
            }
        }

        // Print the distances from the source
        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println("To " + i + ": " + distances[i]);
        }
    }

    public static void main(String[] args) {
        int vertices = 9; // Total number of vertices
        List<List<Edge>> graph = new ArrayList<>(); // Adjacency list to represent the graph

        // Initialize the adjacency list
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges (graph is undirected)
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(7, 8));
        graph.get(1).add(new Edge(2, 12));
        graph.get(1).add(new Edge(7, 11));
        graph.get(2).add(new Edge(3, 7));
        graph.get(2).add(new Edge(8, 4));
        graph.get(3).add(new Edge(4, 14));
        graph.get(4).add(new Edge(5, 10));
        graph.get(5).add(new Edge(6, 2));
        graph.get(6).add(new Edge(7, 6));
        graph.get(7).add(new Edge(8, 1));
        graph.get(2).add(new Edge(5, 5));

        // Call Dijkstra's algorithm
        dijkstra(0, vertices, graph); // Source vertex is 0
    }
}
```

---

### Explanation

1. **Graph Representation:**
   - The graph is represented as an adjacency list of `List<List<Edge>>`, where each vertex points to its neighbors with their respective weights.
   - This is efficient for both space and time in sparse graphs (fewer edges).

2. **Initialization:**
   - An array `distances` is used to keep track of the shortest distance from the source to each vertex. Initially, all values are set to `Integer.MAX_VALUE` (infinity), except for the source vertex (distance 0).
   - A `PriorityQueue` is used to store vertices sorted by their distance from the source.

3. **Relaxation:**
   - For each vertex, all its neighbors are checked. If a shorter path is found to a neighbor, the `distances` array is updated, and the neighbor is added to the priority queue.

4. **Termination:**
   - The algorithm terminates when the priority queue is empty, meaning all vertices have been processed.

5. **Output:**
   - The final `distances` array contains the shortest distance from the source to all other vertices.

---

### Time Complexity
- **O((V + E) log V)**:
  - `V` is the number of vertices.
  - `E` is the number of edges.
  - The `log V` factor comes from the `PriorityQueue`.

---

### Space Complexity
- **O(V + E)**:
  - `O(V)` for the `distances` array.
  - `O(E)` for the adjacency list.

This implementation is efficient and suitable for most real-world applications. Let me know if you need further clarification!
