package com.database.stack;

public class calculate {
    public static void main(String[] args) {
        //创建一个表达式
        String expression = "70+2*60-2";
        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        char oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//定义一个字符串，用来拼接扫描到的数字
        //用while循环expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);//用charAt(0)来获取到每一个字符
            //分别判断加入
            if (operStack.isOper(ch)) {
                //如果符号栈是空的，那么就直接插入
                if(!operStack.isEmpty()){
                    //如果新插入的符号的优先级小于栈中的运算符，就从numStack中pop出两个数，再从operStack中pop出一个符号，进行计算
                    //注意这里不能真的pop出来，只能进行比较，如果用pop那么符号就出栈了，只能用peek()
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char)operStack.pop();
                        //计算，并用res去接受
                        res =  numStack.cal(num1,num2,oper);
                        //并把结果再压入数栈中
                        numStack.push(res);
                        //最后再把当前符号压入栈
                        operStack.push(ch);
                    }else{
                        //优先级大于pop中的值，就直接入栈
                        operStack.push(ch);
                    }
                }else{
                    //如果符号栈是空的，那么就直接入站operStack
                    operStack.push(ch);
                }
            } else {
                //如果我们发现，扫描到的是一个数的时候，我们就要继续扫描下一个，如果下一个是运算符，才需要入栈
                //在入栈之前，还需要进行字符串的拼接。
                //定义一个String类型的变量，让数字拼起来
                keepNum += ch;
                //还要对index进行越界判断，如果index越界了，就直接入栈了
                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //继续扫描,如果下一个是运算符的话。
                    if(operStack.isOper(expression.substring(index + 1,index + 2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));//如果是数字则入栈keepNum
                        //重要的一点！！！！！！！！要把keepNum清空,不清空下一次还会再这个基础上继续拼接
                        keepNum = "";
                    }
                }
            }
            index++; //让 index++ 直到把expression遍历完成
            if(index >= expression.length()){
                break;
            }
        }
        //最后再把数字和符号依次取出计算，最后数栈中只剩一个值，就是结果。
        while (true){
            //循环终止的条件是符号栈为空
            if(operStack.isEmpty()){
               break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char)operStack.pop();
            //计算，并用res去接收
            res =  numStack.cal(num1,num2,oper);
            //并把结果再压入数栈中
            numStack.push(res);

        }
        System.out.println("最后的计算结果为:" + res);
    }
}
//使用上一个类的方法，但是要拓展一些方法
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//定义一个数组，因为我们要用数组来实现栈。
    private int top = -1;//初始状态下，将top置于数组的顶端。

    //编写一个构造器,声明变量的同时就生成长度
    public ArrayStack2(int maxSize){
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

    //判断是个运算符还是一个数字
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
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

    //怎加一个，返回栈顶的值的方法
    public int peek(){
        return stack[top];
    }

}