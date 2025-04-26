package stack;

import java.util.Stack;

public class encoding {

    public static void main(String[] args) {   //abc
        String s = "2[abc]3[cd]ef";

        Stack<Character> stack = new Stack<>();    //3[a2[

        stack.add(s.charAt(0)); //
        int i = 1;
        String result = "";
        while (i < s.length()) {
            char current = s.charAt(i);
            if (current == ']') {
                String temp = "";
                while (stack.peek() != '[') {
                    temp = stack.pop() + temp;
                }
                stack.pop();
                int frequency = stack.pop() - '0';
                temp = temp + result;
                temp = temp.repeat(frequency);
                result = temp;
            } else {
                stack.add(current);
            }
            i++;
        }

        System.out.println(result);
        System.out.println();
    }

}
