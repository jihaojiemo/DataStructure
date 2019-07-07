package com.github.jihaojiemo.queue;

import java.util.Stack;

/**
 * Description: 最小栈
 * Author: Administrator
 * Date: 2019/5/28 0028
 * Time: 11:00
 */
public class MinStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    int i = 0;
    int j = 0;

    /** initialize your data structure here. */
    public MinStack() {

    }

    //两个栈都要入，每次放入元素之前要和栈顶元素比较
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        }else {
            if (x < minStack.peek()) {
                minStack.push(x);
            }
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int data = stack.pop();
            if (data == minStack.peek()) {
                minStack.pop();
            }
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    //返回栈最小元素
    public int getMin() {
        return minStack.peek();
    }
}
