package com.nancy.ddz.model;

import java.util.Arrays;

/**
 * @author chen
 * @date 2020/5/28 23:56
 */
public class Table {
    private static Table t = null;
    private Game G;
    private Poker P;
    private Player A;
    private Player B;
    private Player C;

    private Table() {
        G = Game.getInstance();
        P = Poker.getInstance();
        A = new Player();
        B = new Player();
        C = new Player();
    }

    public static Table getInstance() {
        if (t == null) {
            t = new Table();
        }
        return t;
    }

    public void startGame() {
        //设置一副扑克
        P.putAllPoker();
        //洗牌
        P.setAllPoker(G.washAllPoker(P));
        //显示52张打乱的扑克
        System.out.println(Arrays.toString(P.getAllPoker()));
        //设置地主牌
        G.setBossPoker(P);
        //发牌
        G.pushPoker(P, A, B, C);
        //显示玩家手牌
        System.out.println("玩家A的手牌：" + Arrays.toString(A.getHands()));
        System.out.println("玩家B的手牌：" + Arrays.toString(B.getHands()));
        System.out.println("玩家C的手牌：" + Arrays.toString(C.getHands()));
        //检查地主牌花落谁家
        G.checkLandlord(A, B, C);
        //显示3位玩家的分值权限：默认-1，得地主牌-2，0-3对应叫分
        System.out.println(A.getPoint() + " , " + B.getPoint() + " , " + C.getPoint());
        //判断叫分是否结束
        while (!G.checkPoints(A, B, C)) {
            //显示分值列表
            G.updateScore();
            //3位玩家按顺序叫分
            G.turnPoints(A, B, C);
        }
        //显示3位玩家的分值权限
        System.out.println(A.getPoint() + " , " + B.getPoint() + " , " + C.getPoint());
        //判断谁的地主，并发底牌
        G.whoWin(A, B, C);
    }
}
