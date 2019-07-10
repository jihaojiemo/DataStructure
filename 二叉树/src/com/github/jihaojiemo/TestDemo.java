package com.github.jihaojiemo;

/**
 * Description:
 * Author: admin
 * Create: 2019-06-11 19:48
 */
public class TestDemo {

    public static void main(String[] args) {
        TestHeap testHeap = new TestHeap();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        testHeap.initHeap(array);
        testHeap.show();

        testHeap.pushHeap(11);
        testHeap.show();

        testHeap.popHeap();
        testHeap.show();

        testHeap.heapSort();
        testHeap.show();
    }

    public static void main1(String[] args) {

        TestBinaryTree testBinaryTree = new TestBinaryTree();

        TestBinaryTree.TreeNode root =
                testBinaryTree.createTestTree("ABC##DE#G##F###");

        //前序遍历
        testBinaryTree.binaryTreePrevOrder(root);
        System.out.println();
        testBinaryTree.binaryTreePrevOrderNonR(root);
        System.out.println("================");

        //中序遍历
        testBinaryTree.binaryTreeInOrder(root);
        System.out.println();
        testBinaryTree.binaryTreeInOrderNonR(root);
        System.out.println("================");

        //后序遍历
        testBinaryTree.binaryTreePostOrder(root);
        System.out.println();
        testBinaryTree.binaryTreePostOrderNonR(root);
        System.out.println("================");

        //层序遍历
        testBinaryTree.binaryTreeLevelOrder(root);

        System.out.println(testBinaryTree.getSize(root));
        System.out.println(testBinaryTree.getLeafSize(root));
        System.out.println(testBinaryTree.find(root,'C').value);
        System.out.println(testBinaryTree.getKLevelSize(root,3));
    }
}
