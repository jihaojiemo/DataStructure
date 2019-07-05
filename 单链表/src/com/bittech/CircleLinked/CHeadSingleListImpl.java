package com.bittech.CircleLinked;

public class CHeadSingleListImpl implements ICLinked {

    class Node {
        private int data;
        private Node next;

        public Node() {
            this.data = -1;
            this.next = null;
        }

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public CHeadSingleListImpl() {
        this.head = new Node();
        this.head.next = this.head;
    }

    @Override
    public void addFirst(int data) {
        Node node = new Node(data);
        node.next = this.head.next;
        this.head.next = node;
    }

    @Override
    public void addLast(int data) {
        Node node = new Node(data);
        Node cur = this.head;
        while (cur.next != this.head) {
            cur = cur.next;
        }

        node.next = cur.next;
        cur.next = node;
    }

    @Override
    public boolean addIndex(int index, int data) {

        Node cur = this.head;

        if(index < 0 || index > getLength()) {
            return false;
        }

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        //cur就是index位置的前驱
        Node node = new Node(data);
        node.next = cur.next;
        cur.next = node;

        return true;
    }

    @Override
    public boolean contains(int key) {

        Node cur = this.head.next;
        while (cur != this.head) {
            if (cur.data != key) {
                cur = cur.next;
            }
            return true;
        }
        return false;
    }


    private Node searchPre(int key) {

        Node pre = this.head;
        while (pre.next != this.head) {
            if (pre.next.data != key) {
                pre = pre.next;
            }
            return pre;
        }
        return null;
    }


    @Override
    public int remove(int key) {

        Node pre = searchPre(key);
        int oldData = 0;

        if(pre == null) {
            throw new Error();
        }

        oldData = pre.next.data;
        pre.next = pre.next.next;

        return oldData;
    }

    @Override
    public void removeAllKey(int key) {

        if (this.head == null || this.head.next == this.head) {
            return;
        }
        Node pre = this.head;
        Node cur = this.head.next;

        while (cur != this.head) {
            if (cur.data == key) {
                pre.next = cur.next;
                cur = cur.next;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
    }

    @Override
    public int getLength() {

        //头结点不算
        Node cur = this.head.next;
        int count = 0;
        while (cur != this.head) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    @Override
    public void display() {

        Node cur = this.head.next;
        while (cur != this.head) {
            System.out.print(cur.data + " ");
        }
        System.out.println();
    }

    @Override
    public void clear() {

        while (this.head.next != this.head) {
            Node cur = this.head.next;
            this.head.next = cur.next;
        }
        this.head = null;
    }
}
