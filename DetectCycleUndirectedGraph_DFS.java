package DFS;

import java.util.ArrayList;

public class DetectCycleUndirectedGraph_DFS {

    public boolean dfs(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                if (dfsTraverse(i, adj, visited, -1)) return true;
            }
        }
        return false;
    }

    public boolean dfsTraverse(int v, ArrayList<ArrayList<Integer>> graph, boolean[] vis, int parent) {
        vis[v] = true;

        for (int neighbour : graph.get(v)) {
            if (!vis[neighbour]) {
                if (dfsTraverse(neighbour, graph, vis, v)) return true;
            } else if (parent != neighbour) {
                return true; // A cycle is detected
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int v = 6;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        // Adding edges to the graph
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 3, 4);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 5);
        addEdge(graph, 2, 5);

        DetectCycleUndirectedGraph_DFS obj = new DetectCycleUndirectedGraph_DFS();

        // Check for cycles and print the result
        boolean hasCycle = obj.dfs(v, graph);
        System.out.println("Cycle detected: " + hasCycle);
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u); // For an undirected graph
    }
}
