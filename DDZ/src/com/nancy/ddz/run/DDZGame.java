package com.nancy.ddz.run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author chen
 * @date 2020/5/28 23:40
 */
public class DDZGame {
    public static void main(String[] args) {
        String[] allPoker = new String[54];
        String bossPoker;
        String[] playerA = new String[20];
        String[] playerB = new String[20];
        String[] playerC = new String[20];
        //叫分权限数组，-1为默认值，得到地主牌为-2，叫分分值对应数字
        int[] points = {-1, -1, -1};
        //分值数组
        ArrayList<Integer> score = new ArrayList<Integer>();
        //定义变量isEnd表示叫分是否结束，默认没结束
        boolean isEnd = false;
        //初始整副扑克
        setAllPoker(allPoker);
        //洗牌
        allPoker = washAllPoker(allPoker);
        System.out.println(Arrays.toString(allPoker));
        //设置地主牌
        bossPoker = allPoker[setBossPoker(allPoker)];
        System.out.println("地主牌：" + bossPoker);
        //发牌
        pushPoker(allPoker, playerA, playerB, playerC);
        System.out.println("玩家A手牌：" + Arrays.toString(playerA));
        System.out.println("玩家B手牌：" + Arrays.toString(playerB));
        System.out.println("玩家C手牌：" + Arrays.toString(playerC));
        //确定地主牌位置
        isBossPoker(playerA, playerB, playerC, bossPoker, points);
        System.out.println(Arrays.toString(points));
        //初始化分值数组
        for (int i = 0; i < 4; i++) {
            score.add(i);
        }
        //叫分
        isEnd = endCall(points, score);
        System.out.println(Arrays.toString(points));
        //判断地主，发底牌
        isBoss(isEnd, allPoker, playerA, playerB, playerC, points);
    }

