package DFS;

import java.util.*;

class DetectCycleDirectedGraph_DFS {

    public static List<Integer> list  = new ArrayList<Integer>();


    public static boolean traverse(ArrayList<ArrayList<Integer>> graph, int v) {
        boolean[] visited = new boolean[v];
        boolean[] res = new boolean[v];
        Stack<Integer> path = new Stack<Integer>();

        for(int i = 0; i<v; i++) {
            if(!visited[i]) {
                if(dfs(i, graph, visited,res, path)) return true;
            }
        }

        return false;
    }

    public static boolean dfs(int current, ArrayList<ArrayList<Integer>> graph, boolean[] visited, boolean[] res, Stack<Integer> path) {
        visited[current] = true;
        res[current] = true;
        path.push(current);

        for(int neighbour : graph.get(current)) {
            if(!visited[neighbour]) {
                if(dfs(neighbour, graph, visited, res, path)) {
                    return true;
                }
            }else if(res[neighbour]){

                list.clear();
                boolean isCkeck = false;

                for(int node : path) {
                    if(node == neighbour) {
                        isCkeck = true;
                    }
                    if(isCkeck) {
                        list.add(node);
                    }
                }
                Collections.sort(list);
                return true;
            }
        }
        res[current] = false;
        path.pop();
        return false;

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i<v; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for(int i = 0; i<e; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            addEdge(graph, x, y);
        }




        // Detect cycles
        if (traverse(graph, v)) {
            for (int node : list) {
                System.out.print((node + 1) + " ");
            }
        } else {
            System.out.println("No cycle found");
        }



    }

    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
    }
}