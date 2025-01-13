package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import java.util.*;

public class shortestPathBFS {

    // Method to find the shortest path using BFS
    public List<Integer> findShortestPath(int v, ArrayList<ArrayList<Integer>> graph, int src, int dest) {
        // Queue to manage BFS traversal
        Queue<List<Integer>> queue = new LinkedList<>();

        // Add the source node as the starting path
        queue.add(Collections.singletonList(src));

        // Set to track visited nodes
        boolean[] visited = new boolean[v];
        visited[src] = true;

        // Perform BFS
        while (!queue.isEmpty()) {
            // Get the current path from the queue
            List<Integer> path = queue.poll();

            // Get the last node in the current path
            int lastNode = path.get(path.size() - 1);

            // If the destination is reached, return the path
            if (lastNode == dest) {
                return path;
            }

            // Traverse all neighbors
            for (int neighbor : graph.get(lastNode)) {
                if (!visited[neighbor]) {
                    // Mark neighbor as visited
                    visited[neighbor] = true;

                    // Create a new path including this neighbor
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);

                    // Add the new path to the queue
                    queue.add(newPath);
                }
            }
        }

        // If no path is found, return an empty list
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        int v = 8; // Number of vertices
        int source = 0; // Source vertex
        int destination = 7; // Destination vertex

        // Initialize the adjacency list for the graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges to the graph
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 0, 3);
        addEdge(graph, 3, 4);
        addEdge(graph, 4, 7);
        addEdge(graph, 3, 7);
        addEdge(graph, 6, 7);
        addEdge(graph, 4, 5);
        addEdge(graph, 4, 6);
        addEdge(graph, 5, 6);

        // Create an instance of the ShortestPathBFS class
        shortestPathBFS obj = new shortestPathBFS();

        // Find the shortest path from source to destination
        List<Integer> shortestPath = obj.findShortestPath(v, graph, source, destination);

        // Print the shortest path
        if (shortestPath.isEmpty()) {
            System.out.println("No path exists from " + source + " to " + destination);
        } else {
            System.out.println("Shortest path from " + source + " to " + destination + ": " + shortestPath);
        }
    }

    // Utility method to add an edge to the graph
    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v); // Add v to u's adjacency list
        graph.get(v).add(u); // Add u to v's adjacency list (undirected graph)
    }
}

