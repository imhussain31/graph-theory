package BruteForce;

import java.util.Scanner;

public class kth_sum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int targetLevel = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        String tree = scanner.nextLine();

        int treeLength = tree.length();
        int sumAtTargetLevel = 0;
        int currentLevel = -1;

        for (int i = 0; i < treeLength; i++) {
            char currentChar = tree.charAt(i);

            if (currentChar == '(') {
                currentLevel++; // Moving to a deeper level
            } else if (currentChar == ')') {
                currentLevel--; // Moving up a level
            } else if (currentLevel == targetLevel) {
                // Parse the numeric value
                int nodeValue = currentChar - '0';
                i++; // Move to the next character
                while (i < treeLength && Character.isDigit(tree.charAt(i))) {
                    int digit = tree.charAt(i) - '0';
                    nodeValue = nodeValue * 10 + digit;
                    i++;
                }
                i--; // Move back one step as the loop will increment again
                sumAtTargetLevel += nodeValue;
            }
        }

        // Output the sum at the target level
        System.out.println(sumAtTargetLevel);
    }
}
