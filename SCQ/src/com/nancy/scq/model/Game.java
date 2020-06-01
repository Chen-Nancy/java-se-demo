package com.nancy.scq.model;

/**
 * @author chen
 * @date 2020/5/29 1:00
 */
public class Game {
    private static Game g = null;
    private final Robot rt;

    private Game() {
        rt = Robot.getInstance();
    }

    public static Game getInstance() {
        if (g == null) {
            g = new Game();
        }
        return g;
    }

    public void startGame() {
        rt.setRed(rt.initBalls("红"));
        rt.setBlue(rt.initBalls("蓝"));
        System.out.println(rt.getRed());
        System.out.println(rt.getBlue());
        rt.winBalls();
        System.out.println(rt.getWinner());
    }
}
