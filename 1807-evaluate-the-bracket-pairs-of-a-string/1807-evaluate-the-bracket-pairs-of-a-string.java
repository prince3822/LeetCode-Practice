class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String,String> map = new HashMap<>();
        for (List<String> x : knowledge) map.put(x.get(0), x.get(1));

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length();) {
            if (s.charAt(i) == '(') {
                int j = s.indexOf(')', i);
                String key = s.substring(i + 1, j);
                ans.append(map.getOrDefault(key, "?"));
                i = j + 1;
            } else {
                ans.append(s.charAt(i++));
            }
        }
        return ans.toString();
    }
}