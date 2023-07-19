package com.database.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
       // int[] arr = {187,35,289,12};
        int[] arr = new int[100000];

        //随机生成100000个数字放到数组中
        for(int i = 0; i < 100000; i++){
            arr[i] = (int)(Math.random() * 100000);
        }

        //记录排序前的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间:" + date1Str);

        SelectSort(arr);

        //记录排序后的时间
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + date2Str);
    }
    public static void SelectSort(int[] arr){
        int temp = 0;
        int minIndex = 0;

        for (int j = 0; j < arr.length; j++){
            minIndex = j;
            for(int i = j; i < arr.length; i++){
                if(arr[j] > arr[i]){
                    minIndex = i;
                }
            }
            temp = arr[j];
            arr[j] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
