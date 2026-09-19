
import java.util.Scanner;

class LRUArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input number of frames in memory
        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        // 2. Input number of pages that will be accessed
        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        // 3. Input exactly 'n' pages
        int[] pages = new int[n];
        System.out.println("Enter exactly " + n + " page numbers:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        // 4. Create memory array to store pages in frames
        int[] memory = new int[frames];

        // 5. Array to track when each frame was last used
        int[] recent = new int[frames];

        // 6. Initialize memory frames to -1 (empty)
        for (int i = 0; i < frames; i++) {
            memory[i] = -1;
        }

        int pageFaults = 0; // Count of page faults

        // 7. Process each page one by one
        for (int i = 0; i < n; i++) {
            int page = pages[i];
            boolean found = false;

            // 7a. Check if page is already in memory
            for (int j = 0; j < frames; j++) {
                if (memory[j] == page) {
                    found = true;        // Page is already in memory
                    recent[j] = i;       // Update last used time
                    break;
                }
            }

            // 7b. If page not found, it is a page fault
            if (!found) {
                pageFaults++;

                // Find the least recently used (LRU) page index
                int lruIndex = 0;
                int minRecent = Integer.MAX_VALUE;
                for (int j = 0; j < frames; j++) {
                    if (memory[j] == -1) { // Empty frame found
                        lruIndex = j;
                        break;
                    } else if (recent[j] < minRecent) { // Least recently used
                        minRecent = recent[j];
                        lruIndex = j;
                    }
                }

                // Replace LRU page with the new page
                memory[lruIndex] = page;
                recent[lruIndex] = i; // Update recent usage
            }

            // 7c. Print current state of frames after accessing this page
            System.out.print("Frames after accessing page " + page + ": ");
            for (int j = 0; j < frames; j++) {
                if (memory[j] != -1) {
                    System.out.print(memory[j] + " ");
                }
            }
            System.out.println();
        }

        // 8. Print total number of page faults
        System.out.println("Total page faults: " + pageFaults);
    }
}
