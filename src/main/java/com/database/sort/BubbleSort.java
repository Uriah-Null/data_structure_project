package com.database.sort;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {3, 9, -1, 10, 20};
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

        bubbleSort(arr);

        //记录排序后的时间
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + date2Str);
    }

    public static void bubbleSort(int[] arr){
        int temp = 0;//defined a temporary variable
        boolean flag = false;//defined a boolean variable to sign exchange or not

        for(int j = arr.length; j > 1; j--){
            for(int i = 0; i < j - 1; i++){
                if(arr[i] > arr[i + 1]){
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            if(!flag){
                break;//aborted the loop
            }else{
                flag = false;//reset flag
            }
        }
    }
}
