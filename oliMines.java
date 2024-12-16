import java.util.Scanner;

public class oliMines {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t--!=0){
            int m = sc.nextInt();
            int n = sc.nextInt();
            int target = 2;

            int[] arr = new int[n];

            for (int i = 0; i<arr.length; i++){
                arr[i] = sc.nextInt();
            }

            binarySearch(arr , target);

        }

    }

    public static int binarySearch(int[] arr, int k){
        int left = arr[0];
        int right = arr.length - 1;

        while(left<=right){
            int mid = left + (left-right)/2;

            if(arr[mid] == k){
                return arr[mid];
            }

            if(arr[mid] < k){
                left = mid +1;
            }else{
                right = mid - 1;
            }
        }

        return -1;


    }


}
