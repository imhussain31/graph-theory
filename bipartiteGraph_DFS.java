package DFS;

import java.util.ArrayList;
import java.util.Arrays;

public class bipartiteGraph_DFS {
    public boolean bipartite(int v, ArrayList<ArrayList<Integer>> graph){
        int[] color = new int[v];
        Arrays.fill(color, -1); // Initialize all vertices as uncolored

        // Check all components (important for disconnected graphs)
        for (int i = 0; i < v; i++) {
            if (color[i] == -1) { // Unvisited node
                if (!checkDfs(i, graph, color, 0)) {
                    return false; // Not bipartite
                }
            }
        }
        return true; // Graph is bipartite
    }

    public boolean checkDfs(int node, ArrayList<ArrayList<Integer>> adj, int[] color, int currentColor){
        color[node] = currentColor;

        for (int neighbor : adj.get(node)) {
            if (color[neighbor] == -1) { // If neighbor is uncolored
                if (!checkDfs(neighbor, adj, color, 1 - currentColor)) {
                    return false;
                }
            } else if (color[neighbor] == color[node]) { // If neighbor has the same color
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int v = 6;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        // Adding edges to the graph
        addEdge(graph, 0, 3);
        addEdge(graph, 3, 2);
        addEdge(graph, 2, 4);
        addEdge(graph, 4, 3);

        bipartiteGraph_DFS obj = new bipartiteGraph_DFS();

        if (obj.bipartite(v, graph)) {
            System.out.println("The graph is bipartite.");
        } else {
            System.out.println("The graph is not bipartite.");
        }
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u); // For an undirected graph
    }

}
