class Solution:
    def maxLength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0

        for i in range(n):
            prod = 1
            lcm = 1
            gcd = 0

            for j in range(i, n):
                prod *= nums[j]
                gcd = math.gcd(gcd, nums[j])
                lcm = math.lcm(lcm, nums[j])

                if prod == gcd * lcm:
                    ans = max(ans, j - i + 1)

        return ans