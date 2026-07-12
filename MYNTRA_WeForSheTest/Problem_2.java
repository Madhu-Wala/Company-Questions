package MYNTRA_WeForSheTest;
import java.util.*;
public class Problem_2 {
    public static int findNumberOfDays(int n, int m, int[][] grid) {
        long totalHours = 0;
        int totalSites = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] > 0) {
                    totalSites++;

                    // 1. Add the time required to service the laptop
                    totalHours += grid[i][j];

                    // 2. Add ROUND-TRIP travel time: 2 hours per unit distance each way
                    // Distance = (i + j). Round trip time = (i + j) * 2 * 2 = 4 * (i + j)
                    long roundTripTime = (long)(i + j) * 4;
                    totalHours += roundTripTime;
                }
            }
        }

        // If there are no sites to visit, she needs 0 days
        if (totalSites == 0) {
            return 0;
        }

        // Calculate minimum days globally using integer ceiling division
        // This perfectly packs multiple tasks into the same 8-hour days
        long days = (totalHours + 7) / 8;

        return (int) days;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            System.out.print(findNumberOfDays(n, m, grid));
        }
        sc.close();
    }
}
