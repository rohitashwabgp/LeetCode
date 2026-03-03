import java.util.Stack;

public class LeetCode20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(' || current == '{' || current == '[') {
                stack.push(current);
            }

            if (current == ')' || current == '}' || current == ']') {
                if (stack.isEmpty()) return false;
                char prev = stack.pop();

                if ((prev == '(' && current != ')') || (prev == '{' && current != '}')
                        || (prev == '[' && current != ']')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
