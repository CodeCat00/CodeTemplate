package template;

import java.util.Stack;

public class StackTemplate {

    /**
     * 逆波兰表达式
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(stack.pop() - stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    stack.push(stack.pop() / stack.pop());
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    /**
     * 有效括号
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;
                default:
                    if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 最大矩形
     */
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;

        Stack<Integer> stack = new Stack<>();
        int max = 0;

        for (int i = 0; i <= heights.length; i++) {
            int cur = 0;
            if (i < heights.length) {
                cur = heights[i];
            }

            while (!stack.isEmpty() && cur <= heights[stack.peek()]) {
                int index = stack.pop();
                int h = heights[index];
                int w = i;
                if (!stack.isEmpty()) {
                    int peek = stack.peek();
                    w = i - peek - 1;
                }
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }

        return max;
    }

    /**
     * 实现队列
     */
    static class MyQueue {

        private final Stack<Integer> stack1 = new Stack<>();
        private final Stack<Integer> stack2 = new Stack<>();

        /** Initialize your data structure here. */
        public MyQueue() {

        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            while (! stack2.isEmpty()) {
                int val = stack2.pop();
                stack1.push(val);
            }
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            while (! stack1.isEmpty()) {
                int val = stack1.pop();
                stack2.push(val);
            }
            if (stack2.isEmpty()) return -1;
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            while (! stack1.isEmpty()) {
                int val = stack1.pop();
                stack2.push(val);
            }
            if (stack2.isEmpty()) return -1;
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}
