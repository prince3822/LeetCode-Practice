class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                StringBuilder temp = new StringBuilder();

                while (st.peek() != '(')
                    temp.append(st.pop());

                st.pop(); // remove '('

                for (char x : temp.toString().toCharArray())
                    st.push(x);
            } else {
                st.push(c);
            }
        }

        StringBuilder ans = new StringBuilder();

        while (!st.isEmpty())
            ans.append(st.pop());

        return ans.reverse().toString();
    }
}