package com.database.hashTab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("add:  添加雇员");
            System.out.println("find: 查找雇员");
            System.out.println("list: 显示雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id：");
                    int id = scanner.nextInt();
                    System.out.println("输入名字：");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入要查找的id：");
                    int findId = scanner.nextInt();
                    hashTab.find(findId);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;
    public Emp (int id,String name){
        super();
        this.id = id;
        this.name = name;
    }
}

//创建一个HashTab来管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedLists;
    private int size;//表示一共有多少条链表
    //构造器
    public HashTab(int size){
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //初始化每个链表
        for(int i = 0; i < size; i++){
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到员工应该添加到哪条链表中
        int empLinkedListNo = hashFun(emp.id);
        System.out.println(empLinkedListNo);
        empLinkedLists[empLinkedListNo].add(emp);//获取到链表所在位置后添加
    }

    //查找雇员
    public void find(int no){
        int findNo = hashFun(no);
        Emp emp =  empLinkedLists[findNo].findEmpById(no);
        if(emp != null){
            //找到了
            System.out.println("在第" + findNo + "条链表中，找到了" + no + "雇员。");
        }else{
            //没找到
            System.out.println("没有找到相应雇员信息！");
        }
    }

    //遍历所有的链表
    public void list(){
        for(int i = 0; i < size; i++){
            empLinkedLists[i].list(i + 1);//显示链表
        }
    }

    //编写散列函数，使用一个简单取模法
    public int hashFun(int id){
        return id % size;
    }
}

//链表的相关操作
class EmpLinkedList{
    private Emp head;//头节点，默认null
    //添加雇员
    public void add(Emp emp){
        //如果时添加第一个雇员
        if(head == null){
            head = emp;//如果是空的，就添加第一个
            return;
        }
        //如果不是第一个，就循环到最后一个元素，然后添加
        Emp curEmp = head;//一个辅助指针，帮助定位到奥链表末尾
        while(true){
            if(curEmp.next == null){
                break;//当链表已经在最后的时候
            }
            curEmp = curEmp.next ;
        }
        curEmp.next = emp;
    }

    //根据雇员编号,查找雇员
    public Emp findEmpById(int no){
        if(head == null) {//说明链表为空
            System.out.println("没有该雇员信息！");
            return null;//没有找到
        }
        Emp curEmp = head;//一个辅助指针
        while(true){
            if(curEmp.next == null){
                break;//当链表已经在最后的时候
            }
            if(curEmp.id == no){
                break;
            }
            curEmp = curEmp.next;//后移
        }
        return curEmp;//没有找到
    }

    //遍历链表
    public void list(int no){
        if(head == null){//说明链表为空
            System.out.println("第" + no + "链表为空");
            return;
        }
        System.out.print("第" + no + "链表的信息为：");
        Emp curEmp = head;//一个辅助指针
        while(true){
            System.out.printf("=> id = %d  name = %s \t",curEmp.id,curEmp.name);
            if(curEmp.next == null){
                break;//当链表已经在最后的时候
            }
            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }
}
