package com.nancy.atm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author chen
 * @date 2020/5/28 21:37
 */
public class User implements Serializable {
    private String userName;
    private String passWord;
    private int money;
    private ArrayList<Operation> operations = new ArrayList<Operation>();

    public User() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        this.userName = sc.next();
        System.out.println("请输入密码：");
        this.passWord = sc.next();
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.money = 9999;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    @Override
    public String toString() {
        return "[" + this.userName + "," + this.passWord + "," + this.money + "]" + "\n" + this.operations + "\n\n";
    }
}
