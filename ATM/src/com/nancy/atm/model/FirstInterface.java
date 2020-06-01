package com.nancy.atm.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author chen
 * @date 2020/5/28 21:34
 */
public class FirstInterface {
    private static FirstInterface fi = null;

    private FirstInterface() {

    }

    public static FirstInterface getInstance() {
        if (fi == null) {
            fi = new FirstInterface();
        }
        return fi;
    }

    public void initial(ArrayList<User> user) throws IOException {
        User admin = new User("admin", "admin");
        admin.getOperations().add(new Operation("开户", ATM.getDate(), 0));
        admin.getOperations().add(new Operation("存款", ATM.getDate(), 9999));
        user.add(admin);
        ATM.fileOut();
    }

    public void newUser(ArrayList<User> user) throws IOException {
        boolean isNew = true;
        User one = new User();
        for (int i = 0; i < user.size(); i++) {
            if (one.getUserName().equals(user.get(i).getUserName())) {
                isNew = false;
                break;
            }
        }
        if (!isNew) {
            System.out.println("该用户名已存在！");
        }
        if (isNew) {
            one.setMoney(0);
            one.getOperations().add(new Operation("开户", ATM.getDate(), 0));
            user.add(one);
            ATM.fileOut();
            System.out.println("开户成功！");
        }
    }

    public int checkUser(ArrayList<User> user) throws IOException {
        boolean isTrueUser = false;
        int userIndex = -1;
        User one = new User();
        for (int i = 0; i < user.size(); i++) {
            if (one.getUserName().equals(user.get(i).getUserName())) {
                isTrueUser = true;
                userIndex = i;
                break;
            }
        }
        if (!isTrueUser) {
            System.out.println("非法用户名！");
            return -1;
        }
        if (one.getPassWord().equals(user.get(userIndex).getPassWord())) {
            user.get(userIndex).getOperations().add(new Operation("登录", ATM.getDate(), 0));
            ATM.fileOut();
            System.out.println("登录成功！");
            return userIndex;
        } else {
            user.get(userIndex).getOperations().add(new Operation("登录（-1）", ATM.getDate(), 0));
            ATM.fileOut();
            System.out.println("密码错误！");
            return -1;
        }
    }

    public void admin(ArrayList<User> user) {
        System.out.println(user);
    }
}
