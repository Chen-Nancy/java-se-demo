package com.nancy.tcs.model;

import java.util.Scanner;

/**
 * @author chen
 * @date 2020/5/28 23:15
 */
public class PrintMap {
    private static PrintMap pm = null;
    /**
     * 界面宽
     */
    private final int width = 16;
    /**
     * 界面高
     */
    private final int height = 10;
    /**
     * 移动方向
     */
    private String move;

    private PrintMap() {

    }

    public static PrintMap getInstance() {
        if (pm == null) {
            pm = new PrintMap();
        }
        return pm;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getMove() {
        return move;
    }

    /**
     * 输入移动方向
     */
    public void operation() {
        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入蛇的移动方向(wasd)，然后回车");
        move = sc.next();
    }

    /**
     * 遍历蛇列表，查看蛇坐标
     *
     * @param s 蛇对象
     * @param x 横坐标
     * @param y 纵坐标
     * @return
     */
    public int printSnake(Snake s, int x, int y) {
        for (int i = 0; i < s.getSnake().size(); i++) {
            if (s.getSnake().get(i).equals(x + "," + y)) {
                if (i == 0) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        return 0;
    }

    /**
     * 打印游戏界面
     *
     * @param s  蛇对象
     * @param jd 节点对象
     */
    public void print(Snake s, Fruit jd) {
        int snakeXY;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                snakeXY = printSnake(s, x, y);
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    System.out.print(" * ");
                } else if (snakeXY == 1) {
                    System.out.print(" X ");
                } else if (snakeXY == 2) {
                    System.out.print(" o ");
                } else if (jd.getRandJD().equals(x + "," + y)) {
                    System.out.print(" + ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}
