package com.nancy.tcs.run;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author chen
 * @date 2020/5/28 22:47
 */
public class TCSGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int width = 16;
        int height = 10;
        //蛇坐标数组，默认0号元素为蛇头
        String[] snake = {"3,1", "2,1", "1,1"};
        //节点坐标
        String randJD = "";
        String move;
        int snakeLength = snake.length;
        while (true) {
            //生成随机节点
            randJD = "".equals(randJD) ? randomJD(snake, width, height) : randJD;
            //更新游戏界面
            printMap(width, height, snake, randJD);
            System.out.println("请输入蛇的移动方向(wasd)，然后回车");
            //输入操作方向
            move = sc.next();
            //蛇移动，更改蛇坐标数组
            snake = snakeMove(height, width, snake, randJD, move);
            //如果蛇吃到节点，蛇长度增加，则将节点赋空
            if (snake.length > snakeLength) {
                snakeLength = snake.length;
                randJD = "";
            }
        }
    }

    /**
     * 判断蛇位置
     *
     * @param snake 蛇坐标数组
     * @param x     横坐标
     * @param y     纵坐标
     * @return 蛇头返回1，蛇身返回2，不是蛇返回0
     */
    public static int printSnake(String[] snake, int x, int y) {
        for (int i = 0; i < snake.length; i++) {
            if (snake[i].equals(x + "," + y)) {
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
     * 生成随机节点
     *
     * @param snake  蛇坐标数组
     * @param width  界面宽
     * @param height 界面高
     * @return
     */
    public static String randomJD(String[] snake, int width, int height) {
        Random rand = new Random();
        int randX, randY;
        boolean isRepeat = false;
        while (true) {
            randX = rand.nextInt(width - 2) + 1;
            randY = rand.nextInt(height - 2) + 1;
            for (int i = 0; i < snake.length; i++) {
                if (snake[i].equals(randX + "," + randY)) {
                    isRepeat = true;
                    break;
                } else {
                    isRepeat = false;
                }
            }
            if (!isRepeat) {
                return randX + "," + randY;
            }
        }
    }

    /**
     * 画游戏界面
     *
     * @param width  界面宽
     * @param height 界面高
     * @param snake  蛇坐标数组
     * @param randJD 节点坐标
     */
    public static void printMap(int width, int height, String[] snake, String randJD) {
        int snakeXY;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //判断蛇位置
                snakeXY = printSnake(snake, x, y);
                //画界面边界
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    System.out.print(" * ");
                }
                //画蛇头
                else if (snakeXY == 1) {
                    System.out.print(" X ");
                }
                //画蛇身
                else if (snakeXY == 2) {
                    System.out.print(" o ");
                }
                //画节点
                else if ((x + "," + y).equals(randJD)) {
                    System.out.print(" + ");
                }
                //画空白
                else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 获取蛇头坐标
     *
     * @param snake 蛇坐标数组
     * @return
     */
    public static int[] getPoint(String[] snake) {
        int[] snakeJD = new int[2];
        String[] stStr = snake[0].split(",");
        snakeJD[0] = Integer.parseInt(stStr[0]);
        snakeJD[1] = Integer.parseInt(stStr[1]);
        return snakeJD;
    }

    /**
     * 蛇移动控制
     *
     * @param height 界面高
     * @param width  界面宽
     * @param snake  蛇坐标数组
     * @param randJD 节点坐标
     * @param move   移动方向
     * @return 蛇坐标数组
     */
    public static String[] snakeMove(int height, int width, String[] snake, String randJD, String move) {
        int[] snakeJD = getPoint(snake);
        boolean isTrue = false;
        //向上移动
        if ("w".equals(move)) {
            if (snakeJD[1] > 1) {
                snakeJD[1]--;
                isTrue = true;
            }
        }
        //向下移动
        if ("s".equals(move)) {
            if (snakeJD[1] < height - 2) {
                snakeJD[1]++;
                isTrue = true;
            }
        }
        //向左移动
        if ("a".equals(move)) {
            if (snakeJD[0] > 1) {
                snakeJD[0]--;
                isTrue = true;
            }
        }
        //向右移动
        if ("d".equals(move)) {
            if (snakeJD[0] < width - 2) {
                snakeJD[0]++;
                isTrue = true;
            }
        }
        if (isTrue) {
            //如果蛇头与第二节蛇身重合，不做任何操作
            if (snake[1].equals(snakeJD[0] + "," + snakeJD[1])) {
                return snake;
            }
            //如果蛇头与节点重合，增加蛇长度
            if (randJD.equals(snakeJD[0] + "," + snakeJD[1])) {
                snake = Arrays.copyOf(snake, snake.length + 1);
            }
            //更新蛇身坐标
            for (int i = snake.length - 1; i > 0; i--) {
                snake[i] = snake[i - 1];
            }
            //更新蛇头坐标
            snake[0] = snakeJD[0] + "," + snakeJD[1];
        }
        return snake;
    }
}
