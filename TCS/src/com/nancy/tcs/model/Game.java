package com.nancy.tcs.model;

/**
 * @author chen
 * @date 2020/5/28 23:14
 */
public class Game {
    private static Game g = null;
    private final Snake s;
    private final Fruit jd;
    private final PrintMap pm;

    private Game() {
        s = Snake.getInstance();
        jd = Fruit.getInstance();
        pm = PrintMap.getInstance();
    }

    public static Game getInstance() {
        if (g == null) {
            g = new Game();
        }
        return g;
    }

    public void startGame() throws InterruptedException {
        //初始化节点为空
        jd.setRandJD("");
        //初始化蛇列表
        s.initial(pm);
        //记录蛇列表长度
        int snakelength = s.getSnake().size();
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    pm.operation();
                }
            }
        };
        t.start();
        while (true) {
            Thread.sleep(800);
            //如果节点为空，产生一个随机节点坐标
            if ("".equals(jd.getRandJD())) {
                jd.initial(pm, s);
            }
            //打印游戏界面
            pm.print(s, jd);
            //输入移动方向
//            pm.operation();
            //蛇移动
            s.snakeMove(jd, pm);
            //如果蛇吃到节点，长度增加，将节点赋空
            if (s.getSnake().size() > snakelength) {
                snakelength = s.getSnake().size();
                jd.setRandJD("");
            }
        }
    }
}
