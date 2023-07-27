package com.database.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {
    public static void main(String[] args) {
        //将数组升序排列
        //int[] arr = {4,6,8,5,9};
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

        heapSort(arr);//调用堆排序

        //记录排序后的时间
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + date2Str);
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr){
        int temp = 0;//中间值,用作交换使用
        //System.out.println("堆排序:");
        //分布完成  一步完成
        /*
        adjustHeap(arr,1,arr.length);
        System.out.println("第1次" + Arrays.toString(arr));//4,9,8,5,6
        adjustHeap(arr,0,arr.length);
        System.out.println("第2次" + Arrays.toString(arr));//9,6,8,5,4
        */
        //完成我们最终代码
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for(int i = arr.length / 2 - 1; i >= 0; i--){//这样就是倒序的构建堆,从下往上
            adjustHeap(arr, i, arr.length);
        }

        /*
        3）**将其与末尾元素进行交换**，此时末尾就是最大值了
        4）然后将剩余 n - 1 个元素重新构造成一个堆，这样会得到 n 个元素的次小值。**如此反复**，就得到了一个有序序列。
        */
        for(int j = arr.length - 1; j > 0; j--){
            //交换,把最大的那个只逐个放到第一个位置,倒着放
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);//重新构建成堆
        }
        //System.out.println(Arrays.toString(arr));
    }

    /**
    * @Author: Cui
    * @Description: 将一个数组调整成大顶堆的样子
    * @DateTime:  18:45
    * @Params: arr:要调整的数组，i：表示非叶子节点在数组中的索引，length：表示数组长度
    * @Return
    */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];//先取出当前元素的值，保存在临时变量中。
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1){
            if(k + 1 < length && arr[k] < arr[k + 1]){//如果当前元素的左子节点，小于右子节点
                k++;//k 指向右子节点
            }
            if(arr[k] > temp){//如果子节点大于父节点，那么需要交换两个树
                arr[i] = arr[k];//把较大的值赋值给当前节点
                i = k;//！！！，这个很重要，i 指向 k ，继续循环比较。
            }else{
                break;
            }
        }
        //当for循环结束后，我们已经将i为父节点的树的最大值，放在了最顶端。
        //将temp值放在调整之后的位置
        arr[i] = temp;
    }
}
