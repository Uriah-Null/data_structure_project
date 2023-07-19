package com.database.stack;

import java.sql.Array;
import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack是否正确
        //先创建一个ArrayStack对象来表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";//用来接受输入的值
        boolean loop = true;//loop用于控制是否推出菜单
        Scanner scanner = new Scanner(System.in);//创建输入流方法

        while(loop){
            System.out.println("1：显示栈");
            System.out.println("2：退出程序");
            System.out.println("3：添加数据到栈");
            System.out.println("4：从栈取出数据");
            System.out.println("请输入你的选择:");
            key = scanner.next();

            switch (key){
                case "1":
                    stack.list();
                    break;
                case "2":
                    System.out.println("退出程序");
                    loop = false;
                    break;
                case "3":
                    System.out.println("请输入你向入栈的数据：");
                    int push = scanner.nextInt();
                    stack.push(push);
                    break;
                case "4":
                    stack.pop();
                    break;
                default:
                    break;
            }

        }
    }
}

class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//定义一个数组，因为我们要用数组来实现栈。
    private int top = -1;//初始状态下，将top置于数组的顶端。

    //编写一个构造器,声明变量的同时就生成长度
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];//根据maxSize来生成一个数组
    }

    //判断栈满,因为栈是从下标为0开始的，所以栈满时正好是 maxSize - 1
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //判栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈 push
    public void push(int value){
        if (isFull()){
            System.out.println("栈是满的！");
            return ;
        }
        top++;//top先自加再往里面填值
        stack[top] = value;
        System.out.println("插入数据成功！");
    }

    //出站 pop
    public int pop(){
        int value = 0;
        if(isEmpty()){
            System.out.println("栈是空的！");
            return 0;
        }
        value = stack[top];
        top--;
        System.out.println("输出数据成功！");
        return value;
    }

    //遍历栈的时候是从栈顶开始遍历的 栈顶的下标值为top
    public void list(){
        if(isEmpty()){
            System.out.println("栈是空的！");
            return ;
        }
        for(int i = top; i >= 0; i--){
            System.out.println( stack[i] + " ");
        }
    }
}

