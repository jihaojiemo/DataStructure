package com.github.jihaojiemo.queue;

public class MyQueueImpl implements IMyQueue {

    class Node {
        private int data;
        private Node next;
        public Node(int data) {
            this.data = data;
        }
    }

    private Node front;
    private Node rear;
    private int usedSize;

    public MyQueueImpl() {
        this.front = null;
        this.rear = null;
        this.usedSize = 0;
    }

    @Override
    public boolean empty() {
        return this.usedSize == 0;
    }

    @Override
    public int peek() {
        if (empty()) {
            throw new UnsupportedOperationException("队列为空");
        }
        return this.front.data;
    }

    @Override
    public int poll() {
        if (empty()) {
            throw new UnsupportedOperationException("栈为空");
        }
        int oldData = this.front.data;
        this.front = this.front.next;
        this.usedSize--;
        return oldData;
    }

    @Override
    public void add(int item) {

        Node node = new Node(item);

        if (empty()) {
            this.front = node;
            this.rear = node;
        }else {
            this.rear.next = node;
            this.rear = node;
        }
        this.usedSize++;
    }

    @Override
    public int size() {
        return this.usedSize;
    }
}
