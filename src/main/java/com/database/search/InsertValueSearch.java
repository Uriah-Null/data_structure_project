package com.database.search;

import java.util.Arrays;
import java.util.EnumSet;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i = 0; i < arr.length; i++){
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 78);
        System.out.println("下标为：" + index);
    }

    /**
    * @Author: Cui
    * @Description: arr:数组  num:要查询的数组  left:左指针  right:右指针
    * @DateTime:  16:40
    * @Params: 插值查找算法
    * @Return 如果找到了，返回对应的下标，如果没找到，返回-1
    */
    public static int insertValueSearch(int[] arr, int left, int right, int nums){
        if(left > right || nums < arr[0] || nums > arr[arr.length - 1]){
            return -1;
        }
        //求出mid
        int mid = left + (right + left) * (nums - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if(nums > midValue){
            //应该向右递归
            return insertValueSearch(arr, mid + 1, right, nums);
        } else if (nums < midValue) {
            //应该向左递归
            return insertValueSearch(arr, mid - 1, right, nums);
        }else{
            return mid;
        }
    }
}
