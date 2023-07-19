package com.database.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转后缀表达式的功能
        //说明
        //1、1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
        String infixExpriession = "1+((2+3)*4)-5";
        InfixExpression s1 = new InfixExpression(infixExpriession.length());
        InfixExpression s2 = new InfixExpression(infixExpriession.length());
        int subscript = 0;
        char element = ' ';
        String number = "";
        while(true){
            //如果遍历到最后一个数，直接跳出循环
            if(subscript >= infixExpriession.length()){
                break;
            }
            element = infixExpriession.substring(subscript,subscript + 1).charAt(0);

            if(s1.isOper(element)){
                //如果element的值是一个运算符
                //如果s1为空，或栈顶运算符为"（"，则直接把此运算符入栈。
                if(s1.isEmpty() || s1.peek() == '('){
                    s1.push(element);
                }else if(s1.priority(element) > s1.priority(s1.peek())){
                    //否则，如果优先级比栈顶运算符的高，也将此运算符入栈。
                    s1.push(element);
                }else{
                    //否则，将s1栈顶的运算符弹出，并压入到s2中，再次转到4-a中与s1中的栈顶运算符相比较。
                    s2.push(s1.pop());
                    continue;
                }
            }else if(s1.isBracket(element)){
                //如果输入的是括号
                if(element == '('){
                    //如果是左括号
                    s1.push(element);
                }else{
                    //如果时右括号“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号**丢弃**
                    while (true){
                        if(s1.peek() == '('){
                            break;
                        }
                        s2.push(s1.pop());
                    }
                }
            }else{
                //如果element的值是一个数字,压入s2
                if(number == ""){
                    number += element;
                }
                //如果运行到最后一个字符的时候
                if(subscript == infixExpriession.length() - 1){
                    s2.push(Integer.parseInt(number));
                    break;
                }
                if(!s2.isOper(infixExpriession.substring(subscript + 1,subscript +2).charAt(0))){
                    if(!s2.isBracket(infixExpriession.substring(subscript + 1,subscript +2).charAt(0))){
                        number += element;
                        subscript++;
                        continue;
                    }
                }
                s2.push(Integer.parseInt(number));
                number = "";
                //s2.push(element);
            }
            subscript++;
        }

        //将s1中剩余的运算符依次弹出并压入s2
        while(true){
            if(s1.isEmpty()){
                break;
            }
            s2.push(s1.pop());
        }

        //定义一个数组
        List<Object> list = new ArrayList<Object>();
        while(true) {
            if (s2.isEmpty()) {
                break;
            }
            int temp = s2.pop();

            if((temp + '0') < 60){
                list.add((char)(temp + '0'));
                continue;
            }
            list.add((char)temp);
        }

        //倒序输出
        Collections.reverse(list);
        System.out.println("生成的后缀表达式为：" + list);


        //首先定义一个计算公式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 - ";
        Poland poland = new Poland(suffixExpression.length());
        int index = 0;//定义一个下标
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        char ch = ' ';
        String str = "";
        while(true){
            //跳出循环
            if(index >= suffixExpression.length()){
                break;
            }
            ch = suffixExpression.substring(index,index + 1).charAt(0);
            if(ch == ' '){
                index++;
                continue;
            }else{
                if(!poland.isOper(ch)){
                    //插入数字
                    if(str == ""){
                        str += ch;
                    }
                    //判断如果下一个元素不是符号
                    if(!poland.isOper(suffixExpression.substring(index + 1,index + 2).charAt(0))){
                        //如果不是符号,就拼接到str后面
                        str += ch;
                        index++;
                        continue;
                    }
                    poland.push(Integer.parseInt(str));
                    str = "";
                }else{
                    //如果是符号，就取出刚刚入栈的数，进行计算，然后再放入栈中
                    num1 = poland.pop();
                    num2 = poland.pop();
                    res = poland.cal(num1,num2,ch);
                    poland.push(res);
                }
            }
            index++;
        }
        System.out.println("最终计算结果为：" + res);
    }
}

class InfixExpression{
    private int maxSize;//栈的大小
    private int[] stack;//定义一个数组，因为我们要用数组来实现栈。
    private int top = -1;//初始状态下，将top置于数组的顶端。

    //构造器
    public InfixExpression(int maxSize){
        this.maxSize= maxSize;
        stack = new int[maxSize];
    }

    //判断栈满,因为栈是从下标为0开始的，所以栈满时正好是 maxSize - 1
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //判栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //怎加一个，返回栈顶的值的方法
    public int peek(){
        return stack[top];
    }

    //入栈 push
    public void push(int value){
        if (isFull()){
            System.out.println("栈是满的！");
            return ;
        }
        top++;//top先自加再往里面填值
        stack[top] = value;
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
        return value;
    }

    //判断运算符优先级的方法,数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;
        }//目前假定只有 + - * / 这四个运算
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

    //判断是个运算符还是一个数字
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //判断是否为括号
    public boolean isBracket(char val){
        return val == ')' || val == '(';
    }
}

class Poland{
    private int maxSize;//栈的大小
    private int[] stack;//定义一个数组，因为我们要用数组来实现栈。
    private int top = -1;//初始状态下，将top置于数组的顶端。
    //编写一个构造器,声明变量的同时就生成长度

    public Poland(int maxSize){
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

    //判断是个运算符还是一个数字
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/' || val == ' ';
    }

    //一个计算方法
    public int cal(int num1, int num2, char oper){
        int res = 0;//定义一个res接收结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-'://注意顺序
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }
}

