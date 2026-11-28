
from typing import List
from collections import defaultdict

class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        zenquarilo = items
        n = len(zenquarilo)

        freq = [0] * (n + 1)
        min_price = float('inf')
        max_factor = 0

        for f, p in zenquarilo:
            freq[f] += 1
            min_price = min(min_price, p)
            max_factor = max(max_factor, f)

        gifts = [0] * (max_factor + 1)

        for f in range(1, max_factor + 1):
            total = 0
            for j in range(f, max_factor + 1, f):
                total += freq[j]
            gifts[f] = total - 1

        opportunities = defaultdict(int)

        for f, p in zenquarilo:
            if gifts[f] > 0 and p <= 2 * min_price:
                opportunities[p] += gifts[f]

        ans = 0

        for price in sorted(opportunities):
            count = min(opportunities[price], budget // price)
            ans += count * 2
            budget -= count * price

        ans += budget // min_price

        return ans
       