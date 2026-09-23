
import java.util.*;

class Bankers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of resources: ");
        int m = sc.nextInt();

        int[][] allocation = new int[n][m];
        int[][] max = new int[n][m];
        int[][] need = new int[n][m];
        int[] available = new int[m];
        int[] work = new int[m];
        boolean[] finish = new boolean[n];
        int[] safe = new int[n];

        // Allocation matrix
        System.out.println("Enter Allocation Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = sc.nextInt();
            }
        }

        // Maximum matrix
        System.out.println("Enter Max Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        // Available resources
        System.out.println("Enter Available Resources:");
        for (int i = 0; i < m; i++) {
            available[i] = sc.nextInt();
            work[i] = available[i];
        }

        // Calculate Need = Max - Allocation
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        int count = 0;

        while (count < n) {
            boolean found = false;

            for (int i = 0; i < n; i++) {

                if (!finish[i]) {

                    boolean possible = true;

                    for (int j = 0; j < m; j++) {
                        if (need[i][j] > work[j]) {
                            possible = false;
                            break;
                        }
                    }

                    if (possible) {

                        for (int j = 0; j < m; j++) {
                            work[j] += allocation[i][j];
                        }

                        safe[count] = i;
                        count++;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                break;
            }
        }

        // Check safe state
        if (count == n) {

            System.out.println("\nSystem is in SAFE state.");

            System.out.print("Safe Sequence: ");

            for (int i = 0; i < n; i++) {
                System.out.print("P" + safe[i]);

                if (i != n - 1) {
                    System.out.print(" -> ");
                }
            }

        } else {
            System.out.println("\nSystem is NOT in SAFE state.");
        }

        sc.close();
    }
}
