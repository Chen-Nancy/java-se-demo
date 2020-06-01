package com.nancy.atm.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author chen
 * @date 2020/5/28 21:36
 */
public class SecondInterface {
    private static SecondInterface si = null;

    private SecondInterface() {

    }

    public static SecondInterface getInstance() {
        if (si == null) {
            si = new SecondInterface();
        }
        return si;
    }

    public void saveMoney(ArrayList<User> user, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的存款金额：");
        int setMoney = sc.nextInt();
        int inMoney = user.get(index).getMoney() + setMoney;
        user.get(index).setMoney(inMoney);
        user.get(index).getOperations().add(new Operation("存款", ATM.getDate(), setMoney));
        ATM.fileOut();
        System.out.println("存入操作成功!");
    }

    public void putMoney(ArrayList<User> user, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的取款金额：");
        int setMoney = sc.nextInt();
        if (setMoney <= user.get(index).getMoney()) {
            int putMoney = user.get(index).getMoney() - setMoney;
            user.get(index).setMoney(putMoney);
            user.get(index).getOperations().add(new Operation("取款", ATM.getDate(), -setMoney));
            ATM.fileOut();
            System.out.println("提取操作成功!");
        } else {
            user.get(index).getOperations().add(new Operation("取款（-1）", ATM.getDate(), 0));
            ATM.fileOut();
            System.out.println("您的账户余额不足!");
        }
    }

    public void moveMoney(ArrayList<User> user, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        int toIndex = -1;
        boolean isTrueUser = false;
        System.out.println("请输入目标用户名:");
        String toUser = sc.next();
        for (int i = 0; i < user.size(); i++) {
            if (toUser.equals(user.get(i).getUserName())) {
                isTrueUser = true;
                toIndex = i;
                break;
            }
        }
        if (!isTrueUser) {
            user.get(index).getOperations().add(new Operation("转账（-1）", ATM.getDate(), 0));
            ATM.fileOut();
            System.out.println("非法用户名！");
            return;
        }
        System.out.println("请输入您的转账金额：");
        int setMoney = sc.nextInt();
        int inMoney, putMoney;
        if (setMoney <= user.get(index).getMoney()) {
            putMoney = user.get(index).getMoney() - setMoney;
            user.get(index).setMoney(putMoney);
            inMoney = user.get(toIndex).getMoney() + setMoney;
            user.get(toIndex).setMoney(inMoney);
            user.get(index).getOperations().add(new Operation("转账", ATM.getDate(), -setMoney));
            user.get(toIndex).getOperations().add(new Operation("转账", ATM.getDate(), setMoney));
            ATM.fileOut();
            System.out.println("转账操作成功!");
        } else {
            user.get(index).getOperations().add(new Operation("转账（-1）", ATM.getDate(), 0));
            ATM.fileOut();
            System.out.println("您的账户余额不足!");
        }
    }

    public void checkMoney(ArrayList<User> user, int index) throws IOException {
        user.get(index).getOperations().add(new Operation("查询", ATM.getDate(), 0));
        ATM.fileOut();
        System.out.println("您的账户余额：" + user.get(index).getMoney() + "元");
    }
}
