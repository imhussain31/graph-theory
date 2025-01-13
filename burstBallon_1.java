package BruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class burstBallon_1 {
    public static int calculateMaxPoints(List<Integer> points) {
        if (points.size() == 1){
            return points.get(0);
        }

        int maxPoints = Integer.MIN_VALUE;

        for (int i = 0; i < points.size(); i++) {
            int currentPoint = points.get(i);
            points.remove(i);

            int remainingPoints = calculateMaxPoints(points);

            points.add(i, currentPoint);

            if (i == 0) {
                remainingPoints += points.get(i + 1);
            } else if (i == points.size() - 1) {
                remainingPoints += points.get(i - 1);
            } else {
                remainingPoints += points.get(i + 1) * points.get(i - 1);
            }

            maxPoints = Math.max(maxPoints, remainingPoints);
        }

        return maxPoints;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            points.add(scanner.nextInt());
        }

        System.out.println(calculateMaxPoints(points));
        scanner.close();
    }
}