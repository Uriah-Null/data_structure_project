package com.database.linkedList;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Stack;

//Stack的使用
public class TestStack{

    public static void main(String[]args){
        Stack<String> stack = new Stack();
        //入栈
        stack.add("1");
        stack.add("2");
        stack.add("3");
        //出栈
        while(stack.size() > 0){
            System.out.println(stack.pop());//pop就是将栈顶的数据取出
        }
    }

}
