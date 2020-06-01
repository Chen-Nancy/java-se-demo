package com.nancy.tcs.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author chen
 * @date 2020/5/28 23:16
 */
public class Snake {
    private static Snake s = null;
    /**
     * 蛇列表
     */
    private final ArrayList<String> snake = new ArrayList<String>();
    /**
     * 蛇头xy坐标
     */
    private int headX, headY;

    private Snake() {

    }

    public static Snake getInstance() {
        if (s == null) {
            s = new Snake();
        }
        return s;
    }

    public ArrayList<String> getSnake() {
        return snake;
    }

    public int getHeadX() {
        headX = Integer.parseInt(snake.get(0).split(",")[0]);
        return headX;
    }

    public int getHeadY() {
        headY = Integer.parseInt(snake.get(0).split(",")[1]);
        return headY;
    }

    /**
     * 初始化蛇列表
     *
     * @param pm 地图对象
     */
    public void initial(PrintMap pm) {
        Random rand = new Random();
        int indX, indY, direction;
        indX = rand.nextInt(pm.getWidth() - 7) + 3;
        indY = rand.nextInt(pm.getHeight() - 7) + 3;
        direction = rand.nextInt(4);
        snake.add(indX + "," + indY);
        if (direction == 0) {
            for (int i = 0; i < 2; i++) {
                indY--;
                snake.add(indX + "," + indY);
            }
        }
        if (direction == 1) {
            for (int i = 0; i < 2; i++) {
                indY++;
                snake.add(indX + "," + indY);
            }
        }
        if (direction == 2) {
            for (int i = 0; i < 2; i++) {
                indX--;
                snake.add(indX + "," + indY);
            }
        }
        if (direction == 3) {
            for (int i = 0; i < 2; i++) {
                indX++;
                snake.add(indX + "," + indY);
            }
        }
    }

    /**
     * 蛇移动
     *
     * @param JD 节点对象
     * @param pm 地图对象
     */
    public void snakeMove(Fruit JD, PrintMap pm) {
        getHeadX();
        getHeadY();
        boolean isTrue = false;
        //向上移动
        if ("w".equals(pm.getMove())) {
            if (headY > 1) {
                headY--;
                isTrue = true;
            }
        }
        //向下移动
        if ("s".equals(pm.getMove())) {
            if (headY < pm.getHeight() - 2) {
                headY++;
                isTrue = true;
            }
        }
        //向左移动
        if ("a".equals(pm.getMove())) {
            if (headX > 1) {
                headX--;
                isTrue = true;
            }
        }
        //向右移动
        if ("d".equals(pm.getMove())) {
            if (headX < pm.getWidth() - 2) {
                headX++;
                isTrue = true;
            }
        }
        if (isTrue) {
            //如果蛇头与蛇身重合，不做任何操作
            for (int i = 1; i < snake.size(); i++) {
                if (snake.get(i).equals(headX + "," + headY)) {
                    return;
                }
            }
            //如果蛇头与节点重合，增加蛇长度
            if (JD.getRandJD().equals(headX + "," + headY)) {
                snake.add("");
            }
            //更新蛇身坐标
            for (int i = snake.size() - 1; i > 0; i--) {
                snake.set(i, snake.get(i - 1));
            }
            //更新蛇头坐标
            snake.set(0, headX + "," + headY);
        }
    }
}
