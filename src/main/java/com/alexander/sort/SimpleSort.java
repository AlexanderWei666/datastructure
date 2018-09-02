package com.alexander.sort;

import java.util.Arrays;

public class SimpleSort {
    public static void main(String[] args) {
        int[] a = {56, 4,32, 1, 6, 57, 46, 85, 46, 54};
        System.out.println(Arrays.toString(a));
        bubbleSort(a);
        System.out.println(Arrays.toString(a));

    }
    /**
     * 交换数组元素
     *
     * @param arr 待排序数组
     * @param a 待交换元素
     * @param b 待交换元素
     */
    private static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    /**
     * 冒泡排序，每次遍历所有数字，交换，右边有序
     * @param arr 待排序数组
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if(flag)
                return;
        }
    }

    /**
     * 简单选择排序，每次找到最小的放在队头
     * @param arr 待排序数组
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[min] > arr[j]){
                    min = j;
                }
            }
            if(min != i) {
                swap(arr, min, i);
            }
        }
    }

    /**
     * 插入排序，每次和前面的数比，插入到比他大的数前面
     * @param arr 待排序数组
     */
    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }
}
