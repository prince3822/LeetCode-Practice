class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(')
            return false;
        Boolean[][][] dp = new Boolean[m][n][m + n];
        return dfs(grid, 0, 0, 0, dp);
    }
    boolean dfs(char[][] g, int r, int c, int bal, Boolean[][][] dp) {
        bal += g[r][c] == '(' ? 1 : -1;
        if (bal < 0)
            return false;
        if (r == g.length - 1 && c == g[0].length - 1)
            return bal == 0;
        if (dp[r][c][bal] != null)
            return dp[r][c][bal];
        boolean ans = false;
        if (r + 1 < g.length)
            ans |= dfs(g, r + 1, c, bal, dp);
        if (c + 1 < g[0].length)
            ans |= dfs(g, r, c + 1, bal, dp);
        return dp[r][c][bal] = ans;
    }
}