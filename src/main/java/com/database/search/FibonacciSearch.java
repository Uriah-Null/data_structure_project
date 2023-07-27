package com.database.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        int index = fibSearch(arr, 1000);
        System.out.println("下标为：" + index);
    }

    //因为后面我们mid = low + F(k - 1) - 1，需要使用斐波那契数列
    //因此我们需要先获取到一个斐波那契数列
    //非递归得到一个斐波那契数列。
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < maxSize; i++){
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
    * @Author: Cui
    * @Description: 用斐波那契查找
    * @DateTime:  13:05
    * @Params: a 待查找数组，num 要查找的数字
    * @Return 找到返回对应下标，找不到返回-1
    */
    //编写斐波那契查找算法
    public static int fibSearch(int[] a, int num){
        int mid;
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int f[] = fib();//获取到斐波那契数列
        //得到斐波那契分割数值的下标
        while(high > f[k] - 1){
            k++;
        }
        //f[k]的值可能大于a的长度，因此需要用Arrays类，构造一个新的数组，把长度进行填充
        int[] temp = Arrays.copyOf(a,f[k]);
        for(int i = high + 1; i < temp.length; i++){
            temp[i] = a[high];
        }
        //用while来循环处理,找到我们的数num
        while(low <= high){
            mid = low + f[k - 1] - 1;
            if(num < temp[mid]){//小了，向左边查找
                high = mid - 1;
                k--;
            }else if (num > temp[mid]){//大了，向右边查找
                low = mid + 1;
                k -= 2;//
            }else{
                //找到了
                if(mid <= high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;//没找到，返回-1
    }
}
