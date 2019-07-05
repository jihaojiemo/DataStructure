package com.bittech.DoubleLinked;

/**
 * Author: jhj
 * Description:
 * Date: 2019-05-23 10:00:28
 */
public class Test {

    public static void main(String[] args) {

        DoubleLinkedListImpl list = new DoubleLinkedListImpl();

        list.addFirst(10);
        list.addLast(20);
        list.addFirst(30);
        list.addFirst(40);
        list.addLast(50);
        list.addLast(10);

        System.out.print("原链表：");
        list.display(); //40 30 10 20 50 10

        System.out.println("链表的长度：" + list.getLength());

        list.addIndex(3, 88);
        System.out.print("添加后的链表：");
        list.display();//40 30 10 88 20 50 10

        System.out.println("链表是否包含元素88：" + list.contains(88));
        System.out.println("链表是否包含元素100：" + list.contains(100));

        list.remove(88);
        System.out.print("删除88之后的链表：");
        list.display();

        list.removeAllKey(10);
        System.out.print("删除所有值为10的节点后的链表：");
        list.display();

        list.clear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("清空后的单链表：");
        list.display();
    }
}
