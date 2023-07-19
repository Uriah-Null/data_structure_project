package com.database.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {187,35,289,12};
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

        insertSort(arr);

        //记录排序后的时间
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + date2Str);
    }

    public static void insertSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int insertValue = arr[i];//表示要插入的数
            int insertIndex = i - 1;//表示有序表的最后这个元素的下标
            //insertIndex >= 0是为了保证insertValue不越界
            //insertValue < arr[insertIndex]是为了保证要插入的数字小于前面的数字，这样才有插入的必要，不然可以不插入，直接排在后面就行了。
            while(insertIndex >= 0 && insertValue < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }//找到了插入的位置
            arr[insertIndex + 1] = insertValue;//插入数字
        }
    }
}
