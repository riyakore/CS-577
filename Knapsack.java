import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numInstances = scanner.nextInt();

        for (int i = 0; i < numInstances; i++) {
            int numItems = scanner.nextInt();
            int capacity = scanner.nextInt();
            int[] weights = new int[numItems];
            int[] values = new int[numItems];

            for (int j = 0; j < numItems; j++) {
                weights[j] = scanner.nextInt();
                values[j] = scanner.nextInt();
            }

            int result = knapsack(numItems, capacity, weights, values);
            System.out.println(result);
        }
    }

    public static int knapsack(int numItems, int capacity, int[] weights, int[] values) {
        int[] dp = new int[capacity + 1];

        for (int i = 1; i <= numItems; i++) {
            for (int w = capacity; w >= weights[i - 1]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i - 1]] + values[i - 1]);
            }
        }

        return dp[capacity];
    }
}
