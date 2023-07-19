package com.database.linkedList;

import java.util.Stack;

import static com.database.linkedList.SingleLinkedList.reserveList;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //第一种方式插入数据
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList new_singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(new HeroNode(1,"宋江","及时雨"));
//        singleLinkedList.add(new HeroNode(2,"卢俊义","玉麒麟"));
//        singleLinkedList.add(new HeroNode(3,"吴用","智多星"));
//        singleLinkedList.add(new HeroNode(4,"林冲","豹子头"));
//        singleLinkedList.add(new HeroNode(5,"鲁智深","花和尚"));
        //第二种方式插入数据
        singleLinkedList.addByOrder(new HeroNode(1,"宋江","及时雨"));
        singleLinkedList.addByOrder(new HeroNode(4,"林冲","豹子头"));
        singleLinkedList.addByOrder(new HeroNode(3,"吴用","智多星"));
        singleLinkedList.addByOrder(new HeroNode(2,"卢俊义","玉麒麟"));
        singleLinkedList.addByOrder(new HeroNode(5,"鲁智深","花和尚"));
        singleLinkedList.addByOrder(new HeroNode(2,"卢俊义","玉麒麟"));

        //查询链表
        //singleLinkedList.list();

        //修改一个数据
        singleLinkedList.update(new HeroNode(4,"林冲","豹子头111"));

        //查询链表
        //singleLinkedList.list();

        //删除一个数据
        //singleLinkedList.delete(2);
        //singleLinkedList.delete(3);
        //查询链表
        singleLinkedList.list();

        //统计有效节点的个数
        singleLinkedList.getHeroNodeNumber();

        //查找倒数第k个节点的信息
        System.out.println(singleLinkedList.getHeroNodeFromLast(2));

        //单链表反转输出
        System.out.println("反转后的链表为：");
        reserveList(singleLinkedList.getHead());
        singleLinkedList.list();

        //压入栈
        Stack<HeroNode> stack = new Stack();
        stack.add(new HeroNode(1,"宋江","及时雨"));
        stack.add(new HeroNode(4,"林冲","豹子头"));
        stack.add(new HeroNode(3,"吴用","智多星"));
        stack.add(new HeroNode(2,"卢俊义","玉麒麟"));
        stack.add(new HeroNode(5,"鲁智深","花和尚"));

        System.out.println("===============================");
        System.out.println("使用出栈的方式，输出数据！");
        while(stack.size() > 0){

            System.out.println(stack.pop());
        }
    }
}

//定义SingleLinkedList,来管理英雄
class SingleLinkedList {
    //初始化一个头节点，头节点就不动了,不存放数据
    private HeroNode head = new HeroNode(0,"","");

    //获得头节点
    public HeroNode getHead(){
        return head;
    }

    //添加节点到单链表
    //思路：不考虑编号的顺序时
    //1、找到当前链表的最后一个节点
    //2、将最后的节点的next指向新的节点
    public void add(HeroNode node) {
        //遍历链表,寻找最后一个节点
        HeroNode temp = head;//先定义一个变量指向头节点
        while(true){
            //如果节点的next域指向为空，那么终止循环
            if(temp.next == null){
                break;
            }
            temp = temp.next;//如果没找到最后，就移动到下一个位置。
        }
        temp.next = node;//将最后一个节点的next域指向新插入的这个节点。
    }

    //第二种添加方式,将英雄添加到指定位置
    public void addByOrder(HeroNode node){
        //遍历链表,寻找要添加位置的前一个节点
        HeroNode temp = head;//先定义一个变量指向头节点
        boolean flag = false;//定义一个标志，用来表示节点是否已经存在
        while(true){
            //如果节点的next域指向为空，那么终止循环
            if(temp.next == null) {
                break;
            }
            //如果下一个节点的编号大于新节点的编号，那么就停止循环，直接插入数据
            if(temp.next.no > node.no){

                break;
            } else if (temp.next.no == node.no) {//如果编号已经存在，那么也要终止循环
                flag = true;
                break;
            }
            temp = temp.next;//如果以上三个步骤都不满足，就移动到下一个位置。
        }
        if (flag == true){
            System.out.println("插入节点失败!" + temp + ",该节点已经存在了");
        }else{
            node.next = temp.next;
            temp.next = node;
            System.out.println("插入节点成功！");
        }
    }

    //修改节点信息，根据no来进行修改
    public void update(HeroNode newnode) {
        HeroNode temp = head;//先定义一个变量指向头节点
        boolean flag = false;//定义一个标志，用来表示节点是否已经存在
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //遍历链表,找到要修改的那个节点编号
        while(true){
            //当循环到结尾的时候跳出循环
            if(temp.next == null){
                break;
            }
            if(temp.no == newnode.no){
                flag = true;//查找到了这个节点
                break;
            }
            temp = temp.next;//指针后移
        }
        if (flag == true){
            temp.name = newnode.name;
            temp.nickname = newnode.nickname;
            System.out.println("修改节点成功！");
        }else{
            System.out.println("未查找到该节点！");
        }
    }

    //删除节点，根据no来删除
    public void delete(int no){
        HeroNode temp = head;//先定义一个变量指向头节点
        boolean flag = false;//定义一个标志，用来表示节点是否已经存在
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        while(true){
            //当循环到结尾的时候跳出循环
            if(temp.next == null){
                break;
            }
            //查找到匹配的no号
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
            }
        if(flag == true){
            System.out.println("找到了要删除的节点！");
            temp.next = temp.next.next;
        }else{
            System.out.println("未查找到该节点！");
        }
    }


    //显示链表
    public void list() {
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //遍历链表
        HeroNode temp = head.next;//定义一个变量指向头节点
        while(true){
            //如果节点的next域指向为空，那么终止循环
            if(temp == null){
                break;
            }
            System.out.println(temp);//自动调用toString方法
            temp = temp.next;//向后移动
        }
    }

    //统计有效节点的个数
    public void getHeroNodeNumber(){
        HeroNode temp = head;//定义一个变量指向头节点
        int count = 0;
        while(true){
            //当循环到结尾的时候跳出循环
            if(temp.next == null){
                break;
            }
            temp = temp.next;//指针后移
            count++;
        }
        System.out.println("有效节点数为：" + count);
    }

    //查找链表中倒数第k个节点的信息
    public HeroNode getHeroNodeFromLast(int k){
        HeroNode temp = head;//定义一个变量指向头节点
        int count = 0;
        while(true){
            //当循环到结尾的时候跳出循环
            if(temp.next == null){
                temp = head;
                break;
            }
            temp = temp.next;//指针后移
            count++;
        }
        for(int i = 0; i <= count - k ; i++){
            temp = temp.next;
        }
        return temp;
    }

    //单链表的反转
   public static void reserveList(HeroNode head){
       HeroNode cur = head.next;
       HeroNode next = null;
       HeroNode reserveHead = new HeroNode(0,"","");
       while(cur != null){
            next = cur.next;
            cur.next = reserveHead.next;
            reserveHead.next = cur;
            cur = next;
       }
       head.next = reserveHead.next;
   }

}


//创建一个HeroNode，每个HeroNode就是一个节点
class HeroNode {
    public int no;//英雄编号
    public String name;//英雄名字
    public String nickname;//英雄昵称
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为显示方便，重写toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }



}
