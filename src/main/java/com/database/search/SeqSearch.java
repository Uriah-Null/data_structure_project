package com.database.search;

import java.util.Scanner;

public class SeqSearch {
    /*
    有一个数列：{1,8,10,89,1000,1234}，
    判断数列中是否包含给定的查找条件的值，如果找到了，提示找到，并且给出下标值。
    */
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的值：");
        int result = Search(arr,scanner.nextInt());
        if( result != -1){
            System.out.println("数组中存在该值，下标为：" + result);
        }else {
            System.out.println("数组中没有该值!");
        }
    }

    /**
    * @Author: Cui
    * @Description: 顺序查找指定的数字在数组中的下标
    * @DateTime:  22:46
    * @Params: arr 待查找的数组, num  待查找的数字
    * @Return
    */
    public static int Search(int[] arr, int num){
        for(int index = 0;index < arr.length; index++){
            if(num == arr[index]){
                return index;
            }
        }
        return -1;
    }
}
