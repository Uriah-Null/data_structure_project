package com.database.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];//给temp和arr一样的长度
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("归并排序后=" + Arrays.toString(arr));
    }


    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
    * @Author: Cui
    * @Description: 这是一个合并的方法
    * @DateTime:  12:50
    * @Params: arr:要排序的数组  left:左侧序列头指针  mid:右侧序列头指针  right:右侧序列尾指针  temp:临时数组
    * @Return
    */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid + 1;//初始化j，右边有序序列的初始索引，因为数组长度除以二后，取整了，所以要+1才能表示下一个位置
        int t = 0;//指向temp数组的索引

        //先把左右两边的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                //把左边小于基准值的数，放到temp中去
                temp[t++] = arr[i++];
            }else{
                //把右边小于基准值的数，放到temp中去
                temp[t++] = arr[j++];
            }
        }

        //把剩下的数据的一边的数据一次放到temp中去
        while (i <= mid){//左边
            temp[t++] = arr[i++];
        }
        while (j <= right){//右边
            temp[t++] = arr[j++];
        }

        //最后把temp的元素拷贝到arr中，注意！不是全部都拷贝，处理了几个元素就拷贝几个元素
        t = 0;//将 t 归零
        int tempLeft = left;//定义一个left
        while(tempLeft <= right){
            arr[tempLeft++] = temp[t++];
        }
    }
}
