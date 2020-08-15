package com.deng.seckilling.Algorithm;

/**
 * 二分查找算法
 *
 * @author dengjunbing
 * @version v1.0
 * @since 2020/8/4 11:20
 */
public class BinSearch {

    public static void main(String[] args) {

        int[] arr = new int[]{2, 5, 8, 13, 15, 16, 24};
        System.out.println(binSearch(arr,24));
        System.out.println(binSearch(arr, 0, arr.length - 1, 5));
    }

    /**
     * 二分查找（递归方式）
     *
     * @param arr   待查找数组
     * @param left  查找下标开始位置
     * @param right 查找下标结束位置
     * @param key   待查找的值
     * @return 待查找的值在数组中的脚标位置
     */
    public static int binSearch(int[] arr, int left, int right, int key) {
        if (null == arr || arr.length == 0 || left > right) {
            return -1;
        }
        int mid;
        mid = (right - left) / 2 + left;
        if (arr[mid] > key) {
            return binSearch(arr, left, mid - 1, key);
        } else if (arr[mid] < key) {
            return binSearch(arr, mid + 1, right, key);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找（非递归方式）
     *
     * @param arr 待查找数组
     * @param key 待查找的值
     * @return 待查找的值在数组中的脚标位置
     */
    public static int binSearch(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (arr[mid] > key) {
                right = mid - 1;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
