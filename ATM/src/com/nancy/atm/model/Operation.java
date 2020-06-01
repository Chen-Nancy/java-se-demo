package com.nancy.atm.model;

import java.io.Serializable;

/**
 * @author chen
 * @date 2020/5/28 21:35
 */
public class Operation implements Serializable {
    private String way;
    private String date;
    private int money;

    public Operation() {

    }

    public Operation(String way, String date, int money) {
        this.way = way;
        this.date = date;
        this.money = money;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "[" + way + "," + date + "," + money + "]";
    }
}
