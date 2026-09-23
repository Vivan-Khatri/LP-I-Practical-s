
import java.util.*;

class RoundRobin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] bt = new int[n];
        int[] rem = new int[n];
        int[] wt = new int[n];
        int[] tat = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rem[i] = bt[i];
        }

        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();

        int time = 0;

        while (true) {
            boolean done = true;

            for (int i = 0; i < n; i++) {
                if (rem[i] > 0) {
                    done = false;

                    if (rem[i] > quantum) {
                        time += quantum;
                        rem[i] -= quantum;
                    } else {
                        time += rem[i];
                        wt[i] = time - bt[i];
                        rem[i] = 0;
                    }
                }
            }

            if (done) {
                break;
            }
        }

        System.out.println("\nProcess\tBT\tWT\tTAT");

        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];

            System.out.println("P" + (i + 1) + "\t"
                    + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }
    }
}
