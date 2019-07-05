package com.bittech.Linked;

public class Test {

    //创造两个链表的公共节点
    public static void createCut( MySingleListImpl.Node headA,
                                  MySingleListImpl.Node headB){

        headA.next.next = headB.next.next.next;
    }


    //输入两个链表，找出它们的第一个公共结点
    public static MySingleListImpl.Node getIntersectionNode(MySingleListImpl.Node headA,
                                                            MySingleListImpl.Node headB) {

        //pL始终指向的是较长的链表，pS指向的是较短的链表
        MySingleListImpl.Node pL = headA;
        MySingleListImpl.Node pS = headB;

        int lenA = 0;
        while (pL != null) {
            lenA++;
            pL = pL.next;
        }
        int lenB = 0;
        while (pS != null) {
            lenB++;
            pS = pS.next;
        }

        //此时pL和pS已经移动了
        pL = headA;
        pS = headB;

        int len = lenA - lenB;
        if (len < 0) {
            //说明A短B长
            pL = headB;
            pS = headA;
            len = lenB - lenA;
        }

        for (int i = 0; i < len; i++) {
            pL = pL.next;
        }

        //此时pL和pS在同一起跑线上
        while (pL != null && pS != null && pL != pS) {
            pL = pL.next;
            pS = pS.next;
        }
        if (pL == pS && pL != null && pS != null) {
            return pL;
        }
        return null;
    }


    //将两个有序链表合并为一个新的有序链表并返回
    public static MySingleListImpl.Node mergeTwoLists(MySingleListImpl.Node headA,
                                                      MySingleListImpl.Node headB) {

        //在外部类的外部创建内部类对象
        MySingleListImpl.Node newHead = new MySingleListImpl().new Node(-1);

        MySingleListImpl.Node tmpHead = newHead;

        while(headA != null && headB != null) {
            if(headA.data >= headB.data) {
                tmpHead.next = headB;
                tmpHead = headB;
                headB = headB.next;
            }else {
                tmpHead.next = headA;
                tmpHead = headA;
                headA = headA.next;
            }
        }

        if (headA != null) {
            tmpHead.next = headA;
        }
        if (headB != null) {
            tmpHead.next = headB;
        }
        return newHead.next;
    }


    public static void main(String[] args) {

//        MySingleListImpl list = new MySingleListImpl();
//
//        list.addIndex(0, 1);
//        list.addIndex(1, 2);
//        list.addIndex(2, 3);
//        list.addIndex(3, 3);
//        list.addIndex(4, 2);
//        list.addIndex(5, 1);
//
//        System.out.print("原链表：");
//        list.display();

//        System.out.println("链表的长度：" + list.getLength());
//
//        list.addIndex(3, 88);
//        System.out.print("添加后的链表：");
//        list.display();
//
//        System.out.println("链表是否包含元素99：" + list.contains(99));
//        System.out.println("链表是否包含元素100：" + list.contains(100));
//
//        list.remove(99);
//        System.out.print("删除99之后的链表：");
//        list.display();
//
//        list.removeAllKey(20);
//        System.out.print("删除所有值为20的节点后的链表：");
//        list.display();
//
//        list.clear();
//        System.out.print("清空后的单链表：");
//        list.display();

//        MySingleListImpl.Node newNode = list.reverseList();
//        System.out.print("反转后的链表：");
//        list.show(newNode);
//
//        MySingleListImpl.Node newNode2 = list.reverse();
//        list.show(newNode2);

//        MySingleListImpl.Node middleNode = list.middleNode();
//        System.out.println("中间节点的值为：" + middleNode.getData());
//
//        MySingleListImpl.Node kthToTail = list.findKthToTail(3);
//        System.out.println("倒数第3个节点的值为：" + kthToTail.getData());
//
//        MySingleListImpl.Node node = list.partition(50);
//        System.out.print("分割后的链表为：");
//        list.show(node);
//
//        list.createLoop();
//        //System.out.println("链表是否有环:" + list.hasCycle());
//        MySingleListImpl.Node cur = list.detectCycle();
//        System.out.println("链表入环点是:" + cur.getData());

//        System.out.print("删除重复元素后的链表为：");
////        MySingleListImpl.Node cur = list.deleteDuplication();
////        list.show(cur);

        //System.out.println("链表是否是回文结构：" + list.chkPalindrome());

//        MySingleListImpl list1 = new MySingleListImpl();
//
//        list1.addLast(1);
//        list1.addLast(2);
//        list1.addLast(3);
//        list1.addLast(4);
//        list1.display();
//
//        MySingleListImpl list2 = new MySingleListImpl();
//        list2.addLast(1);
//        list2.addLast(13);
//        list2.addLast(21);
//        list2.addLast(22);
//        list2.addLast(34);
//        list2.addLast(44);
//        list2.addLast(45);
//        list2.display();

//        createCut(list1.head, list2.head);
//
//        System.out.println("===================");
//        list1.display();
//        list2.display();
//        System.out.println("===================");

//        MySingleListImpl.Node cur = getIntersectionNode(list1.head, list2.head);
//
//        System.out.println("两个链表的第一个公共节点是：" + cur.data);

//        MySingleListImpl.Node cur = mergeTwoLists(list1.head, list2.head);
//        System.out.println("合并后的链表为：");
//        list1.show(cur);

        MySingleListImpl list = new MySingleListImpl();
        list.addIndex(0, 1);
        list.addIndex(1, 1);
        list.addIndex(2, 1);
        list.addIndex(3, 3);
        list.addIndex(4, 3);
        list.addIndex(5, 4);
        list.addIndex(6, 5);
        list.addIndex(7, 5);
        list.addIndex(8, 7);
        list.addIndex(9, 9);
        list.addIndex(10, 9);

        System.out.print("原链表：");
        list.display();

//        System.out.print("删除重复元素后的链表为：");
//        MySingleListImpl.Node cur = list.deleteDuplication();
//        list.show(cur);
    }
}
