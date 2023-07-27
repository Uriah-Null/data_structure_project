package com.database.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
有一个数列：{1,8,10,89,1000,1234}，
判断数列中是否包含给定的查找条件的值，如果找到了，提示找到，并且给出下标值。
*/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        int left = 0;
        int right = arr.length - 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的值：");
        ArrayList<Integer> arrayList = Search(arr,scanner.nextInt(),left,right);
        if(arrayList != null){
            System.out.println("数组中存在该值，下标为：" + arrayList);
        }else {
            System.out.println("数组中没有该值!");
        }
    }

    /**
    * @Author: Cui
    * @Description: arr:数组  num:要查询的数组  left:左指针  right:右指针
    * @DateTime:  16:21
    * @Params: 二分法查找
    * @Return 返回-1表示没找到，返回下标表示找到
    */
    public static ArrayList<Integer> Search(int[] arr, int num, int left,int right) {
        int mid = (left + right) / 2;
        if(left > right){
            return new ArrayList<>();//如果左边的值大于右边的值，就什么都没找到,返回-1
        }
        if(num < arr[mid]){
            right = mid -1;//在左边查询
        }else if(num > arr[mid]){
            left = mid + 1;//在右边查询
        }else{
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(mid);//先把当前值保存了
            int midLeft = mid - 1;
            int midRight = mid + 1;
            //在这里对左右进行扫描，把符合条件的值都找到
            //向左查找
            while(true){
                if(midLeft < 0 || arr[midLeft] != num){//退出
                    break;
                }
                list.add(midLeft);
                midLeft -= 1;//下标左移
            }
            //向右查找
            while(true){
                if(midRight > arr.length - 1 || arr[midRight] != num){//退出
                    break;
                }
                list.add(midRight);
                midRight += 1;//下标右移
            }
            return list;
        }
        return Search(arr,num,left,right);//递归
    }
}
