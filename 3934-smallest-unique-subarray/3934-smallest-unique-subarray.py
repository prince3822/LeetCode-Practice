
class Solution:
    def smallestUniqueSubarray(self, nums: List[int]) -> int:
        n = len(nums)

        MOD1 = 10**9 + 7
        MOD2 = 10**9 + 9
        BASE = 19

        def check(length):
            power1 = pow(BASE, length - 1, MOD1)
            power2 = pow(BASE, length - 1, MOD2)

            h1 = 0
            h2 = 0

            for i in range(length):
                h1 = (h1 * BASE + nums[i]) % MOD1
                h2 = (h2 * BASE + nums[i]) % MOD2

            freq = {h1 * MOD2 + h2: 1}

            for i in range(length, n):
                old = nums[i - length]
                new = nums[i]

                h1 = ((h1 - old * power1) * BASE + new) % MOD1
                h2 = ((h2 - old * power2) * BASE + new) % MOD2

                key = h1 * MOD2 + h2
                freq[key] = freq.get(key, 0) + 1

            return 1 in freq.values()

        left, right = 1, n
        ans = n

        while left <= right:
            mid = (left + right) // 2

            if check(mid):
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans
