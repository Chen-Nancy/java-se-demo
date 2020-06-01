package com.nancy.scq.run;

import com.nancy.scq.model.Game;

/**
 * @author chen
 * @date 2020/6/1 22:56
 */
public class SCQGame {
    public static void main(String[] args) {
        Game g = Game.getInstance();
        g.startGame();
    }
}
