class Solution:
    def climbStairs(self, n, costs):
        INF = float('inf')
        dp = [INF] * (n + 1)
        dp[0] = 0
        for j in range(1, n + 1):
            for i in range(max(0, j - 3), j):
                dp[j] = min(dp[j], dp[i] + costs[j - 1] + (j - i) ** 2)
        return dp[n]    