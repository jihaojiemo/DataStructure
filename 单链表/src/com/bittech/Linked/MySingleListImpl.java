package com.bittech.Linked;

public class MySingleListImpl implements ILinked {

    class Node {

        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public int getData() {
            return this.data;
        }
    }

    public Node head;
    public MySingleListImpl() {
        this.head = null;
    }

    @Override
    public void addFirst(int data) {

        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
        }else {
            node.next = this.head;
            this.head = node;
        }
    }

    @Override
    public void addLast(int data) {

        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
        }else {
            Node cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }


//    public Node searchIndex(int index) {
//
//        if (index < 0 || index > getLength()) {
//            throw new UnsupportedOperationException();
//        }
//
//        int i = 0;
//        Node cur = this.head;
//        while (i < index-1) {
//            cur = cur.next;
//            i++;
//        }
//        return cur;
//    }

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
        Node cur = this.head;
        for(int i = 0; i < index-1; i++) {
            cur = cur.next;
        }
        Node node = new Node(data);
        node.next = cur.next;
        cur.next = node;
        return true;
//        if (index == 0) {
//            addFirst(data);
//            return true;
//        }
//        if (index == getLength()) {
//            addLast(data);
//            return true;
//        }
//        Node node = new Node(data);
//        Node pre = searchIndex(index);
//        node.next = pre.next;
//        pre.next = node;
//        return true;
    }

    @Override
    public boolean contains(int key) {

        if (this.head == null) {
            return false;
        }

        Node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public Node searchPre(int key) {

        Node cur = this.head;
        while (cur.next != null) {
            if (cur.next.data == key) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public int remove(int key) {

        if (this.head == null) {
            throw new UnsupportedOperationException("单链表为空，不支持删除");
        }

        int oldData = 0;

        Node pre = searchPre(key);

        if (pre == null) {
            throw new UnsupportedOperationException("链表中没有该节点的前驱");
        }

        oldData = pre.next.data;
        pre.next = pre.next.next;

        if (this.head.data == key) {
            oldData = this.head.data;
            this.head = this.head.next;
            return oldData;
        }

        return oldData;
    }

    @Override
    public void removeAllKey(int key) {

        if (this.head == null) {
            throw new UnsupportedOperationException("单链表为空，不支持删除");
        }

        Node pre = this.head;
        Node cur = this.head.next;

        while (cur != null) {
            if (cur.data == key) {
                pre.next = cur.next;
                cur = cur.next;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }

        if (this.head.data == key) {
            this.head = this.head.next;
        }
    }

    @Override
    public int getLength() {

        if (this.head == null) {
            return 0;
        }

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

        Node cur = this.head;
        while (cur.next != null) {
            this.head = cur.next;
            cur = cur.next;
        }
        this.head = null;
    }


    //反转一个单链表
    public Node reverseList() {

        Node reverseHead = null;
        Node prev = null;
        Node cur = this.head;

        while (cur != null) {
            Node curNext = cur.next;
            if (curNext == null) {
                reverseHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return reverseHead;
    }


    //打印翻转后的链表
    public void show(Node newHead) {

        Node cur = newHead;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }


    //返回链表的中间结点，如果有两个中间结点，则返回第二个中间结点
    public Node middleNode() {

        Node cur = this.head;
        for (int i = 0; i < getLength()/2; i++) {
            cur = cur.next;
        }
        return cur;
    }


    //输出该链表中倒数第k个结点
    public Node findKthToTail(int k) {

        if (this.head == null || k <= 0) {
            return null;
        }

        Node fast = this.head;
        Node slow = this.head;

        //先让快引用走k-1步
        for (int i = 0; i < k-1; i++) {
            if (fast.next != null) {//类似于k>getLength()的情况
                fast = fast.next;
            }else {
                System.out.println("没有这个节点");
                return null;
            }
        }
        //然后两个引用一起走
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    //以给定值x将链表分为两部分，所有小于x的节点排在大于或者等于x的节点之前
    public Node partition(int x) {

        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;
        Node pHead = this.head;
        while (pHead != null) {
            Node pHeadNext = pHead.next;
            pHead.next = null;
            if (pHead.data < x) {
                //前一部分是空链表
                if (beforeStart == null) {
                    beforeStart = pHead;
                    beforeEnd = pHead;
                }else {
                    beforeEnd.next = pHead;
                    beforeEnd = pHead;
                }
            }else {
                if (afterStart == null) {
                    afterStart = pHead;
                    afterEnd = pHead;
                }else {
                    afterEnd.next = pHead;
                    afterEnd = pHead;
                }
            }
            pHead = pHeadNext;
        }

        if (beforeStart == null) {
            return afterStart;
        }
        beforeEnd.next = afterStart;
        return beforeStart;
    }

    //创建一个有环的链表
    public void createLoop() {

        Node cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = this.head.next.next;
    }

    //判断链表是否有环
    public boolean hasCycle() {

        Node fast = this.head;
        Node slow = this.head;

        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    //寻找环的入口点
    public Node detectCycle() {

        Node fast = this.head;
        Node slow = this.head;

        //循环退出两种情况:
        //1.fast.next == null(找到单链表结尾也没有找到环); 2.fast == slow(有环)
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
               break;
            }
        }

        //链表没有环的情况
        if (fast.next == null) {
            return null;
        }

        //链表有环
        slow = this.head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    //在一个排序的链表中，删除该链表中重复的结点，重复的结点不保留，返回链表头指针
    //重复的节点一定是连续的
    public Node deleteDuplication() {

        //虚拟节点
        Node node = new Node(-1);
        //让tmpHead移动，node作为头指针是要被返回的
        Node tmpHead = node;
        Node cur = this.head;
        while (cur != null) {
            if (cur.next != null && cur.data == cur.next.data) {
                while (cur.next != null && cur.data == cur.next.data) {
                    cur = cur.next;
                }
                cur = cur.next;
                //cur指向的这个节点和前面的不同
                //?????
                tmpHead.next = cur;
            }else {
                //确定是不重复的节点，串起来就行
                tmpHead.next = cur;
                tmpHead = cur;
                cur = cur.next;
            }
        }
        return node.next;
    }

    //链表的回文结构
    public boolean chkPalindrome() {

        Node fast = this.head;
        Node slow = this.head;

        while (fast != null && fast.next != null) {
            //slow指向的是链表的中间位置
            fast = fast.next.next;
            slow = slow.next;
        }

        //把后半部分全部进行反转
        Node p = slow.next;//要进行反转的节点
        Node pNext = p.next;
        while (p != null) {
            p.next = slow;
            slow = p;
            p = pNext;
            if (p != null) {
                pNext = p.next;
            }
        }

        while (this.head != slow) {
            if (this.head.data != slow.data) {
                return false;
            }
            if (this.head.next == slow) {
                return true;
            }
            this.head = this.head.next;
            slow = slow.next;
        }

        return true;
    }
}


