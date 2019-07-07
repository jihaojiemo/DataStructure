package com.github.jihaojiemo.queue;

public class TestDemo {

    public static void main(String[] args) {

//        MyQueueImpl myQueue = new MyQueueImpl();
////        myQueue.add(10);
////        myQueue.add(20);
////        myQueue.add(30);
////        System.out.println(myQueue.poll());//10
////        System.out.println(myQueue.peek());//20
////        System.out.println(myQueue.size());//2
////        System.out.println(myQueue.poll());//20
////        System.out.println(myQueue.poll());//30
////        System.out.println(myQueue.size());

        //两个队列实现一个栈，后进先出
        TestMyStack testMyStack = new TestMyStack();
        testMyStack.push(10);
        testMyStack.push(20);
        testMyStack.push(30);
        testMyStack.push(40);
        System.out.println(testMyStack.pop());//40
        testMyStack.push(50);
        System.out.println(testMyStack.pop());//50
        testMyStack.push(60);
        System.out.println(testMyStack.pop());//60
        System.out.println(testMyStack.pop());//30
        System.out.println(testMyStack.top());//20
        System.out.println(testMyStack.pop());//20
        System.out.println(testMyStack.pop());//10

        //最小栈
//        MinStack minStack = new MinStack();
//        minStack.push(-10);
//        minStack.push(1);
//        minStack.push(-30);
//        System.out.println(minStack.getMin());//-30
//        minStack.pop();
//        System.out.println(minStack.getMin());//-10

        System.out.println("============");

        //两个栈实现一个队列，先进先出
        TestMyQueue testMyQueue = new TestMyQueue();
        testMyQueue.push(10);
        testMyQueue.push(20);
        testMyQueue.push(30);
        testMyQueue.push(40);
        System.out.println(testMyQueue.pop());//10
        testMyQueue.push(50);
        System.out.println(testMyQueue.pop());//20
        testMyQueue.push(60);
        System.out.println(testMyQueue.pop());//30
        System.out.println(testMyQueue.pop());//40
        System.out.println(testMyQueue.pop());//50
        System.out.println(testMyQueue.pop());//60

    }
}
