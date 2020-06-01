package com.nancy.scq.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author chen
 * @date 2020/5/29 0:58
 */
public class Robot {
    private static Robot rt = null;
    private ArrayList<Ball> red = new ArrayList<Ball>();
    private ArrayList<Ball> blue = new ArrayList<Ball>();
    private ArrayList<Ball> winner = new ArrayList<Ball>();

    private Robot() {

    }

    public static Robot getInstance() {
        if (rt == null) {
            rt = new Robot();
        }
        return rt;
    }

    public ArrayList<Ball> getRed() {
        return red;
    }

    public void setRed(ArrayList<Ball> red) {
        this.red = red;
    }

    public ArrayList<Ball> getBlue() {
        return blue;
    }

    public void setBlue(ArrayList<Ball> blue) {
        this.blue = blue;
    }

    public ArrayList<Ball> getWinner() {
        return winner;
    }

    public void setWinner(ArrayList<Ball> winner) {
        this.winner = winner;
    }

    /**
     * 初始化一个球
     *
     * @param color
     * @param num
     * @return
     */
    public Ball initBall(String color, String num) {
        Ball b = new Ball(num, color);
        return b;
    }

    /**
     * 初始化球列表
     *
     * @param color
     * @return
     */
    public ArrayList<Ball> initBalls(String color) {
        ArrayList<Ball> ball = new ArrayList<Ball>();
        String num;
        int forCount = color.equals("红") ? 33 : 16;
        for (int i = 0; i < forCount; i++) {
            if (i < 10) {
                num = "0" + (i + 1);
            } else {
                num = "" + (i + 1);
            }
            ball.add(initBall(color, num));
        }
        return ball;
    }

    /**
     * 取得中奖号码
     */
    public void winBalls() {
        int num;
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            num = rand.nextInt(33 - i);
            winner.add(red.get(num));
            red.remove(num);
        }
        num = rand.nextInt(16);
        winner.add(blue.get(num));
    }
}