    /**
     * 初始整副扑克
     *
     * @param allPoker 整副扑克，54张
     */
    public static void setAllPoker(String[] allPoker) {
        String[] color = {"♣", "♦", "♥", "♠"};
        int nextIndex = 0;
        //初始化2-10
        for (int i = 2; i <= 10; i++) {
            for (int j = 0; j < color.length; j++) {
                allPoker[nextIndex] = color[j] + i;
                nextIndex++;
            }
        }
        //初始化J-A
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < color.length; j++) {
                if (i == 0) {
                    allPoker[nextIndex] = color[j] + "J";
                }
                if (i == 1) {
                    allPoker[nextIndex] = color[j] + "Q";
                }
                if (i == 2) {
                    allPoker[nextIndex] = color[j] + "K";
                }
                if (i == 3) {
                    allPoker[nextIndex] = color[j] + "A";
                }
                nextIndex++;
            }
        }
        //初始化大小王
        allPoker[allPoker.length - 2] = "小王";
        allPoker[allPoker.length - 1] = "大王";
    }

    /**
     * 洗牌
     *
     * @param allPoker 整副扑克，45张
     * @return 打乱后的扑克数组
     */
    public static String[] washAllPoker(String[] allPoker) {
        String[] newAllPoker = new String[54];
        //加入新数组的递增下标
        int nextIndex = 0;
        //随机抽取的下标
        int newIndex;
        //设置变量isRepeat表示随机抽取的牌是否在新数组中重复，默认不重复
        boolean isRepeat = false;
        Random rand = new Random();
        while (true) {
            newIndex = rand.nextInt(newAllPoker.length);
            //遍历新数组，查看新抽取的值是否重复
            for (int i = 0; i < newAllPoker.length; i++) {
                if (allPoker[newIndex].equals(newAllPoker[i])) {
                    isRepeat = true;
                    break;
                } else {
                    isRepeat = false;
                }
            }
            if (!isRepeat) {
                newAllPoker[nextIndex] = allPoker[newIndex];
                nextIndex++;
            }
            //如果新数组填充完成，返回新数组
            if (nextIndex == newAllPoker.length) {
                return newAllPoker;
            }
        }
    }

    /**
     * 在除开最后三张牌的牌组中抽取一张地主牌
     *
     * @param allPoker 整副扑克，45张
     * @return 地主牌所在下标
     */
    public static int setBossPoker(String[] allPoker) {
        int index;
        Random rand = new Random();
        index = rand.nextInt(allPoker.length - 3);
        return index;
    }

    /**
     * 三个玩家一人一张依次发牌
     *
     * @param allPoker 整副扑克，45张
     * @param playerA  玩家A手牌
     * @param playerB  玩家B手牌
     * @param playerC  玩家C手牌
     */
    public static void pushPoker(String[] allPoker, String[] playerA, String[] playerB, String[] playerC) {
        //扑克发牌下标
        int index = 0;
        //玩家手牌下标
        int playerIndex = 0;
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    playerA[playerIndex] = allPoker[index];
                    allPoker[index] = "";
                }
                if (j == 1) {
                    playerB[playerIndex] = allPoker[index];
                    allPoker[index] = "";
                }
                if (j == 2) {
                    playerC[playerIndex] = allPoker[index];
                    allPoker[index] = "";
                }
                index++;
            }
            playerIndex++;
        }
    }

    /**
     * 确定地主牌花落谁家
     *
     * @param playerA   玩家A手牌
     * @param playerB   玩家B手牌
     * @param playerC   玩家C手牌
     * @param bossPoker 地主牌
     * @param points    叫分权限数组
     */
    public static void isBossPoker(String[] playerA, String[] playerB, String[] playerC, String bossPoker, int[] points) {
        for (int i = 0; i < playerA.length; i++) {
            if (bossPoker.equals(playerA[i])) {
                points[0] = -2;
                return;
            }
        }
        for (int i = 0; i < playerB.length; i++) {
            if (bossPoker.equals(playerB[i])) {
                points[1] = -2;
                return;
            }
        }
        for (int i = 0; i < playerA.length; i++) {
            if (bossPoker.equals(playerC[i])) {
                points[2] = -2;
                return;
            }
        }
    }

    /**
     * 显示分值数组
     *
     * @param score
     */
    public static void whoCall0(ArrayList<Integer> score) {
        for (int i = 0; i < score.size(); i++) {
            System.out.print(i + "：" + score.get(i) + "分	");
        }
        System.out.println();
    }

    /**
     * 判断叫分权限
     *
     * @param points 叫分权限数组
     * @param score  分值数组
     */
    public static void whoCall(int[] points, ArrayList<Integer> score) {
        Scanner sc = new Scanner(System.in);
        int index;
        if (points[0] == -2 || (points[0] == -1 && points[2] >= 0)) {
            System.out.println("玩家A叫分：");
            whoCall0(score);
            index = sc.nextInt();
            points[0] = score.get(index);
            if (score.get(index) > 0) {
                score.remove(index);
            }
            return;
        }
        if (points[1] == -2 || (points[1] == -1 && points[0] >= 0)) {
            System.out.println("玩家B叫分：");
            whoCall0(score);
            index = sc.nextInt();
            points[1] = score.get(index);
            if (score.get(index) > 0) {
                score.remove(index);
            }
            return;
        }
        if (points[2] == -2 || (points[2] == -1 && points[1] >= 0)) {
            System.out.println("玩家C叫分：");
            whoCall0(score);
            index = sc.nextInt();
            points[2] = score.get(index);
            if (score.get(index) > 0) {
                score.remove(index);
            }
        }
    }

    /**
     * 判断叫分是否结束
     *
     * @param points 叫分权限数组
     * @return 如果有玩家叫3分，或三位玩家都叫完，则返回true
     */
    public static boolean endCall(int[] points, ArrayList<Integer> score) {
        for (int i = 0; i < 3; i++) {
            whoCall(points, score);
            for (int j = 0; j < points.length; j++) {
                if ("3".equals(points[j] + "")) {
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * 判断地主，发底牌
     *
     * @param isEnd    叫分是否结束
     * @param allPoker 整副扑克
     * @param playerA  玩家A手牌
     * @param playerB  玩家B手牌
     * @param playerC  玩家C手牌
     * @param points   叫分权限
     */
    public static void isBoss(boolean isEnd, String[] allPoker, String[] playerA, String[] playerB, String[] playerC, int[] points) {
        if (isEnd) {
            if (points[0] > points[1] && points[0] > points[2]) {
                System.out.println("玩家A的地主！");
                playerA[playerA.length - 1] = allPoker[allPoker.length - 1];
                playerA[playerA.length - 2] = allPoker[allPoker.length - 2];
                playerA[playerA.length - 3] = allPoker[allPoker.length - 3];
                System.out.println("玩家A手牌：" + Arrays.toString(playerA));
            }
            if (points[1] > points[0] && points[1] > points[2]) {
                System.out.println("玩家B的地主！");
                playerB[playerB.length - 1] = allPoker[allPoker.length - 1];
                playerB[playerB.length - 2] = allPoker[allPoker.length - 2];
                playerB[playerB.length - 3] = allPoker[allPoker.length - 3];
                System.out.println("玩家B手牌：" + Arrays.toString(playerB));
            }
            if (points[2] > points[0] && points[2] > points[1]) {
                System.out.println("玩家C的地主！");
                playerC[playerC.length - 1] = allPoker[allPoker.length - 1];
                playerC[playerC.length - 2] = allPoker[allPoker.length - 2];
                playerC[playerC.length - 3] = allPoker[allPoker.length - 3];
                System.out.println("玩家C手牌：" + Arrays.toString(playerC));
            }
            if (points[0] == points[1] && points[0] == points[2]) {
                System.out.println("一群怂逼，重新发牌！");
            }
        }
    }
}
