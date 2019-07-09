package com.github.jihaojiemo;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * Description: 排序算法
 * Author: admin
 * Date: 2019/5/28
 * Time: 19:13
 */
public class TestSortDemo {

    /**
     * 直接插入排序：
     *   1.时间复杂度：O(n^2)，最好情况下（有序的情况下）O(n)
     *   2.空间复杂度：O(1)
     *   3.稳定性：稳定（没有发生跳跃式交换）
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = 0;
            for (j = i-1; j >= 0; j--) {
                if (array[j] > temp) {
                    array[j + 1] = array[j];
                }else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
    }

    public static void shell(int[] array, int gap) {
        for (int i = gap; i < array.length; i++) {
            int temp = array[i];
            int j = 0;
            for (j = i - gap; j >= 0; j -= gap) {
                if (array[j] > temp) {
                    array[j + gap] = array[j];
                } else {
                    break;
                }
            }
            array[j+gap] = temp;
        }
    }

    /**
     * 希尔排序：直接插入排序的优化，分组的思想
     *   1.时间复杂度：O(n^1.3) ~ O(n^1.5)
     *   2.空间复杂度：O(1)
     *   3.稳定性：不稳定
     */
    public static void shellSort(int[] array) {
        int[] drr = {5, 3, 1};//假设增量序列如此
        //不管是几组，做法都一样
        for (int i = 0; i < drr.length; i++) {
            shell(array, drr[i]);
        }
    }

