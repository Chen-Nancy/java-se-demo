package com.nancy.ddz.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author chen
 * @date 2020/5/28 23:58
 */
public class Game {
    private static Game g = null;
    /**
     * 地主牌
     */
    private String bossPoker;
    /**
     * 底牌
     */
    private String[] bottomPoker = new String[3];
    /**
     * 分值
     */
    private ArrayList<Integer> score = new ArrayList<Integer>();

    private Game() {

    }

    public static Game getInstance() {
        if (g == null) {
            g = new Game();
        }
        return g;
    }

    /**
     * 洗牌
     *
     * @param P 扑克对象
     * @return 54张扑克数组
     */
    public String[] washAllPoker(Poker P) {
        String[] newAllPoker = new String[54];
        //加入新数组的递增下标
        int nextIndex = 0;
        //随机抽取的下标
        int newIndex;
        //设置变量isRepeat表示随机抽取的牌是否在新数组中重复，默认不重复
        boolean isRepeat = false;
        Random rand = new Random();
        while (true) {
            newIndex = rand.nextInt(P.getAllPoker().length);
            //遍历新数组，查看新抽取的值是否重复
            for (int i = 0; i < newAllPoker.length; i++) {
                if (P.getAllPoker()[newIndex].equals(newAllPoker[i])) {
                    isRepeat = true;
                    break;
                } else {
                    isRepeat = false;
                }
            }
            if (!isRepeat) {
                newAllPoker[nextIndex] = P.getAllPoker()[newIndex];
                nextIndex++;
            }
            //如果新数组填充完成，返回新数组
            if (nextIndex == newAllPoker.length) {
                return newAllPoker;
            }
        }
    }

    /**
     * 设置地主牌
     *
     * @param P 扑克对象
     */
    public void setBossPoker(Poker P) {
        int index;
        Random rand = new Random();
        index = rand.nextInt(P.getAllPoker().length - 3);
        bossPoker = P.getAllPoker()[index];
    }

