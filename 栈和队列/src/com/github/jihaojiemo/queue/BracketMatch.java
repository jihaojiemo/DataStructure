package com.github.jihaojiemo.queue;

import java.util.Stack;

/**
 * Description: 括号匹配问题
 * Author: Administrator
 * Date: 2019/5/28 0028
 * Time: 11:00
 */
public class BracketMatch {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        int top = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                //左括号入栈
                stack.push(s.charAt(i));
            }else {
                if (top == 0) {
                    System.out.println("右括号多于左括号");
                    return false;
                }else {
                    //得到栈顶元素进行比较
                    char ch = stack.peek();
                    if (ch == '(' && s.charAt(i) == ')'
                    || ch == '[' && s.charAt(i) == ']'
                    || ch == '{' && s.charAt(i) == '}') {
                        stack.pop();
                    }else {
                        System.out.println("左右括号匹配次序出错");
                        return false;
                    }
                }
            }
        }
        if (top > 0) {
            System.out.println("左括号多于右括号");
            return false;
        }
        System.out.println("左右括号匹配成功！");
        return true;
    }
}
