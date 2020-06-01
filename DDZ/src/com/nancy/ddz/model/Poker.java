package com.nancy.ddz.model;

/**
 * @author chen
 * @date 2020/5/28 23:57
 */
public class Poker {
    private static Poker p = null;
    /**
     * 54张一副扑克
     */
    private String[] allPoker = new String[54];

    private Poker() {

    }

    public static Poker getInstance() {
        if (p == null) {
            p = new Poker();
        }
        return p;
    }

    public void setAllPoker(String[] allPoker) {
        this.allPoker = allPoker;
    }

    public String[] getAllPoker() {
        return allPoker;
    }

    /**
     * 初始化一副扑克
     */
    public void putAllPoker() {
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
}
