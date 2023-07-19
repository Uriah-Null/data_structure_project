package com.database.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
        //int arr[] = { 53, 3, 542, 748, 14, 214};
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

        radixSort(arr);

        //记录排序后的时间
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + date2Str);
    }

    public static void radixSort(int[] arr){
        //首先我们要知道数组中最大的那个数的位数
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大的数是几
        int maxLength = (max + "").length();

        for(int i = 0; i < maxLength; i++){
            //定义一个二维数组，表示10个桶，每个桶就是一个二维数组。
            //为了防止放入数的时候数据溢出，就需要每个一维数组都是数组长度那么长，很明显是在用空间换时间。
            int[][] bucket = new int[10][arr.length];
            //为了记录每个桶中实际存放了多少数据。定义一个一维数组，记录每个数组的个数
            //如：bucketElementCounts[0]记录的就是bucket[0]这个桶的数据个数
            int[] bucketElementCounts = new int[10];

            //取出个位
            for(int j = 0; j < arr.length; j++){
                //取出每个元素的个位值
                int digitOfElement =  (arr[j] / (int)(Math.pow(10,i)) % 10);
                //放入对应桶,这句很妙,放进去以后还要把指针向下移动
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序，挨个取数据
            int index = 0;
            //遍历每个桶，把数据放回arr中
            for(int k = 0; k < 10; k++){
                //如果桶中有数据，才拿出来数据
                if(bucketElementCounts[k] != 0){
                    //循环该桶（即第k个一维数组）
                    for(int l = 0; l < bucketElementCounts[k]; l++){
                        arr[index++] = bucket[k][l];
                    }
                }
                //数据用完以后要将每个bucketElementCounts[k]清0
                bucketElementCounts[k] = 0;
            }
            //System.out.println("第" + ( i + 1 ) + "轮处理：" + Arrays.toString(arr));
        }
    }
}
