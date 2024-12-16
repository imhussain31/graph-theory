import java.util.ArrayList;

class DetectCycleDirectedGraph_DFS {

    public boolean cycleDetect(int v, ArrayList<ArrayList<Integer>> graph){
        boolean[] vis = new boolean[v];
        boolean[] res = new boolean[v];

        for (int i = 0; i<v; i++){
            if(!vis[i]){
                if(dfs(i , graph, vis ,res)) return true;
            }
        }
        return false;
    }

    public boolean dfs(int v, ArrayList<ArrayList<Integer>> graph, boolean[] vis, boolean[] res){
        vis[v] = true;
        res[v] = true;

        for(int neighbour: graph.get(v)){
            if(!vis[neighbour]){
              if( dfs(neighbour, graph, vis, res)) return true;
            }
            else if(res[neighbour]){
                return true;
            }
        }
        res[v] = false;
        return false;

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


        DetectCycleDirectedGraph_DFS obj = new DetectCycleDirectedGraph_DFS();

        // Check for cycles and print the result
        boolean hasCycle = obj.cycleDetect(v, graph);
        System.out.println("Cycle detected: " + hasCycle);
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
    }
}
