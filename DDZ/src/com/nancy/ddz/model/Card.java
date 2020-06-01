package com.nancy.ddz.model;

/**
 * @author chen
 * @date 2020/5/28 23:59
 */
public class Card {
    public String num;
    public String color;
    public int score;

    public String[] colors = {"♠", "♥", "♣", "♦"};

    public Card() {

    }

    public Card(String num, String color, int score) {
        this.num = num;
        this.color = color;
        this.score = score;
    }

    @Override
    public String toString() {
        return color + num + "[" + score + "]";
    }
}
