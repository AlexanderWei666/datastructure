package com.alexander.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class MergeSort {

    public static class TestComparator {
        private int age;
        private String name;

        public TestComparator(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "(" + this.age + ", " + this.name + ")";
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 3, 4, 8, 10, 30, 15};
        System.out.println("Before merge sort:");
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, (a, b) -> b - a);//反向排序很方便
        System.out.println("After merge sort:");
        System.out.println(Arrays.toString(arr));

        String[] arr2 = {"A", "b", "C", "D", "E", "g", "c"};
        System.out.println("Before merge sort:");
        System.out.println(Arrays.toString(arr2));
        mergeSort(arr2, (a, b) -> -a.compareTo(b));//很方便进行反向排序
        System.out.println("After merge sort:");
        System.out.println(Arrays.toString(arr2));

        TestComparator[] testComparators = new TestComparator[8];
        Random random = new Random(new Date().getTime());
        for (int i = 0; i < testComparators.length; i++) {
            testComparators[i] = new TestComparator(18 + random.nextInt(50),
                    (char) (97 + random.nextInt(26))
                            + ""
                            + (char) (97 + random.nextInt(26))
                            + (char) (97 + random.nextInt(26)));
        }
        System.out.println("Before merge sort:");
        System.out.println(Arrays.toString(testComparators));


        System.out.println("Sort by age:");
        mergeSort(testComparators, Comparator.comparingInt(TestComparator::getAge));
        System.out.println(Arrays.toString(testComparators));

        System.out.println("Sort by name:");
        mergeSort(testComparators, Comparator.comparing(TestComparator::getName));
        System.out.println(Arrays.toString(testComparators));

    }

    private static <T> void mergeSort(T[] arr, Comparator<? super T> c) {
        T[] temp = arr.clone();
        sort(arr, 0, arr.length - 1, temp, c);
    }

    private static <T> void sort(T[] arr, int left, int right, T[] temp, Comparator c) {
        if (left < right) {
            int mid = (left + right) >>> 1;
            sort(arr, left, mid, temp, c);// 左边归并排序
            sort(arr, mid + 1, right, temp, c); // 右边归并排序
            merge(arr, left, mid, right, temp, c);// 合并有序子数组
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T> void merge(T[] arr, int left, int mid, int right, T[] temp, Comparator c) {
        //左右指针保存排序数组的位置
        int i = left; //左序列指针
        int j = mid + 1; //右序列指针
        int t = 0;//temp的指针
        while (i <= mid && j <= right) {
            if (c.compare(arr[i], arr[j]) < 0) {
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
