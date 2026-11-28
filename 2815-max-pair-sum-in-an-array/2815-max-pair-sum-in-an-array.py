
from typing import List

class Solution:
    def maxSum(self, nums: List[int]) -> int:
        ans = -1
        max_digit = {}

        for num in nums:
            digit = max(map(int, str(num)))

            if digit in max_digit:
                ans = max(ans, num + max_digit[digit])

            max_digit[digit] = max(max_digit.get(digit, 0), num)

        return ans
     