
class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)
        min_price = min(price for factor, price in items)

        dp = [0] * (budget + 1)

        for i in range(n):
            factor, price = items[i]

            free = sum(
                1 for j in range(n)
                if i != j and items[j][0] % factor == 0
            )

            for b in range(budget, price - 1, -1):
                dp[b] = max(dp[b], dp[b - price] + free + 1)

        return max(
            dp[b] + (budget - b) // min_price
            for b in range(budget + 1)
        )
   