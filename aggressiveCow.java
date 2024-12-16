import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class aggressiveCow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t--!=0){
            int n = sc.nextInt();
            int[] arr = new int[n];

            for(int i = 0; i<arr.length; i++){
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);


        }

    }
}
