package com.github.jihaojiemo.stack;

public class MyStackImpl implements IMyStack {

    private int[] elem;//存放数据元素的数组
    private int top;//当前可以存放数据元素的下标
    private int usedSize;

    public MyStackImpl(int size) {
        this.elem = new int[size];
        this.top = 0;
        this.usedSize = 0;
    }

    public boolean isFull() {
        return this.top == this.elem.length;
    }

    @Override
    public boolean empty() {
        return this.usedSize == 0;
    }

    @Override
    public void push(int item) {
        if (isFull()) {
            throw new UnsupportedOperationException("栈已满");
        }
        this.elem[this.top++] = item;
        this.usedSize++;
    }

    @Override
    public int pop() {
        if (empty()) {
            throw new UnsupportedOperationException("栈为空");
        }
        this.top--;
        this.usedSize--;
        return this.elem[this.top];
    }

    @Override
    public int peek() {
        if (empty()) {
            throw new UnsupportedOperationException("栈为空");
        }
        return this.elem[this.top-1];
    }

    @Override
    public int size() {
        return this.usedSize;
    }
}
