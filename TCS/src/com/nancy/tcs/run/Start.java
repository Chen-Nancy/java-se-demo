package com.nancy.tcs.run;

import com.nancy.tcs.model.Game;

/**
 * @author chen
 * @date 2020/5/28 23:16
 */
public class Start {
    public static void main(String[] args) throws InterruptedException {
        Game g = Game.getInstance();
        g.startGame();
    }
}
