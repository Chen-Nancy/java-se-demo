package com.nancy.scq.model;

/**
 * @author chen
 * @date 2020/5/29 0:57
 */
public class Ball {
    private String num;
    private String color;

    public Ball(String num, String color) {
        this.num = num;
        this.color = color;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "[" + color + num + "]";
    }
}
