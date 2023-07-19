package com.database.recursion;

public class print {

    public static void main(String[] args) {
        test(5);//设置初始n的值为5，也就是会循环4次
    }

   public static void test(int n){
       if(n > 2){
           test(n - 1);//每一次递归调用n的值都减1
       }
       //每次调用到test时就会暂时不执行该语句，等最后一次得到结果以后，从最后一次的结果倒序向前输出
       System.out.println("n = " + n);
    }


}
