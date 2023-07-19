package com.database.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
       /* int[] arr = {-9,78,0,23,-567,70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/
        int[] arr = new int[100000];

        //随机生成100000个数字放到数组中
        for(int i = 0; i < 100000; i++){
            arr[i] = (int)(Math.random() * 100000);
        }

        //记录排序前的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间:" + date1Str);

        quickSort(arr,0, arr.length - 1);

        //记录排序后的时间
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + date2Str);
    }

    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;//序列长度小于等于1的时候直接结束
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];//基准位是第一个位置

        while (i < j) {
            //先看右边，依次往左递减，找到一个小于基准位的数的下标
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增，找到第一个大于基准位的数的下标
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //将两个数进行交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i下标的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }
}
