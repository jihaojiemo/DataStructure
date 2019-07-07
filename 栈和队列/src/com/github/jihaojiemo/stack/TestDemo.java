package com.github.jihaojiemo.stack;

import com.github.jihaojiemo.queue.TestMyQueue;

public class TestDemo {

    public static void main(String[] args) {

        MyStackImpl myStack = new MyStackImpl(10);
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        System.out.println(myStack.size());//4
        System.out.println(myStack.pop());//40
        System.out.println(myStack.peek());//30

    }
}
