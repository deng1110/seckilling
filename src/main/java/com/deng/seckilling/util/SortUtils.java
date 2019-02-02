package com.deng.seckilling.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * 排序的工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/1/29 11:12
 */
public class SortUtils {
    public static void main(String[] args) {

        Integer[] array = makeArray(100000);
        System.out.println(Arrays.toString(array));
        getCurrentTime();
        insertionSort(array, true);
        System.out.println(Arrays.toString(array));
        getCurrentTime();
    }

    /**
     * 插入排序
     * 时间复杂度O(N2)
     *
     * @param array      待排序数组，int类型
     * @param isPositive 是否正序排序,true:从小到大，false:从大到小
     */
    public static void insertionSort(Integer[] array, boolean isPositive) {
        int length = array.length;
        int insertIndex, insertElement;
        if (isPositive) {
            for (int i = 1; i < length; i++) {
                insertElement = array[i];
                insertIndex = i - 1;
                while (insertIndex >= 0 && array[insertIndex] > insertElement) {
                    array[insertIndex + 1] = array[insertIndex];
                    insertIndex--;
                }
                array[insertIndex + 1] = insertElement;
            }
        } else {
            for (int i = 1; i < length; i++) {
                insertElement = array[i];
                insertIndex = i - 1;
                while (insertIndex >= 0 && array[insertIndex] < insertElement) {
                    array[insertIndex + 1] = array[insertIndex];
                    insertIndex--;
                }
                array[insertIndex + 1] = insertElement;
            }
        }
    }

    /**
     * 自动生成一个打乱顺序的指定长度的Integer类型数组
     *
     * @param length 指定的长度
     * @return
     */
    private static Integer[] makeArray(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }

        for (int i = 0; i < length; i++) {
            int r = i + new Random().nextInt(array.length - i);
            Integer temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }
        return array;
    }

    /**
     * 获取当前时间在方法内打印出来(精确到毫秒)
     */
    private static void getCurrentTime() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println("--------------当前时间:" + df.format(new Date()));
    }
}
