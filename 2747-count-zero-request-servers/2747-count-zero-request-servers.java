import java.util.Arrays;
class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        int[] count = new int[n + 1];
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        int[][] sortedQueries = new int[m][2];
        for (int idx = 0; idx < m; idx++) {
            sortedQueries[idx][0] = queries[idx];
            sortedQueries[idx][1] = idx;
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0;
        int j = 0;
        int activeServers = 0;
        for (int[] q : sortedQueries) {
            int qTime = q[0];
            int qIdx = q[1];
            while (j < logs.length && logs[j][1] <= qTime) {
                int serverId = logs[j][0];
                if (count[serverId] == 0) {
                    activeServers++;
                }
                count[serverId]++;
                j++;
            }
            while (i < logs.length && logs[i][1] < qTime - x) {
                int serverId = logs[i][0];
                count[serverId]--;
                if (count[serverId] == 0) {
                    activeServers--;
                }
                i++;
            }
            ans[qIdx] = n - activeServers;
        }
        return ans;
    }
}
