import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import java.util.*;

public class shortestPathBFS {

    // Method to calculate the shortest path from a source to all vertices using BFS
    public int[] shortestPath(int v, ArrayList<ArrayList<Integer>> graph, int src) {
        // Initialize a queue to process nodes level by level
        Queue<Integer> queue = new LinkedList<>();
        // Add the source node to the queue
        queue.add(src);

        // Array to store distances from the source to each vertex, initialized to -1
        int[] distance = new int[v];
        Arrays.fill(distance, -1);
        // Distance to the source itself is 0
        distance[src] = 0;

        // BFS loop to traverse the graph
        while (!queue.isEmpty()) {
            // Fetch the current node from the queue
            int currentNode = queue.poll();

            // Traverse all neighbors of the current node
            for (int neighbor : graph.get(currentNode)) {
                // If the neighbor has not been visited yet
                if (distance[neighbor] == -1) {
                    // Update the distance to the neighbor
                    distance[neighbor] = distance[currentNode] + 1;
                    // Add the neighbor to the queue for further processing
                    queue.add(neighbor);
                }
            }
        }

        // Return the array of distances
        return distance;
    }

    public static void main(String[] args) {
        int v = 6; // Number of vertices in the graph
        int source = 0; // Source vertex
        int destination = 5; // Destination vertex (not used in this logic)

        // Initialize the adjacency list to represent the graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>()); // Create an empty list for each vertex
        }

        // Add edges to the graph
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 3, 4);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 5);

        // Create an instance of the shortestPathBFS class
        shortestPathBFS obj = new shortestPathBFS();

        // Compute the shortest path distances from the source vertex
        int[] distances = obj.shortestPath(v, graph, source);

        // Print the shortest distances from the source to all vertices
        System.out.println("Shortest distances from source " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vertex " + i + ": " + distances[i]);
        }
    }

    // Utility method to add an edge to the graph
    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v); // Add v to u's adjacency list
        graph.get(v).add(u); // Add u to v's adjacency list (since the graph is undirected)
    }
}
