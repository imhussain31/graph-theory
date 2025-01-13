package BruteForce;

import java.util.Scanner;

public class AeroplaneBombing {

    public static int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int k = 1; k<=t; k++){
            int row = sc.nextInt();

            int[][] board = new int[row][5];

            for(int i = 0; i<row; i++){
                for(int j = 0; j<5; j++){
                    board[i][j] = sc.nextInt();
                }
            }

            ans = Integer.MIN_VALUE;

            int isSafeRow = 0;
            getMaxCoins(board, isSafeRow, board.length - 1, 1, false, 0);
            getMaxCoins(board, isSafeRow, board.length - 1, 2, false, 0);
            getMaxCoins(board, isSafeRow, board.length - 1, 3, false, 0);

            System.out.println("#"+k+" "+ans);

        }

    }

    public static void getMaxCoins(int[][] board, int isSafeRow, int cur_row, int cur_col, boolean isBombedUsed, int coins){
        if(cur_row<0 || cur_col>=5 || cur_col<0){
            ans = Math.max(ans, coins);
            return;
        }

        if(board[cur_row][cur_col] == 1 || board[cur_row][cur_col] == 0){
            int newCoins = coins;
            if(board[cur_row][cur_col] == 1){
                newCoins++;
            }
            if(isBombedUsed){
                isSafeRow--;
            }
            getMaxCoins(board, isSafeRow, cur_row-1, cur_col, isBombedUsed, newCoins);
            getMaxCoins(board, isSafeRow, cur_row-1, cur_col + 1, isBombedUsed, newCoins);
            getMaxCoins(board, isSafeRow, cur_row-1, cur_col - 1, isBombedUsed, newCoins);
        } else if(board[cur_row][cur_col] == 2){
             if(isBombedUsed && isSafeRow<=0){
                 ans = Math.max(ans, coins);
                 return;
             } else if(isBombedUsed && isSafeRow > 0){
                 isSafeRow--;
                 getMaxCoins(board, isSafeRow, cur_row-1, cur_col, isBombedUsed, coins);
                 getMaxCoins(board, isSafeRow, cur_row-1, cur_col + 1, isBombedUsed, coins);
                 getMaxCoins(board, isSafeRow, cur_row-1, cur_col - 1, isBombedUsed, coins);
             }else{
                 isBombedUsed = true;
                 isSafeRow = 4;
                 getMaxCoins(board, isSafeRow, cur_row-1, cur_col, isBombedUsed, coins);
                 getMaxCoins(board, isSafeRow, cur_row-1, cur_col + 1, isBombedUsed, coins);
                 getMaxCoins(board, isSafeRow, cur_row-1, cur_col - 1, isBombedUsed, coins);
             }
        }
    }
}
