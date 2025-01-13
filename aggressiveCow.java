package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class aggressiveCow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t--!=0){
            int stalls = sc.nextInt();
            int cow = sc.nextInt();
            int[] arr = new int[stalls];
            for(int i = 0; i<arr.length; i++){
                arr[i] = sc.nextInt();
            }

            System.out.println(search(arr, cow));
        }

    }

    public static int search(int[] arr, int cow){
        if(arr.length == 0){
            return 0;
        }

        Arrays.sort(arr);

        int low = arr[0];
        int high = arr[arr.length-1];
        int ans = 0;
        while(low<=high){
            int mid = (low+high)/2;

            if(isPossible(arr, mid, cow)){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        return ans;

    }

    public static boolean isPossible(int[] arr, int mid, int cow){

        int last = arr[0];
        int count = 1;

        for (int i = 0; i<arr.length; i++){
            if(arr[i] - last >= mid){
                count++;
                last = arr[i];
            }
            if(count == cow){
                return true;
            }
        }


        return false;

    }

}
