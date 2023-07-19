package com.database.linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addNode(new HeroNode_Double(1,"宋江","及时雨"));
        doubleLinkedList.addNode(new HeroNode_Double(2,"卢俊义","玉麒麟"));
        doubleLinkedList.addNode(new HeroNode_Double(3,"吴用","智多星"));
        doubleLinkedList.addNode(new HeroNode_Double(4,"林冲","豹子头"));
        doubleLinkedList.addNode(new HeroNode_Double(5,"鲁智深","花和尚"));

        System.out.println("初始链表：");
        //输出查看一下
        doubleLinkedList.list();

        System.out.println("删除后的链表：");
        doubleLinkedList.deleteNode(3);
        //输出查看一下
        doubleLinkedList.list();

        System.out.println("修改后的链表：");
        doubleLinkedList.updateNode(new HeroNode_Double(4,"林冲","豹子头aaa"));
        //输出查看一下
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    HeroNode_Double head = new HeroNode_Double(0,"","");

    public void addNode(HeroNode_Double node){
        HeroNode_Double temp = head;
        while(true){
            if(temp.next == null){
                temp.next = node;
                node.pre = temp;
                System.out.println("插入节点成功！");
                break;
            }
            temp = temp.next;
        }
    }

    public void deleteNode(int no){
        HeroNode_Double temp = head;
        boolean flag = false;
        if(head.next == null){
            System.out.println("链表为空！");
        }
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            if(temp.next != null){
                temp.next.pre = temp.pre;//如果是最后一个节点，会出现空指针异常，所以要加上一个if判断
            }
            System.out.println("链表删除成功！");
        }else{
            System.out.println("要删除的节点不存在！");
        }
    }

    public void updateNode(HeroNode_Double newnode){
        HeroNode_Double temp = head;//先定义一个变量指向头节点
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

    public void list(){
        HeroNode_Double temp = head.next;
        while(true){
            if(temp.next == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode_Double {
    public int no;
    public String name;
    public String nickname;
    public HeroNode_Double next;//下一个节点
    public HeroNode_Double pre;//上一个节点

    //构造方法
    public HeroNode_Double(int no, String name, String nickname){
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
