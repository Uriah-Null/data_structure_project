package com.database.recursion;

public class queen8 {
    int max = 8;//定义一共有几个皇后
    int[] array = new int[max];//定义一个数组，存放皇后的位置
    static int count = 0;

    public static void main(String[] args) {
        queen8 queen8 = new queen8();
        queen8.check(0);
        System.out.println("总共打印了" + count + "次");
    }

    //编写一个方法，放置第n个皇后
    public void check(int n){
        if(n == max){//n = 8的时候，其实第8个皇后就已经放好了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i = 0; i < max; i++){
            //先把这个皇后n，放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){//如果成立，表示不冲突
                //如果不冲突，接着放n + 1个皇后,开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array[n] = i；即将第n个皇后放置在本行的后移一个位置
        }
    }

    /**
    * @Author: Cui
    * @Description: 检测是否与前面的皇后摆放位置冲突
    * @DateTime:  15:20
    * @Params: n 表示第n个皇后
    * @Return
    */
    //检测是否与前面的摆放位置冲突
    private boolean judge(int n){
        for (int i = 0; i < n; i++){
            if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){//如果当前位置与前面摆放的位置相冲突
                //如果在一条斜线上Math.abs(n - i) == Math.abs(array[n] - array[i])计算的是斜率
                //这里没有必要判断是否在同一行，因为n是一直在递增的，不能能出现n在同一行的情况
                return false;
            }
        }
        return true;
    }

    //写一个方法，将皇后的位置输出
    private void print(){
        count++;
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
