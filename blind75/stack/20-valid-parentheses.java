class Solution {
    /**
    遍历String， 并用stack来存储括号；
    我们可以每次遇到开括号之后，将对应的闭括号入栈， 当字符为闭括号时 我们需要判断此时栈如果为空、
    或者栈顶的元素不是对应的闭括号 就return false；
    最后return stack.isEmpty(); 确保栈中没有其他的元素
     */
     // T: O(N) S:O(N)
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c: s.toCharArray()) {
            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            } else {
                if (c == '{') {
                    stack.push('}');
                } else if (c == '[') {
                    stack.push(']');
                } else {
                    stack.push(')');
                }
            }
        }
        return stack.isEmpty();
    }
}
