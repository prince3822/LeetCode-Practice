class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] a = new int[128];
        int l = 0, ans = 0;

        for (int r = 0; r < s.length(); r++) {
            l = Math.max(l, a[s.charAt(r)]);
            a[s.charAt(r)] = r + 1;
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}