package com.algorithm.example.sort;

import java.util.Arrays;

/**
 * @author:無忌
 * @date:2020/11/2
 * @description:数组排序算法总结
 * https://www.cnblogs.com/onepixel/articles/7674659.html
 */
class P79_Sort {
    public static void bubbleSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        int length = array.length, minIndex;
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j] < array[i]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void insertionSort(int[] array) {
        int length = array.length;
        int preIndex, cur;
        for (int i = 1; i < length; i++) {
            preIndex = i - 1;
            cur = array[i];
            while (preIndex >= 0 && array[preIndex] > cur) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = cur;
        }
    }

    //待复习
    public static void shellSort(int[] array) {
        int length = array.length;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; i++) {
                int j = i;
                int cur = array[i];
                while (j - gap >= 0 && array[j - gap] > cur) {
                    array[j] = array[j - gap];
                    j = j - gap;
                }
                array[j] = cur;
            }
        }
    }

    //待复习 https://www.jianshu.com/p/39dd1d9b491d
    public static void mergeSortWrapper(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int low, int high) {
        //使用递归的方式进行归并排序，所需要的空间复杂度是O（N+logN）
        int mid = (low + high) / 2;
        if (low < high) {
            //递归地对左右两边进行排序
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            //合并
            merge(arr, low, mid, high);
        }
    }

    //merge函数实际上是将两个有序数组合并成一个有序数组
    //因为数组有序，合并很简单，只要维护几个指针就可以了
    private static void merge(int[] arr, int low, int mid, int high) {
        //temp数组用于暂存合并的结果
        int[] temp = new int[high - low + 1];
        //左半边的指针
        int i = low;
        //右半边的指针
        int j = mid + 1;
        //合并后数组的指针
        int k = 0;

        //将记录由小到大地放进temp数组
        for (; i <= mid && j <= high; k++) {
            if (arr[i] < arr[j])
                temp[k] = arr[i++];
            else
                temp[k] = arr[j++];
        }

        //接下来两个while循环是为了将剩余的（比另一边多出来的个数）放到temp数组中
        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= high)
            temp[k++] = arr[j++];

        //将temp数组中的元素写入到待排数组中
        for (int l = 0; l < temp.length; l++)
            arr[low + l] = temp[l];
    }

    //待复习：https://www.jianshu.com/p/6a02a657abcb
    public static void quickSortWrapper(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = divide(arr, start, end);
        quickSort(arr, start, partition - 1);
        quickSort(arr, partition + 1, end);
    }

    private static int divide(int[] arr, int start, int end) {
        int base = arr[end];
        while (start < end) {
            while (start < end && arr[start] <= base) {
                start++;
            }
            if (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                end--;
            }
            while (start < end && arr[end] >= base) {
                end--;
            }
            if (start < end) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
                start++;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 6, 3, 9, 7, 1};
        System.out.println("sort before:" + Arrays.toString(array));
        //bubbleSort(array);
        //selectionSort(array);
        //insertionSort(array);
        shellSort(array);
        //mergeSortWrapper(array);
        //quickSortWrapper(array);
        System.out.println("sort after:" + Arrays.toString(array));
    }
}
