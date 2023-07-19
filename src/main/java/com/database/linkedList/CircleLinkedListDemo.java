package com.database.linkedList;

public class CircleLinkedListDemo {
    public static void main(String[] args) {
        circlelinkedlist circlelinkedlist = new circlelinkedlist();
        //添加数据(添加5个小孩节点)
        circlelinkedlist.addKids(5);

        System.out.println("一共有" + circlelinkedlist.getNodeNumber() + "个有效节点。");
        circlelinkedlist.list();

        circlelinkedlist.outKids(5,1,2);

    }
}

class circlelinkedlist {
    private kidNode first = new kidNode(0);

    public void addKids(int nums) {
        //如果插入的数据小于1则表示不能插入
        if (nums < 1) {
            System.out.println("nums的值不正确！");
            return;
        }
        kidNode curNode = null;//定义一个辅助指针
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建节点
            kidNode kidNode = new kidNode(i);
            //如果是第一个小孩
            if (i == 1) {
                first = kidNode;
                first.next = first;//指向自己
                curNode = first;//curNode也指向自己
            } else {
                curNode.next = kidNode;
                kidNode.next = first;
                curNode = kidNode;
            }
        }
    }

    /**
    * @Author: Cui
    * @Description:
    * @DateTime:  15:40
    * @Params: n:一共有多少人。k:从第几个人开始报数。m:一次数几下。
    * @Return 
    */
    public void outKids(int n, int k, int m){
        //先定义一个辅助指针变量helper，事先指向环形链表的最后这个节点。
        kidNode helper = first;
        for(int i = 1; i < n; i++){
            helper = helper.next;
        }
        while (true){
            //小孩开始报数的时候，先将helper和first移动k-1次。
            for(int i = 0; i < k-1; i++){
                first = first.next;
                helper = helper.next;
            }
            //开始数数
            for(int i = 0; i < m-1; i++){
                first = first.next;
                helper = helper.next;
            }
            //出队
            System.out.println(first.no + "号小朋友出队!");
            first = first.next;
            helper.next = first;
            //设置一个终止循环的条件
            if (first.next == first){
                System.out.println(first.no + "号小朋友出队!");
                first.next = null;
                break;
            }
        }
    }

    public void list(){
        kidNode curnode = first;
        while(true){
            if(curnode.next == first){
                break;
            }
            System.out.println(curnode);
            curnode = curnode.next;
        }
        System.out.println(curnode);
    }

    public int getNodeNumber(){
        kidNode curnode = first;
        int count = 0;
        while(true){
            if(curnode.next == first){
                break;
            }
            count++;
            curnode = curnode.next;
        }
        return ++count;
    }
}

class kidNode{
    public int no;
    public kidNode next;

    public kidNode(int no){
        this.no = no;
    }

    public int getNo(){
        return no;
    }

    @Override
    public String toString() {
        return "kidNode{" +
                "no=" + no +
                '}';
    }
}