    /**
     * 发牌，并设置底牌
     *
     * @param P 扑克对象
     * @param A 玩家A
     * @param B 玩家B
     * @param C 玩家C
     */
    public void pushPoker(Poker P, Player A, Player B, Player C) {
        //扑克发牌下标
        int index = 0;
        //玩家拿手牌
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    A.addHands(P.getAllPoker()[index]);
                    P.getAllPoker()[index] = "";
                }
                if (j == 1) {
                    B.addHands(P.getAllPoker()[index]);
                    P.getAllPoker()[index] = "";
                }
                if (j == 2) {
                    C.addHands(P.getAllPoker()[index]);
                    P.getAllPoker()[index] = "";
                }
                index++;
            }
        }
        //设置底牌
        for (int i = 0; i < 3; i++) {
            bottomPoker[i] = P.getAllPoker()[index];
            P.getAllPoker()[index] = "";
            index++;
        }
    }

    /**
     * 检查地主牌花落谁家
     *
     * @param A 玩家A
     * @param B 玩家B
     * @param C 玩家C
     */
    public void checkLandlord(Player A, Player B, Player C) {
        for (int i = 0; i < A.getHands().length; i++) {
            if (bossPoker.equals(A.getHands()[i])) {
                //如果A得地主牌，将A分值权限设置为-2
                A.setPoint(-2);
                System.out.println("玩家A的地主牌:" + bossPoker);
                return;
            }
        }
        for (int i = 0; i < B.getHands().length; i++) {
            if (bossPoker.equals(B.getHands()[i])) {
                //如果B得地主牌，将B分值权限设置为-2
                B.setPoint(-2);
                System.out.println("玩家B的地主牌:" + bossPoker);
                return;
            }
        }
        for (int i = 0; i < C.getHands().length; i++) {
            if (bossPoker.equals(C.getHands()[i])) {
                //如果C得地主牌，将C分值权限设置为-2
                C.setPoint(-2);
                System.out.println("玩家C的地主牌:" + bossPoker);
                return;
            }
        }
    }

    /**
     * 显示叫分列表
     */
    public void updateScore() {
        //初始化叫分列表
        if (score.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                score.add(i);
                System.out.print(i + ":" + score.get(i) + "分	");
            }
            System.out.println();
            return;
        }
        //更新叫分列表
        for (int i = 0; i < score.size(); i++) {
            System.out.print(i + ":" + score.get(i) + "分	");
        }
        System.out.println();
    }

    /**
     * 检查叫分是否结束
     *
     * @param A 玩家A
     * @param B 玩家B
     * @param C 玩家C
     * @return 是否结束
     */
    public boolean checkPoints(Player A, Player B, Player C) {
        //玩家中有人叫3分
        if (A.getPoint() == 3 || B.getPoint() == 3 || C.getPoint() == 3) {
            return true;
        }
        //3位玩家均完成叫分
        if (A.getPoint() >= 0 && B.getPoint() >= 0 && C.getPoint() >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断叫分顺序
     *
     * @param A 玩家A
     * @param B 玩家B
     * @param C 玩家C
     */
    public void turnPoints(Player A, Player B, Player C) {
        int index = -1;
        Scanner sc = new Scanner(System.in);
        //玩家A没有叫分
        if (A.getPoint() < 0) {
            //A得地主牌或C完成叫分
            if (A.getPoint() == -2 || C.getPoint() >= 0) {
                System.out.println("玩家A叫分:");
                index = sc.nextInt();
                A.setPoint(score.get(index));
                //将除0分以外的叫分从叫分列表中剔除
                if (score.get(index) > 0) {
                    score.remove(index);
                }
                return;
            }
        }
        //玩家B没有叫分
        if (B.getPoint() < 0) {
            //B得地主牌或A完成叫分
            if (B.getPoint() == -2 || A.getPoint() >= 0) {
                System.out.println("玩家B叫分:");
                index = sc.nextInt();
                B.setPoint(score.get(index));
                //将除0分以外的叫分从叫分列表中剔除
                if (score.get(index) > 0) {
                    score.remove(index);
                }
                return;
            }
        }
        //玩家C没有叫分
        if (C.getPoint() < 0) {
            //C得地主牌或B完成叫分
            if (C.getPoint() == -2 || B.getPoint() >= 0) {
                System.out.println("玩家C叫分:");
                index = sc.nextInt();
                C.setPoint(score.get(index));
                //将除0分以外的叫分从叫分列表中剔除
                if (score.get(index) > 0) {
                    score.remove(index);
                }
            }
        }
    }

    /**
     * 判断谁为地主，并发底牌
     *
     * @param A 玩家A
     * @param B 玩家B
     * @param C 玩家C
     */
    public void whoWin(Player A, Player B, Player C) {
        //如果A的分值权限最大，A为地主
        if (A.getPoint() > B.getPoint() && A.getPoint() > C.getPoint()) {
            System.out.println("玩家A的地主!");
            System.out.println("底牌:" + Arrays.toString(bottomPoker));
            for (int i = 0; i < bottomPoker.length; i++) {
                A.addHands(bottomPoker[i]);
            }
            System.out.println("玩家A的手牌:" + Arrays.toString(A.getHands()));
            return;
        }
        //如果B的分值权限最大，B为地主
        if (B.getPoint() > A.getPoint() && B.getPoint() > C.getPoint()) {
            System.out.println("玩家B的地主!");
            System.out.println("底牌:" + Arrays.toString(bottomPoker));
            for (int i = 0; i < bottomPoker.length; i++) {
                B.addHands(bottomPoker[i]);
            }
            System.out.println("玩家B的手牌:" + Arrays.toString(B.getHands()));
            return;
        }
        //如果C的分值权限最大，C为地主
        if (C.getPoint() > A.getPoint() && C.getPoint() > B.getPoint()) {
            System.out.println("玩家C的地主!");
            System.out.println("底牌:" + Arrays.toString(bottomPoker));
            for (int i = 0; i < bottomPoker.length; i++) {
                C.addHands(bottomPoker[i]);
            }
            System.out.println("玩家C的手牌:" + Arrays.toString(C.getHands()));
            return;
        }
        //如果3位玩家均为0分，重新开局
        if (A.getPoint() == B.getPoint() && A.getPoint() == C.getPoint()) {
            System.out.println("3个怂逼，重新发牌！");
        }
    }
}
