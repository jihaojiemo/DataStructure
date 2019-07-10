package com.github.jihaojiemo;

import java.util.Arrays;

/**
 * Description: 堆
 * Author: admin
 * Create: 2019-06-04 21:03
 */
public class TestHeap implements IHeap {

    private int[] elem;
    private int usedSize;
    private static final int DEFAULT_SIZE = 10;

    public TestHeap() {
        this.elem = new int[DEFAULT_SIZE];
        this.usedSize = 0;
    }

    @Override
    public void AdjustDown(int root, int len) {
        int parent = root;
        int child = 2 * parent + 1;//左孩子

        while (child < len) {
            //找左右孩子最大值
            if (child + 1 < len && elem[child] < elem[child + 1]) {//右孩子大（child+1有可能越界）
                ++child;//child下标存放的是左右孩子的最大值
            }
            //左右孩子最大值跟父节点比较
            if (elem[child] > elem[parent]) {
                //如果左右孩子最大值大于父节点则进行交换
                int temp = elem[child];
                elem[child] = elem[parent];
                elem[parent] = temp;

                //parent指向child，child指向左孩子
                //因为不一定只交换一次
                parent = child;
                child = 2 * parent + 1;
            }else {
                break;
            }
        }
    }

    @Override
    public void initHeap(int[] array) {
        //先把数组里面的值全部赋给elem
        for (int i = 0; i < array.length; i++) {
            this.elem[i] = array[i];
            this.usedSize++;
        }
        //然后从最后一棵子树父节点（array.length-1-1）开始向下调整
        //子推父：孩子节点 n，父节点 (n-1)/2
        //父推子：父节点 n，左孩子 2n+1，右孩子 2n+2
        for (int i = (array.length - 1 - 1) / 2; i >= 0; i--) {
            AdjustDown(i, this.usedSize);
        }
    }

    @Override
    public void AdjustUp(int child) {
        int parent = (child - 1) / 2;

        while (child > 0) {
            if (elem[child] > elem[parent]) {
                //如果孩子大于父亲则交换
                int temp = elem[child];
                elem[child] = elem[parent];
                elem[parent] = temp;

                //child指向parent，parent向上走
                child = parent;
                parent = (child - 1) / 2;
            }else {
                break;
            }
        }
    }

    public boolean isFull() {
        return this.usedSize == this.elem.length;
    }

    @Override
    public void pushHeap(int item) {
        if (isFull()) {
            //2倍扩容
            this.elem = Arrays.copyOf(this.elem, 2*this.elem.length);
        }
        this.elem[this.usedSize] = item;
        this.usedSize++;
        //插入之后就不再是大根堆了，向上调整
        AdjustUp(this.usedSize-1);
    }

    public boolean isEmpty() {
        return this.usedSize == 0;
    }

    @Override
    public int popHeap() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("堆为空");
        }
        //堆顶元素和最后一个元素交换
        int oldData = elem[0];
        int temp = elem[0];
        elem[0] = elem[this.usedSize - 1];
        elem[this.usedSize - 1] = temp;

        this.usedSize--;

        //又不是大根堆了，所以要向下调整
        AdjustDown(0, this.usedSize);
        return oldData;
    }

    @Override
    public int getHeapTop() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("堆为空");
        }
        return this.elem[0];
    }

    /**
     * 堆排序：
     *   1.时间复杂度：O(nlog2n)
     *   2.空间复杂度：O(n)
     *   3.稳定性：不稳定
     */
    @Override
    public void heapSort() {
        int end = this.usedSize-1;//要交换的最后一个下标
        while (end > 0) {
            int temp = elem[0];
            elem[0] = elem[end];
            elem[end] = temp;
            //只需要调整0号下标的树
            AdjustDown(0, end);
            end--;
        }
    }

    @Override
    public void show() {
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i] + " ");
        }
        System.out.println();
    }
}
//作业：1亿个数据中找 topK