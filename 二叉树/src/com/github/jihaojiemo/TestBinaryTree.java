package com.github.jihaojiemo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Description: 二叉树
 * Author: admin
 * Create: 2019-06-06 20:09
 */
public class TestBinaryTree {

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public int i = 0;
    //根据字符串创建二叉树
    public TreeNode createTestTree(String s) {
        TreeNode root = null;
        if (s.charAt(i) != '#') {
            root = new TreeNode(s.charAt(i));
            i++;
            root.left = createTestTree(s);
            root.right = createTestTree(s);
        }else {
            i++;
        }
        return root;
    }

    //二叉树的前序遍历（根左右）
    public void binaryTreePrevOrder(TreeNode root) {
        if (root == null) {//root一直在发生改变
            return;
        }
        System.out.print(root.value + " ");
        binaryTreePrevOrder(root.left);
        binaryTreePrevOrder(root.right);
    }

    //二叉树的中序遍历（左根右）
    public void binaryTreeInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        binaryTreeInOrder(root.left);
        System.out.print(root.value + " ");
        binaryTreeInOrder(root.right);
    }

    //二叉树的后序遍历（左右根）
    public void binaryTreePostOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        binaryTreePostOrder(root.left);
        binaryTreePostOrder(root.right);
        System.out.print(root.value + " ");
    }

    //结点个数
    public int getSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getSize(root.left) + getSize(root.right) + 1;
    }

    //叶子节点的个数
    public int getLeafSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return getLeafSize(root.left) + getLeafSize(root.right);
    }

    //第k层节点个数
    public int getKLevelSize(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }else if (k == 1) {
            return 1;
        }
        return getKLevelSize(root.left, k-1) + getKLevelSize(root.right, k-1);
    }

    //查找，依次在二叉树的根、左子树、右子树中查找value，如果找到，返回结点，否则返回null
    public TreeNode find(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.value == value) {
            return root;
        }
        TreeNode ret = find(root.left, value);
        if (ret != null) {
            return ret;
        }
        ret = find(root.right, value);
        if (ret != null) {
            return ret;
        }
        return null;
    }

    //求二叉树的高度
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }else {
            int leftHeight = height(root.left);
            int rightHead = height(root.right);
            return (leftHeight > rightHead ? leftHeight : rightHead) + 1;
        }
    }

    //二叉树的前序遍历非递归
    public void binaryTreePrevOrderNonR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode top = null;

        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                System.out.print(cur.value + " ");
                cur = cur.left;
            }
            top = stack.pop();
            cur = top.right;
        }
        System.out.println();
    }

    //二叉树的中序遍历非递归
    public void binaryTreeInOrderNonR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode top = null;

        while (cur != null && !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            top = stack.pop();
            System.out.print(top.value + " ");
            cur = top.right;
        }
        System.out.println();
    }

    //二叉树的后序遍历非递归
    public void binaryTreePostOrderNonR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;

        while (cur != null && !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == prev) {
                stack.pop();
                System.out.print(cur.value + " ");
                prev = cur;//记录的是该节点是否已经被打印过
                cur = null;
            }else {
                cur = cur.right;
            }
        }
        System.out.println();
    }

    //二叉树的层序遍历
    public void binaryTreeLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        System.out.println();
    }

    //判断一棵树是否是完全二叉树，返回0代表是完全二叉树
    public int binaryTreeComplete(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top != null) {
                queue.offer(root.left);
                queue.offer(root.right);
            }else {
                break;
            }
        }
        while (!queue.isEmpty()) {
            if (queue.peek() == null) {
                queue.poll();
            }else {
                return -1;
            }
        }
        return 0;
    }

    //检查两棵树是否相同
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if (p.value != q.value) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //另一棵树的子树
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }
        if (isSameTree(s, t)) {
            return true;
        }else if (isSubtree(s.left, t)) {
            return true;
        } else if (isSubtree(s.right, t)) {
            return true;
        }else {
            return false;
        }
    }

    //判断一颗二叉树是否是平衡二叉树  O(n^2)
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            return Math.abs(leftHeight - rightHeight) < 2
                    && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    //对称二叉树
    public boolean isSymmetricChild(TreeNode leftTree, TreeNode rightTree) {
        if ((leftTree == null && rightTree != null)
                || (leftTree != null && rightTree == null)) {
            return false;
        }
        if (leftTree == null && rightTree == null) {
            return true;
        }
        return leftTree.value == rightTree.value
                && isSymmetricChild(leftTree.left, rightTree.right)
                && isSymmetricChild(leftTree.right, rightTree.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricChild(root.left, root.right);
    }

    //根据二叉树创建字符串
    public void tree2strChild(TreeNode t, StringBuilder sb) {
        if (t == null) {
            return;
        }
        sb.append(t.value);

        if (t.left != null) {
            sb.append("(");
            tree2strChild(t.left, sb);
            sb.append(")");
        }else {
            if (t.right == null) {
                return;
            }else {
                sb.append("()");
            }
        }

        if (t.right == null) {
            return;
        }else {
            sb.append("(");
            tree2strChild(t.right, sb);
            sb.append(")");
        }
    }
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        tree2strChild(t, sb);
        return sb.toString();
    }

    //给定一个二叉树，找到该树中两个指定节点的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        //最先找到的节点为最近公共祖先
        if (root == p || root == q) {
            return root;
        }
        TreeNode leftTree = lowestCommonAncestor(root.left, p, q);
        TreeNode rightTree = lowestCommonAncestor(root.right, p, q);
        if (leftTree != null && rightTree != null) {
            return root;
        } else if (leftTree != null) {
            return leftTree;
        } else if (rightTree != null) {
            return rightTree;
        }
        return null;
    }

    //二叉树搜索树（中序遍历是有序的）转换成排序双向链表
    public TreeNode Convert(TreeNode pRootOfTree) {
        ConvertChild(pRootOfTree);
        TreeNode head = pRootOfTree;
        while (head != null && head.left != null) {
            head = head.left;
        }
        return head;
    }
    TreeNode prev = null;//保存了前驱信息
    public void ConvertChild(TreeNode pCur) {
        if (pCur == null) {
            return;
        }
        ConvertChild(pCur.left);
        pCur.left = prev;
        if (prev != null) {
            prev.right = pCur;
        }
        prev = pCur;
        ConvertChild(pCur.right);
    }

    //根据一棵树的前序遍历与中序遍历构造二叉树
    public int preindex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        int preindex = 0;
        return build(preorder, inorder, 0, inorder.length - 1);
    }
    public TreeNode build(int[] preorder, int[] inorder, int inbegin, int inend) {
        if (inbegin > inend) {
            return null;
        }
        //根据前序遍历找到根节点的下标
        TreeNode root = new TreeNode(preorder[preindex]);
        //在中序遍历里面找到根节点的下标
        int rootIndex = indexOfInorder(inorder, preorder[preindex], inbegin, inend);
        preindex++;
        root.left = build(preorder, inorder, inbegin, rootIndex - 1);
        root.right = build(preorder, inorder, rootIndex + 1, inend);
        return root;
    }
    public int indexOfInorder(int[] inorder, int val, int inbegin, int inend) {
        for (int i = inbegin; i < inend; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return 0;
    }
}
