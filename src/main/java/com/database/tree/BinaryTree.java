package com.database.tree;

public class BinaryTree {
    public static void main(String[] args) {
        //先创建一个二叉树
        Tree tree = new Tree();
        //创建需要的节点
        HeroNode root = new HeroNode(1,"1");
        HeroNode node2 = new HeroNode(2,"2");
        HeroNode node3 = new HeroNode(3,"3");
        HeroNode node4 = new HeroNode(4,"4");
        HeroNode node5 = new HeroNode(5,"5");
        //先手动插入
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        tree.setRoot(root);

        //测试
        /*
        System.out.println("前序遍历：");//1，2，3，4
        tree.preOrder();

        System.out.println("中序遍历：");//2,1,3,4
        tree.infixOrder();

        System.out.println("后序遍历：");//2,4,3,1
        tree.postOrder();
        */

        //查询测试
        /*
        tree.preSearch(5);
        tree.infixSearch(5);
        tree.postSearch(5);
        */

        //删除测试
        tree.delete(5);
        tree.delete(3);

    }
}
//定义一个二叉树
class Tree{
    private HeroNode root;//根节点
    public void setRoot(HeroNode root){
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public void preSearch(int no){
        if(this.root != null){
            HeroNode heroNode = this.root.preOrderSearch(no);
            if(heroNode != null){
                System.out.println("找到了！雇员名字为:" + heroNode.getName());
            }else{
                System.out.println("没有找到。");
            }
        }else{
            System.out.println("二叉树为空，无法查找。");
        }
    }

    //中序查找
    public void infixSearch(int no){
        if(this.root != null){
            HeroNode heroNode = this.root.infixOrderSearch(no);
            if(heroNode != null){
                System.out.println("找到了！雇员名字为:" + heroNode.getName());
            }else{
                System.out.println("没有找到。");
            }
        }else{
            System.out.println("二叉树为空，无法查找。");
        }
    }

    //后序查找
    public void postSearch(int no){
        if(this.root != null){
            HeroNode heroNode = this.root.postOrderSearch(no);
            if(heroNode != null){
                System.out.println("找到了！雇员名字为:" + heroNode.getName());
            }else{
                System.out.println("没有找到。");
            }
        }else{
            System.out.println("二叉树为空，无法查找。");
        }
    }

    public void delete(int no) {
        if(root.getNo() == no){//如果根节点就是要删除的节点
            root = null;
        }else {
            root.deleteNode(no);//否则递归调用删除节点
        }
    }
}

//先创建HeroNode节点
class HeroNode{
    private String name;
    private  int no;
    private HeroNode left;
    private HeroNode right;
    public HeroNode(int no,String name){
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //输出方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //递归向右子树遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //递归向左子树中序便利
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);//输出父节点
        //递归向右子树遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        //递归向左子树中序便利
        if(this.left != null){
            this.left.postOrder();
        }
        //递归向右子树遍历
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }

    //前序遍历查找
    /**
    * @Author: Cui
    * @Description: 前序遍历查找
    * @DateTime:  22:05
    * @Params: no 要查询的数字
    * @Return HeroNode
    */
    public HeroNode preOrderSearch(int no){
        System.out.println("前序查找");
        if(this.no == no){
            return this;
        }
        HeroNode resNode = null;//用来记录找到的那个节点
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){//说明左边遍历已经找到了
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;//如果没找到，就返回空
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;//用来记录找到的那个节点
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){//说明左边遍历已经找到了
            return resNode;
        }
        System.out.println("中序查找");
        if(this.no == no){
            return this;
        }
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后续遍历查找
    public HeroNode postOrderSearch(int no){

        HeroNode resNode = null;//用来记录找到的那个节点
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){//说明左边遍历已经找到了
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){//说明右边遍历已经找到了
            return resNode;
        }
        System.out.println("后序查找");
        if(this.no == no){
            return this;
        }
        return resNode;
    }

    //删除节点
    public void deleteNode(int no){
        if(this.left != null && this.left.no == no){//如果左节点是要删除的，那么就置为空
            this.left = null;
            System.out.println("此节点被删除");
            return ;
        }
        if(this.right != null && this.right.no == no){
            this.right = null;//如果右节点是要删除的，那么就置为空
            System.out.println("此节点被删除");
            return ;
        }
        //如果都没有置为空,向左和向右进行递归
        if(this.left != null){
            this.left.deleteNode(no);
        }
        if(this.right != null){
            this.right.deleteNode(no);
        }
    }
}