package com.bittech.DoubleLinked;

public class DoubleLinkedListImpl implements IDoubleLinked {

    class Node {

        private Node prev;
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node last;//尾节点

    public DoubleLinkedListImpl() {
        this.head = null;
        this.last = null;
    }

    @Override
    public void addFirst(int data) {

        Node node = new Node(data);
        //分情况讨论
        //第一次插入的节点就是最终的尾节点
        if(this.head == null) {
            this.head = node;
            this.last = node;
        }else {
            node.next = this.head;
            node.next.prev = node;
            this.head = node;
        }

    }

    @Override
    public void addLast(int data) {

        Node node = new Node(data);

        if (this.head == null) {
            this.head = node;
            this.last = node;
        }else {
            this.last.next = node;
            node.prev = this.last;
            this.last = node;
        }
    }


    public Node searchIndex(int index) {

        if (index < 0 || index > getLength()) {
            throw new UnsupportedOperationException("index位置不合法");
        }
        int count = 0;
        Node cur = this.head;
        while (count != index) {
            cur = cur.next;
            count++;
        }
        return cur;
    }

    @Override
    public boolean addIndex(int index, int data) {

        if (index == 0) {
            addFirst(data);
            return true;
        }
        if (index == getLength()) {
            addLast(data);
            return true;
        }

        Node node = new Node(data);
        Node cur = searchIndex(index);

        node.next = cur;
        cur.prev.next = node;
        node.prev = cur.prev;
        cur.prev = node;

        return true;
    }

    @Override
    public boolean contains(int key) {

        Node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public int remove(int key) {

        Node cur = this.head;
        while (cur != null){
            int oldData = 0;
            if (cur.data == key) {
                oldData = cur.data;
                //删除的是头结点
                if (cur == this.head) {
                    this.head = this.head.next;
                    this.head.prev = null;
                }else {
                    cur.prev.next = cur.next;
                    if (cur.next != null) {
                        cur.next.prev = cur.prev;
                    }else {
                        //删除的是尾节点
                        this.last = cur.prev;
                    }
                }
                return oldData;
            }
            cur = cur.next;
        }
        return -1;
    }

    @Override
    public void removeAllKey(int key) {

        Node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                if (cur == this.head) {
                    this.head = this.head.next;
                    this.head.prev = null;
                }else {
                    cur.prev.next = cur.next;
                    if (cur.next != null) {
                        cur.next.prev = cur.prev;
                    }else {
                        this.last.prev = null;
                    }
                }
            }
            cur = cur.next;
        }
    }

    @Override
    public int getLength() {

        int count = 0;
        Node cur = this.head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        return count;
    }

    @Override
    public void display() {

        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    @Override
    public void clear() {

        while (this.head != null) {
            Node cur = this.head.next;
            this.head.next = null;
            this.head.prev = null;
            this.head = cur;
        }
        //释放尾节点的引用
        this.last = null;
    }
}
