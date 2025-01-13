package DFS;

import java.util.Scanner;

public class jewelMaze {
    public static int[] rowDir = {0, 1, 0, -1};
    public static int[] colDir = {1, 0, -1, 0};

    public static int maxJewels;
    public static int[][] bestPath;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        int[][] maze;
        for(int k = 1; k<=t; k++){
            int N = sc.nextInt();
            maze = new int[N][N];
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                   maze[i][j] = sc.nextInt();
                }
            }

            maxJewels = 0;
            bestPath = new int[N][N];
            int[][] visited = new int[N][N];

            findJewel(maze, visited, 0, 0, N, 0);

            System.out.println("Case #" + k);
            for (int[] row : bestPath) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
            System.out.println(maxJewels);
            if (k < t) System.out.println(); //
        }



    }

    public static void findJewel(int[][] maze, int[][] visited, int row, int col, int N, int jewels){

        if(row == N-1 && col == N-1){
            if(jewels > maxJewels){
                maxJewels = jewels;
                for(int i = 0; i<N; i++){
                    bestPath[i] = visited[row].clone();
                }
            }
            return;
        }

        visited[row][col] = 3;

        for(int i = 0; i<4; i++){
            int newRowDir = row + rowDir[i];
            int newColDir = col + colDir[i];

            if(newRowDir>=0 && newRowDir<N && newColDir>=0 && newColDir<N &&
                visited[newRowDir][newColDir] != 1 && visited[newRowDir][newColDir] != 3
            ){
                int additionalJewels = maze[newRowDir][newColDir] == 2 ? 1 : 0;


                findJewel(maze, visited, newRowDir, newColDir, N, jewels+additionalJewels);
            }
        }
        visited[row][col] = 0;
    }

}
