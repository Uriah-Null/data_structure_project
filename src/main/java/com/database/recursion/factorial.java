package com.database.recursion;

public class factorial {
    public static void main(String[] args) {
        System.out.println("5的阶乘为：" + factorial1(5));//计算5以内的阶乘
    }

    public static int factorial1(int n){
        if(n == 1){
            return 1;
        }else {
            //每次都调用本函数，直到n = 1时，往回逐渐返回。
            return factorial1(n-1) * n;
        }
    }
}
