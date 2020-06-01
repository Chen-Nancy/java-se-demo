package com.nancy.atm.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author chen
 * @date 2020/5/28 21:34
 */
public class ATM {
    private static final String FILE_PATH = "E:\\file\\user.txt";
    private static ATM atm = null;
    private static ArrayList<User> user = new ArrayList<User>();
    private final FirstInterface fi;
    private final SecondInterface si;

    private ATM() throws ClassNotFoundException, IOException {
        fi = FirstInterface.getInstance();
        si = SecondInterface.getInstance();
        fileIn();
        if (user.isEmpty()) {
            fi.initial(user);
        }
    }

    public static ATM getInstance() throws ClassNotFoundException, IOException {
        if (atm == null) {
            atm = new ATM();
        }
        return atm;
    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        return sdf.format(new Date());
    }

    public static void fileOut() throws IOException {
        File f = new File(FILE_PATH);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        oos.close();
        fos.close();
    }

    public static void fileIn() throws IOException, ClassNotFoundException {
        File f = new File(FILE_PATH);
        if (f.length() == 0) {
            return;
        }
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<User> al = (ArrayList<User>) ois.readObject();
        if (al != null) {
            user = al;
        }
        ois.close();
        fis.close();
    }

    public void startATM() throws IOException {
        Scanner sc = new Scanner(System.in);
        int num;
        int index;
        while (true) {
            ATM.getDate();
            System.out.println("=======ATM=======");
            System.out.println("=1创建=2登录=3管理员=");
            num = sc.nextInt();
            switch (num) {
                case 1:
                    fi.newUser(user);
                    break;
                case 2:
                    index = fi.checkUser(user);
                    if (index == -1) {
                        break;
                    }
                    while (true) {
                        ATM.getDate();
                        System.out.println("========欢迎使用ATM!========");
                        System.out.println("=1存款=2取款=3查询=4转账=5返回=");
                        num = sc.nextInt();
                        switch (num) {
                            case 1:
                                si.saveMoney(user, index);
                                break;
                            case 2:
                                si.putMoney(user, index);
                                break;
                            case 3:
                                si.checkMoney(user, index);
                                break;
                            case 4:
                                si.moveMoney(user, index);
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("非法操作!");
                                break;
                        }
                        if (num == 5) {
                            break;
                        }
                    }
                    break;
                case 3:
                    fi.admin(user);
                    break;
                default:
                    System.out.println("非法操作!");
                    break;
            }
        }
    }
}
