package com.alexander.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

/**
 * 因为是完全二叉树，可以直接使用数组表示该二叉树，数组下标就是编号
 */
public class HeapSort {
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
        Integer[] arr = {6, 8, 7, 9, 5, 4, 1, 2, 3};
        sort(arr, Comparator.comparingInt(a -> a));
        System.out.println(Arrays.toString(arr));

        TestComparator[] testComparators = new TestComparator[8];
        Random random = new Random(new Date().getTime());
        for (int i = 0; i < testComparators.length; i++) {
            testComparators[i] = new TestComparator(18 + random.nextInt(50),
                    (char) (97 + random.nextInt(26))
                            + ""
                            + (char) (97 + random.nextInt(26))
                            + (char) (97 + random.nextInt(26)));
        }
        System.out.println("Before heap sort:");
        System.out.println(Arrays.toString(testComparators));

        System.out.println("Sort by age:");
        sort(testComparators, Comparator.comparingInt(TestComparator::getAge));
        System.out.println(Arrays.toString(testComparators));

        System.out.println("Sort by name:");
        sort(testComparators, Comparator.comparing(TestComparator::getName));
        System.out.println(Arrays.toString(testComparators));
    }

    public static <T> void sort(T[] arr, Comparator<? extends T> c) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length, c);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j, c);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr 堆
     * @param i 待调整结点
     * @param length 堆长度
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> void adjustHeap(T[] arr, int i, int length, Comparator c) {
        //先取出当前元素i
        T temp = arr[i];

        //从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && c.compare(arr[k + 1], arr[k]) > 0) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换），没有直接在数组中交换，
            //实则保留了交换后的位置k，然后再与该位置的左右子树进行调整，直至该次没有交换，本次调整结束
            //一次调整能保证该节点为根的子树是一个大顶堆
            if (c.compare(arr[k], temp) > 0) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }

        //将temp值放到最终的位置
        arr[i] = temp;
    }

    /**
     * 交换数组元素
     *
     * @param arr 待排序数组
     * @param a 待交换元素
     * @param b 待交换元素
     */
    public static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
