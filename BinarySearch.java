import java.util.Arrays;

public class BinarySearch {
    public static void search(int[] arr, int len, int k) {
        // Step 1: Sort the array for binary search
        Arrays.sort(arr);

        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Correct calculation of mid

            if (arr[mid] == k) {
                System.out.println("Number exists");
                return; // Exit the function if the number is found
            }
            if (arr[mid] < k) {
                left = mid + 1; // Move to the right half
            } else {
                right = mid - 1; // Move to the left half
            }
        }
        System.out.println("Number does not exist");
    }

    public static void main(String[] args) {
        int[] arr = {1, 8, 5, 3, 9, 2, 4};
        int k = 8;

        search(arr, arr.length, k);
    }
}
