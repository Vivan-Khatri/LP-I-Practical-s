#include <iostream>
using namespace std;

int main() {
    int n;
    int bt[20], wt[20], tat[20];

    cout << "Enter number of processes: ";
    cin >> n;

    cout << "Enter burst time:\n";
    for (int i = 0; i < n; i++) {
        cout << "P" << i + 1 << ": ";
        cin >> bt[i];
    }

    // Waiting time
    wt[0] = 0;
    for (int i = 1; i < n; i++) {
        wt[i] = wt[i - 1] + bt[i - 1];
    }

    // Turnaround time
    for (int i = 0; i < n; i++) {
        tat[i] = wt[i] + bt[i];
    }

    cout << "\nProcess\tBurst Time\tWaiting Time\tTurnaround Time\n";

    for (int i = 0; i < n; i++) {
        cout << "P" << i + 1 << "\t"
             << bt[i] << "\t\t"
             << wt[i] << "\t\t"
             << tat[i] << endl;
    }

    return 0;
}
