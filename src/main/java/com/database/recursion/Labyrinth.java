package com.database.recursion;

public class Labyrinth {

    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图,一个八行七列的数组
        int[][] map = new int[8][7];
        //使用1表示墙体
        //上下全部设置为1
        for(int i = 0; i < 7; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部设置为1
        for(int j = 0; j < 8; j++){
            map[j][0] = 1;
            map[j][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;


        //输出这个地图
        System.out.println("当前的地图情况：");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay(map,1,1);

        //输出这个地图
        System.out.println("小球的最终路径：");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    //1、map表示地图
    //2、i,j表示从地图的哪个位置开始出发(1,1)
    //3、如果小球找到了map[6][5]位置，则说明通路找到了。
    //4、约定，mao[i][j]为 0 表示该点没有走过，为1表示墙，2表示可以走，3表示已经走过但是走不通
    //5、在走迷宫时，需要确定一个策略，下->右->上->左,如果该点走不通，再回溯

    /**
    * @Author: Cui
    * @Description: 用递归给小球找路
    * @DateTime:  12:52
    * @Params: map表示地图，i，j表示从哪里开始找
    * @Return 如果找到了通路就返回true，找不到返回false
    */
    public static boolean setWay(int[][] map, int i,int j){
        if(map[6][5] == 2){
            return true;//如果终点为2，则表示通路已经找到
        }else{
            if(map[i][j] == 0){//如果该点还没有走过
                //按照策略走动:下->右->上->左
                map[i][j] = 2;
                if(setWay(map, i + 1, j)){//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                }else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                }else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                }else{
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {//如果map[i][j] != 0,则可能是1，2，3 这三个情况
                return false;
            }
        }
    }
}
