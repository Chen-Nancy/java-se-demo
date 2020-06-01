package com.nancy.ddz.model;

/**
 * @author chen
 * @date 2020/5/28 23:57
 */
public class Player {
    /**
     * 20张手牌
     */
    private String[] hands = new String[20];
    /**
     * 分值权限：默认-1，得地主牌-2，0-3对应叫分
     */
    private int point = -1;
    /**
     * 递增下标，用于开局抓手牌
     */
    private int nextIndex = 0;

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setHands(String[] hands) {
        this.hands = hands;
    }

    public String[] getHands() {
        return hands;
    }

    /**
     * 开局抓手牌
     *
     * @param hand 手牌
     */
    public void addHands(String hand) {
        if (nextIndex < hands.length) {
            hands[nextIndex] = hand;
            nextIndex++;
        }
    }
}
