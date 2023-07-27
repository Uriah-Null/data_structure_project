package com.database.tree;

public class ArrBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        //创建一个ArrBinaryTree
        ArrBinary arrBinary = new ArrBinary(arr);
        System.out.print("前序存储：");
        arrBinary.preOrder();//1,2,4,5,3,6,7
        System.out.println();
        System.out.print("中序存储：");
        arrBinary.infixOrder();//4,2,5,1,6,3,7
        System.out.println();
        System.out.print("后序存储：");
        arrBinary.postOrder();//4,5,2,6,7,3,1
    }
}

//编写一个arrbinary，实现顺序存储二叉树遍历
class ArrBinary{
    private int[] arr;//存储数据节点的数组

    public ArrBinary(int[] arr){
        this.arr = arr;
    }

    //重载一下preOrder让代码变得更好看点
    public void preOrder(){
        this.preOrder(0);
    }

    //重载一下infixOrder让代码变得更好看点
    public void infixOrder(){
        this.infixOrder(0);
    }

    //重载一下postOrder让代码变得更好看点
    public void postOrder(){
        this.postOrder(0);
    }

    /**
    * @Author: Cui
    * @Description: 编写方法，顺序存储前序遍历的二叉树
    * @DateTime:  10:10
    * @Params: n ：数组下标
    * @Return
    */
    public void preOrder(int n){
        //如果数组为空，或者arr.length = 0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空。");
        }
        //输出当前的数组元素
        System.out.print(arr[n] + "  ");
        //向左递归遍历
        if((n * 2 + 1) < arr.length){//防止下标越界
            preOrder(2 * n + 1);
        }
        //向右递归
        if((n * 2 + 2) < arr.length){//防止下标越界
            preOrder(2 * n + 2);
        }
    }

    /**
     * @Author: Cui
     * @Description: 编写方法，顺序存储中序遍历的二叉树
     * @DateTime:  10:10
     * @Params: n ：数组下标
     * @Return
     */
    public void infixOrder(int n){
        //如果数组为空，或者arr.length = 0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空。");
        }
        //向左递归遍历
        if((n * 2 + 1) < arr.length){//防止下标越界
            infixOrder(2 * n + 1);
        }
        //输出当前的数组元素
        System.out.print(arr[n] + "  ");
        //向右递归
        if((n * 2 + 2) < arr.length){//防止下标越界
            infixOrder(2 * n + 2);
        }
    }

    /**
     * @Author: Cui
     * @Description: 编写方法，顺序存储后序遍历的二叉树
     * @DateTime:  10:10
     * @Params: n ：数组下标
     * @Return
     */
    public void postOrder(int n){
        //如果数组为空，或者arr.length = 0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空。");
        }

        //向左递归遍历
        if((n * 2 + 1) < arr.length){//防止下标越界
            postOrder(2 * n + 1);
        }
        //向右递归
        if((n * 2 + 2) < arr.length){//防止下标越界
            postOrder(2 * n + 2);
        }
        //输出当前的数组元素
        System.out.print(arr[n] + "  ");
    }
}