package com.alexander.sort;

import java.util.Arrays;
import java.util.Comparator;

public class ShellSort {
    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 7, 9, 8, 3, 6};
        sort(arr, Integer::compareTo, 1);
        System.out.println(Arrays.toString(arr));
        Integer[] arr1 = {1, 4, 2, 7, 9, 8, 3, 6};
        sort(arr1, Comparator.comparingInt(a -> a), 0);
        System.out.println(Arrays.toString(arr1));
        String[] arr2 = {"d", "z", "s", "f", "g", "s"};
        sort(arr2, (a, b) -> -a.compareTo(b), 0);
        System.out.println(Arrays.toString(arr2));
    }

    /**
     * 交换数组元素
     *
     * @param arr 待排序数组
     * @param a 待交换元素
     * @param b 待交换元素
     */
    private static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * @param arr 待排序数组
     * @param c 外部比较器
     * @param useSwap 选择使用交换还是移动
     * @param <T> 数组类型
     */
    public static <T> void sort(T[] arr, Comparator<? extends T> c, int useSwap) {
        if (useSwap > 0) {
            shellSort(arr, c);
        } else shellSort1(arr, c);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T> void shellSort(T[] arr, Comparator c) {
        for (int gap = arr.length >>> 1; gap > 0; gap = gap >>> 1) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && c.compare(arr[j - gap], arr[j]) > 0) {
                    swap(arr, j, j - gap);
                    j -= gap;
                }
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T> void shellSort1(T[] arr, Comparator c) {
        for (int gap = arr.length >>> 1; gap > 0; gap = gap >>> 1) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                T temp = arr[j];
                if (c.compare(arr[j - gap], arr[j]) > 0) {
                    while (j - gap >= 0 && c.compare(arr[j - gap], temp) > 0) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}
