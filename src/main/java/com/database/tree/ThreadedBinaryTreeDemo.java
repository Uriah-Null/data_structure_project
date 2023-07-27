package com.database.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        //先创建一个二叉树
        ThreadedTree threadedTree = new ThreadedTree();
        //创建需要的节点
        Node root = new Node(1,"1");
        Node node2 = new Node(3,"3");
        Node node3 = new Node(6,"6");
        Node node4 = new Node(8,"8");
        Node node5 = new Node(10,"10");
        Node node6 = new Node(14,"14");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化二叉树
        threadedTree.setRoot(root);
        threadedTree.threadedNodes();

        //测试：以10号的节点为例，测试，看看它的前驱和后继都是谁
        Node leftNode = node5.getLeft();
        Node rightNode = node5.getRight();
        System.out.println("前驱节点为：" + leftNode.toString());//3
        System.out.println("后继节点为：" + rightNode.toString());//1

        //遍历线索化二叉树
        threadedTree.threadedList();//8,3,10,1,14,6
    }
}

//编写线索化二叉树的方法
class ThreadedTree {
    private Node root;//根节点
    //定义一个指针，指向当前节点的前驱节点
    private Node pre = null;

    public void setRoot(Node root) {
        this.root = root;
    }

    //重载一下
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList(){
        //定义一个变量，用来指向当前遍历的节点，从root开始。
        Node node = root;
        while(node != null){
            //先循环找到leftType = 1的节点，那么就找到了第一个节点8
            //后面随着遍历而变化，因为leftType = 1的时候，说明该节点就是按照线索化处理后的有效节点。
            while(node.getLeftType() == 0){
                node = node.getLeft();//如果没找到，就向后传递。
            }
            //找到了第一个节点——8。打印该节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就输出
            while(node.getRightType() == 1){
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个节点
            node = node.getRight();
        }
    }

    //线索化节点
    public void threadedNodes(Node node){
        //如果node == null，不能线索化
        if(node == null){
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //再线索化当前节点(难)
        //处理当前节点的左子节点
        if(node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);//将左指针类型设置为 1
        }
        //处理当前节点的右子节点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);//让前驱节点的右指针，指向当前节点
            pre.setRightType(1);//将右指针类型设置为 1
        }
        //!!! 每处理一个节点后，让当前节点是下一个节点的前驱节点。
        pre = node;
        //最后线索化右子树
        threadedNodes(node.getRight());
    }
}

//先创建HeroNode节点
class Node {
    private String name;
    private int no;
    private Node left;
    private Node right;

    private int leftType;//leftType 为0表示指向左子树，为1表示指向前驱节点
    private int rightType;//rightType 为0表示指向右子树，为1表示指向后继节点

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    //输出方法
    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }


}