    /**
     * 选择排序：
     *   1.时间复杂度：O(n^2)
     *   2.空间复杂度：O(1)
     *   3.稳定性：不稳定
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = array[i];
                }
            }
        }
    }

    /**
     * 冒泡排序：
     *   1.时间复杂度：O(n^2)
     *   2.空间复杂度：O(1)
     *   3.稳定性：稳定
     *
     *  冒泡排序的优化：
     *    如果排了几趟之后，发现已经是有序的了，那么后面就不再需要比较了
     *    时间复杂度：最好情况下O(n)
     */
    public static void bubbleSort(int[] array) {
        boolean swap = false;
        for (int i = 0; i < array.length - 1; i++) {//控制趟数
            swap = false; //不知道从哪一趟是不需要交换的
            for (int j = 0; j < array.length - i - 1; j++) {//每趟比的次数
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swap = true; //如果发现交换把swap置为true
                }
            }
            if (!swap) {
                break; //如果一趟走完没有发生交换，则已经有序了
            }
        }
    }

    /**
     * 快速排序：递归法
     *   1.时间复杂度：最好：O(n*logn)   最坏：O(n^2)
     *   2.空间复杂度：最好：O(logn)    最坏：O(n) -> 123456789
     *   3.稳定性：不稳定
     *   待排序序列可以被分割成均匀的两部分时最好，
     *   如果数组已经有序，不能均匀的被分割，快速排序此时沦为冒泡排序
     */
    public static int partion(int[] array, int low, int high) {
        int temp = array[low];
        while (low < high) {
            while (low < high && array[high] > temp) {
                high--;//high有可能会越界
            }
            array[low] = array[high];
            while (low < high && array[low] < temp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }

    public static void insertSort2(int[] array, int start, int end) {
        for (int i = start+1; i <= end; i++) {
            int temp = array[i];
            int j = 0;
            for (j = i-1; j >= start; j--) {
                if (array[j] > temp) {
                    array[j+1] = array[j];
                }else {
                    break;
                }
            }
            array[j+1] = temp;
        }
    }

    public static void quick(int[] array, int start, int end) {

        //假设对start到end这部分序列有16个元素，已经有序则进行直接插入排序
        if (end - start + 1 <= 16) {
            insertSort2(array, start, end);
            return;
            //如果这部分序列用直接插入排序完成了，那么剩下的函数不需要再进行了
        }

        //每次找基准之前先三数取中一下
        medianOfThree(array, start, end);

        //递归了多少次，par就保存了多少次
        int par = partion(array, start, end);//一趟快排完毕
        //递归左边，首先得保证左边有两个数据元素以上
        if (par > start+1) {
            quick(array, start, par-1);
        }
        //递归右边
        if (par < end-1) {
            quick(array, par+1, end);
        }
    }

    public static void quickSort1(int[] array) {
        quick(array, 0, array.length - 1);
    }

    /**
     * 快排的优化：
     *    如何把待排序序列均匀的分割成两部分？
     *
     *    优化1：三数取中法 array[mid] <= array[low] <= array[high]
     *    优化2：在排序过程中，待排序序列会逐渐趋于有序。如果某一段序列已经有序，
     *      再对这一部分序列进行快排，就会变成冒泡排序。所以到达某一个区间后
     *      用直接插入排序
     */
    public static void swap(int[] array, int low, int high) {
        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;
    }

    public static void medianOfThree(int[] array, int low, int high) {
        int mid = (low+high)/2;
        if (array[mid] > array[low]) {
            swap(array, low, mid);
        }
        if (array[low] > array[high]) {
            swap(array, low, high);
        }
    }

    /**
     * 快速排序：非递归法
     *  借助栈!! 首先进行一趟快排，再借助栈
     */
    public static void quickSort2(int[] array) {
        int low = 0;
        int high = array.length-1;
        Stack<Integer> stack = new Stack<>();

        int par = partion(array, low, high);

        if (par > low+1) {
            stack.push(low);
            stack.push(par-1);
        }
        if (par < high-1) {
            stack.push(par+1);
            stack.push(high);
        }

        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();
            par = partion(array, low, high);

            if (par > low+1) {
                stack.push(low);
                stack.push(par-1);
            }
            if (par < high-1) {
                stack.push(par+1);
                stack.push(high);
            }
        }
    }

    /**
     * 归并排序：递归法
     *   1.时间复杂度：O(n*logn)
     *   2.空间复杂度：O(n)
     *   3.稳定性：稳定
     */
    public static void merge1(int[] array, int start, int mid, int end, int[] tempArray) {
        //int[] tempArray = new int[array.length];//不断的创建销毁对象，效率太低
        int tempIndex = start;//设置为start，防止把原来数据覆盖掉
        int i = start;//把start保存下来是为了接下来拷贝

        int s2 = mid + 1;

        //当有两个归并段并且两个归并段都有数据的时候
        while (start <= mid && s2 <= end) {
            if (array[start] < array[s2]) {
                tempArray[tempIndex++] = array[start++];
            }else {
                tempArray[tempIndex++] = array[s2++];
            }
        }

        //第一个归并段有数据，说明第二个归并段已经走完
        while (start <= end) {
            tempArray[tempIndex++] = array[start++];
        }

        //第二个归并段有数据，说明第一个归并段已经走完
        while (s2 <= end) {
            tempArray[tempIndex++] = array[s2++];
        }

        //把排好序的数据全部从tempArray拷贝到Array中
        while (i <= end) {
            array[i] = tempArray[i++];
        }
    }

    public static void mergeSort1(int[] array, int start, int end, int[] tempArray) {
        if (start >= end) {
            return;
        }
        int mid = (start+end)/2;
        //递归左边
        mergeSort1(array, start, mid, tempArray);
        //递归右边
        mergeSort1(array, mid+1, end, tempArray);

        //单个有序之后进行二路合并
        merge1(array, start, mid, end, tempArray);
    }

    /**
     * 归并排序：非递归法
     */
    public static void merge2(int[] array, int gap) {//gap是每组里面的数据个数
        int[] tempArray = new int[array.length];
        int i = 0;//表示的是tempArray的下标

        int start1 = 0;
        int end1 = start1+gap-1;
        int start2 = end1+1;
        int end2 = start2+gap-1 <= array.length-1 ? start2+gap-1 : array.length-1; //end2可能会数组越界

        //保证有两个归并段
        //比较的时候肯定有一个归并段先没有数据
        while (start2 < array.length) {
            //两个归并段都有数据
            while (start1 <= end1 && start2 <= end2) {
                if (array[start1] < array[start2]) {
                    tempArray[i++] = array[start1++];
                }else {
                    tempArray[i++] = array[start2++];
                }
            }
            //第一个归并段有数据
            while (start1 <= end1) {
                tempArray[i++] = array[start1++];
            }
            //第二个归并段有数据
            while (start2 <= end2) {
                tempArray[i++] = array[start2++];
            }
            //证明一次二路归并已经完成
            start1 = end2 + 1;
            end1 = start1 + gap - 1;
            start2 = end1 + 1;
            end2 = start2+gap-1 <= array.length-1 ? start2+gap-1 : array.length-1;
        }

        //只有一个归并段
        while (start1 < array.length) {
            tempArray[i++] = array[start1++];
        }

        //把数据从tempArray拷贝到原始数组
        for (int j = 0; j < tempArray.length; j++) {
            array[i] = tempArray[j];
        }
    }

    public static void mergeSort2(int[] array) {
        //每个归并段里的数据的个数是以2倍形式增长
        //最少是1组，最多是array.length组
        for (int i = 1; i < array.length; i *= 2) {
            merge2(array, i);
        }
    }


    public static void main(String[] args) {
        //int[] array = {10, 3, 2, 78, 45, 11, 33};
        int[] array = new int[100000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            //array[i] = i;
            // [1, 100001) 每个数字的范围是 1~100000
            array[i] = random.nextInt(100000) + 1;
        }

        int[] tempArray = new int[array.length];

        System.out.println(Arrays.toString(array));
        mergeSort1(array, 0, array.length-1, tempArray);
        System.out.println(Arrays.toString(array));
    }
}
