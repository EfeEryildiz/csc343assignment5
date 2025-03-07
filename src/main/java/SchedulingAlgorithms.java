public class SchedulingAlgorithms {
    public static void main(String[] args) {
        int[] burstTime = {2, 1, 8, 4, 5};
        int[] priority = {2, 1, 4, 2, 3};
        String[] processes = {"P1", "P2", "P3", "P4", "P5"};

        System.out.println("----------------- FCFS -----------------");
        fcfsScheduling(processes, burstTime);

        System.out.println("\n----------------- SJF -----------------");
        sjfScheduling(processes, burstTime);
    }

    public static void fcfsScheduling(String[] processes, int[] burstTime) {
        int n = processes.length;
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        // Calculate waiting time
        waitingTime[0] = 0;  // First process has 0 waiting time
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i-1] + burstTime[i-1];
        }

        // Calculate turnaround time
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = waitingTime[i] + burstTime[i];
        }

        // Print results
        System.out.println("Process ID | Waiting Time | Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.printf("    %s     |      %d       |       %d      \n",
                    processes[i], waitingTime[i], turnaroundTime[i]);
        }

        // Calculate averages
        double avgWaitingTime = 0, avgTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            avgWaitingTime += waitingTime[i];
            avgTurnaroundTime += turnaroundTime[i];
        }
        avgWaitingTime /= n;
        avgTurnaroundTime /= n;

        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
    }

    public static void sjfScheduling(String[] processes, int[] burstTime) {
        int n = processes.length;
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        // Create a copy of processes and burst times for sorting
        String[] processOrder = processes.clone();
        int[] burstTimeCopy = burstTime.clone();

        // Sort processes by burst time
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (burstTimeCopy[j] > burstTimeCopy[j + 1]) {
                    // Swap burst times
                    int tempBurst = burstTimeCopy[j];
                    burstTimeCopy[j] = burstTimeCopy[j + 1];
                    burstTimeCopy[j + 1] = tempBurst;

                    // Swap process IDs
                    String tempProcess = processOrder[j];
                    processOrder[j] = processOrder[j + 1];
                    processOrder[j + 1] = tempProcess;
                }
            }
        }

        // Calculate waiting time
        waitingTime[0] = 0;  // First process has 0 waiting time
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i-1] + burstTimeCopy[i-1];
        }

        // Calculate turnaround time
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = waitingTime[i] + burstTimeCopy[i];
        }

        // Print results
        System.out.println("Process ID | Waiting Time | Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.printf("    %s     |      %d       |       %d      \n",
                    processOrder[i], waitingTime[i], turnaroundTime[i]);
        }

        // Calculate averages
        double avgWaitingTime = 0, avgTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            avgWaitingTime += waitingTime[i];
            avgTurnaroundTime += turnaroundTime[i];
        }
        avgWaitingTime /= n;
        avgTurnaroundTime /= n;

        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
    }
}
