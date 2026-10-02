class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        solve("", 0, 0, n, ans);
        return ans;
    }
    void solve(String s, int open, int close, int n, List<String> ans) {
        if (s.length() == 2 * n) {
            ans.add(s);
            return;
        }
        if (open < n)
            solve(s + "(", open + 1, close, n, ans);
        if (close < open)
            solve(s + ")", open, close + 1, n, ans);
    }
}