package com.bittech.LinkedList;

public class MyLinkedList {

    public static class Node {
        private int value;//保存的是有效数据
        private Node next;//保存的是下一个节点的地址

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    //如果一个节点都没有，head == null
    private Node head;//保存的是链表中第一个节点的引用（可以找到一条链表）

    public MyLinkedList() {
        this.head = null;
    }

    //头插 O(1)
    public void pushFront(int item) {
        Node node = new Node(item);
        //链表为空
        if (this.head == null) {
            this.head = node;
        }
        //链表不为空
        node.next = this.head;
        this.head = node;
    }

    //头删 O(1)
    public void popFront() {
        //链表为空没法删除
        if (this.head == null) {
            throw new Error();
        }
        //链表不为空
        this.head = this.head.next;
    }


    //找最后一个节点的过程 O(n)
    public Node getLast() {
        Node cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    //尾插 O(n)
    public void pushBack(int item) {
        Node node = new Node(item);
        //链表为空（和头插无区别）
        if (this.head == null) {
            this.head = node;
        } else {
            Node last = getLast();
            last.next = node;
        }
    }

    //找倒数第二个节点的过程 O(n)
    private Node getLastLast() {
        Node cur = this.head;
        while (cur.next.next != null) {
            cur = cur.next;
        }
        return cur;
    }


    //尾删 O(n)
    //需要把倒数第二个节点的next置为null（链表中至少要有2个节点）
    public void popBack() {
        //没有节点的情况
        if(this.head == null) {
            throw new Error();
        }
        //只有一个节点的情况
        if(this.head.next == null) {
            this.head = null;
        }

        Node lastLast = getLastLast();
        lastLast.next = null;
    }

    //遍历每一个节点
    public void display() {
        Node cur = this.head;
        while(cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }

    }


    public Node removeElements(int val) {

        //结果链表
        Node result = null;
        //记录结果链表的最后一个节点
        Node last = null;
        Node cur = head;

        //遍历整个原链表
        while(cur != null) {
            Node next = cur.next;

            if(cur.value != val) {
                cur.next = null;
                //把cur节点尾插到result链表中（分情况讨论）
                if(result == null) {
                    result = cur;
                }else {
                    last.next = cur;
                }
                last = cur;
            }
            cur = next;
        }
        return result;
    }

    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(6);
        list.pushFront(3);
        list.pushFront(4);
        list.pushFront(5);
        list.pushFront(6);
        System.out.println(list.removeElements(6));
    }
}
