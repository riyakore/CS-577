import java.util.Random;
import java.util.Scanner;

public class Clauses {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // number of variables
        int n = scnr.nextInt();

        // number of clauses
        int m = scnr.nextInt();

        // storing the numbers in clauses array
        int[][] clauses = new int[m][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                clauses[i][j] = scnr.nextInt();
            }
        }

        scnr.close();

        int[] assignment = solveMax3Sat(n, m, clauses);
        
        for (int i = 0; i < n; i++) {
            System.out.print(assignment[i] + " ");
        }
    }

    private static int[] solveMax3Sat(int n, int m, int[][] clauses) {
        int[] assignment = new int[n];
        Random random = new Random();
        
        for (int i = 0; i < 1000; i++) {
            int[] currentAssignment = new int[n];
            
            for (int j = 0; j < n; j++) {
                currentAssignment[j] = random.nextBoolean() ? 1 : -1;
            }
            
            int satisfiedClauses = countSatisfiedClauses(currentAssignment, clauses);
            
            if (satisfiedClauses >= (7.0 / 8.0) * m) {
                assignment = currentAssignment;
                break;
            }
        }

        return assignment;
    }

    private static int countSatisfiedClauses(int[] assignment, int[][] clauses) {
        int count = 0;
        for (int[] clause : clauses) {
            if (isClauseSatisfied(assignment, clause)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isClauseSatisfied(int[] assignment, int[] clause) {
        for (int value : clause) {
            int number = Math.abs(value);
            boolean negative = value < 0;

            if ((assignment[number - 1] == 1 && !negative) || (assignment[number - 1] == -1 && negative)) {
                return true;
            }
        }
        
        return false;
    }
}
