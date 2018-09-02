package com.alexander.sort;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSort {

    public static void main(String[] args) {
        Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        quickSort(arr, Integer::compareTo);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    public static <T> void quickSort(T[] arr, Comparator<? extends T> c) {
        int left = 0;
        int right = arr.length - 1;
        sort(arr, left, right, c);
    }

    @SuppressWarnings({"rawtype", "unchecked"})
    private static <T> void sort(T[] arr, int left, int right, Comparator c) {
        if (left < right) {//递归结束条件left>=right,即每个数的左边<=中间<=右边
            //获取枢纽值，并将其放在当前待处理序列末尾
            dealPivot(arr, left, right, c);
            //枢纽值被放在序列末尾
            int pivot = right - 1;
            //左指针
            int i = left;
            //右指针
            int j = right - 1;
            while (true) {
                while (c.compare(arr[++i], arr[pivot]) < 0) ;//从左边找第一个不小于arr[pivot]的数
                while (j > left && c.compare(arr[--j], arr[pivot]) > 0) ;//从右边找第一个不大于arr[pivot]的数
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    //指针碰撞后，说明该位置左边的数都比arr[pivot]小，包含该数和右边的都比arr[pivot]大
                    break;
                }
            }
            if (i < right) {//因为可能左边的都比arr[pivot]小，这里i可能等于pivot
                swap(arr, i, right - 1);
            }
            sort(arr, left, i - 1, c);
            sort(arr, i + 1, right, c);
        }

    }

    /**
     * 交换数组元素
     *
     * @param arr 待排序数组
     * @param a   待交换元素
     * @param b   待交换元素
     */
    private static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 处理枢纽值
     *
     * @param arr   待处理数组
     * @param left  左边指针
     * @param right 右边指针
     */
    @SuppressWarnings({"rawtype", "unchecked"})
    private static <T> void dealPivot(T[] arr, int left, int right, Comparator c) {
        int mid = (left + right) / 2;
        if (c.compare(arr[left], arr[mid]) > 0) {
            swap(arr, left, mid);
        }
        if (c.compare(arr[left], arr[right]) > 0) {
            swap(arr, left, right);
        }
        if (c.compare(arr[mid], arr[right]) > 0) {
            swap(arr, right, mid);
        }
        swap(arr, right - 1, mid);
    }
}