package com.alexander.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        Integer[] arr = {5, 3, 4, 8, 10, 30, 15};
        System.out.println("Before merge sort:");
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println("After merge sort:");
        System.out.println(Arrays.toString(arr));

        String[] arr2 = {"A", "b", "C", "D", "E", "g", "c"};
        System.out.println("Before merge sort:");
        System.out.println(Arrays.toString(arr2));
        mergeSort(arr2);
        System.out.println("After merge sort:");
        System.out.println(Arrays.toString(arr2));

    }

    private static <T extends Comparable> void mergeSort(T[] arr) {
        T[] temp = (T[]) new Comparable[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private static <T extends Comparable> void sort(T[] arr, int left, int right, T[] temp) {
        if (left < right) {
            int mid = (left + right) >>> 1;
            sort(arr, left, mid, temp);// 左边归并排序
            sort(arr, mid + 1, right, temp); // 右边归并排序
            merge(arr, left, mid, right, temp);// 合并有序子数组
        }
    }

    private static <T extends Comparable> void merge(T[] arr, int left, int mid, int right, T[] temp) {
        //左右指针保存排序数组的位置
        int i = left; //左序列指针
        int j = mid + 1; //右序列指针
        int t = 0;//temp的指针
        while (i <= mid && j <= right) {
            if (arr[j].compareTo(arr[i]) > 0) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        while (j <= right) {
            temp[t++] = arr[j++];
        }

        t = 0;

        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }
}
