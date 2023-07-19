package com.database.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        /*int[] arr = {8,9,1,7,2,3,5,4,6,0};
        shellSort2(arr);*/

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

        shellSort2(arr);

        //记录排序后的时间
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + date2Str);
    }

    //移动法
    public static void shellSort2(int[] arr){
        int count = 0;
        for(int gap = arr.length / 2;gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j - gap]){
                    while(j - gap >= 0 && temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;//往前移
                    }
                    //当退出循环后，相当于找到了插入的位置
                    arr[j] = temp;
                }
            }
            //System.out.println("第" + count++ + "次排序：" + Arrays.toString(arr));
        }
    }

    //交换法
    public static void shellSort(int[] arr){
        int temp = 0;
        int count = 0;
        //每一次都除以二，这样能缩小增量
        for(int gap = arr.length / 2;gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; i++){
                for(int j = i - gap; j >= 0; j -= gap){
                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("第" + count++ + "次排序：" + Arrays.toString(arr));
        }
    }


}
