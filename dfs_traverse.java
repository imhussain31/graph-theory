package DFS;

import java.util.ArrayList;

public class dfs_traverse {
    public ArrayList<Integer> dfsGraph(int v, ArrayList<ArrayList<Integer>> graph){
           boolean[] vis = new boolean[v];
           ArrayList<Integer> ans = new ArrayList<>();

           dfs(0, graph , vis , ans);

           return ans;
    }

    public void dfs(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> ans){
        visited[v] = true;
        ans.add(v);

        for (int neighbour: adj.get(v)){
            if(!visited[neighbour]){
                dfs(neighbour, adj, visited, ans);
            }
        }

    }

    public static void main(String[] args) {
        int v = 6;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < v; i++) {    //For each iteration, a new, empty ArrayList<Integer> is added to graph. This ensures every vertex has an empty list to store its neighbors initially.
            graph.add(new ArrayList<>());
        }

        // Adding edges to the graph
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 5);

        dfs_traverse obj = new dfs_traverse();

        ArrayList<Integer> result = obj.dfsGraph(v , graph);

        System.out.println(result);
    }

    // Method to add an edge to the graph
    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u); // For an undirected graph
    }
}
