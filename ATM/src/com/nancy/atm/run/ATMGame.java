package com.nancy.atm.run;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author chen
 * @date 2020/5/28 21:38
 */
public class ATMGame {
    /**
     * 管理员用户名
     */
    private static final String ADMIN_USERNAME = "admin";
    /**
     * 管理员密码
     */
    private static final String ADMIN_PASSWORD = "admin";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //创建3个数组，分别用来储存用户名、密码、存款
        String[] userNames = {"admin"};
        String[] passwords = {"admin"};
        int[] moneys = {9999};
        //定义一个num，表示操作数
        int num;
        //第一个while死循环，ATM系统启停
        while (true) {
            System.out.println("=======ATM=======");
            System.out.println("=1创建=2登录=3管理员=");
            num = sc.nextInt();
            //根据操作数不同，分别执行创建账户，登录账户，管理员权限功能
            switch (num) {
                //=====创建用户
                case 1:
                    //定义一个String[]，存放新建的用户名和密码
                    String[] str = newAccount(userNames);
                    if (str == null) {
                        break;
                    }
                    //如果新账户名可用，扩充账户，密码和存款数组，并将新建账户录入
                    userNames = Arrays.copyOf(userNames, userNames.length + 1);
                    passwords = Arrays.copyOf(passwords, passwords.length + 1);
                    moneys = Arrays.copyOf(moneys, moneys.length + 1);
                    userNames[userNames.length - 1] = str[0];
                    passwords[passwords.length - 1] = str[1];
                    moneys[moneys.length - 1] = 0;
                    System.out.println("操作成功!");
                    break;

                //=====登录用户
                //=====金钱操作
                case 2:
                    //定义变量index表示个人账户下标
                    int index = login(userNames, passwords);
                    if (index < 0) {
                        break;
                    }
                    //用户登录成功，进入金钱操作
                    //定义inMoney表示存钱，putMoney表示取钱,toIndex表示转账目标账户下标
                    int inMoney, putMoney;
                    //第二个while死循环，个人账户操作系统启停
                    while (true) {
                        System.out.println("========欢迎使用ATM!========");
                        System.out.println("=1存款=2取款=3查询=4转账=5返回=");
                        num = sc.nextInt();
                        //根据操作数不同，分别执行存款、取款、查询、返回功能
                        switch (num) {
                            //存款操作
                            case 1:
                                System.out.println("请输入您的存款金额:");
                                inMoney = sc.nextInt();
                                addMoney(moneys, index, inMoney);
                                break;
                            //取款操作
                            case 2:
                                System.out.println("请输入您的取款金额:");
                                putMoney = sc.nextInt();
                                subMoney(moneys, index, putMoney);
                                break;
                            //查询操作
                            case 3:
                                System.out.println("您的账户余额:" + moneys[index] + "元");
                                break;
                            //转账操作
                            case 4:
                                moveMoney(moneys, userNames, index);
                                break;
                            //返回操作
                            case 5:
                                break;
                            //非法操作
                            default:
                                System.out.println("非法操作!");
                                break;
                        }
                        //如果操作数是5，返回主页面
                        if (num == 5) {
                            break;
                        }
                    }
                    break;

                //=====管理员
                case 3:
                    admin(userNames, passwords, moneys);
                    break;

                //=====非法操作
                default:
                    System.out.println("非法操作!");
                    break;
            }
        }
    }

    /**
     * 检查输入用户名是否存在于ATM系统用户名数组中，存在则输出所在下标，不存在则输出-1
     *
     * @param userName  输入用户名
     * @param userNames 用户名数组
     * @return
     */
    public static int check(String userName, String[] userNames) {
        for (int i = 0; i < userNames.length; i++) {
            if (userName.equals(userNames[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 新建账户
     *
     * @param userNames 用户名数组
     * @return
     */
    public static String[] newAccount(String[] userNames) {
        Scanner sc = new Scanner(System.in);
        //定义变量userName用来储存输入的用户名，变量password用来储存输入的密码
        String userName, password;
        //定义变量index表示个人账户下标
        int index = -1;
        System.out.println("请输入用户名:");
        userName = sc.next();
        System.out.println("请输入密码:");
        password = sc.next();
        String[] str = {userName, password};
        //查询输入账户名是否存在账户名数组中
        index = check(userName, userNames);
        if (index >= 0) {
            System.out.println("此用户已存在!");
            return null;
        } else {
            return str;
        }
    }

    /**
     * 用户登录，登录成功返回用户所在数组下标，登录失败返回-1
     *
     * @param userNames 用户名数组
     * @param passwords 密码数组
     * @return
     */
    public static int login(String[] userNames, String[] passwords) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String userName = sc.next();
        System.out.println("请输入密码:");
        String password = sc.next();
        //查询输入账户名是否存在账户名数组中
        int index = check(userName, userNames);
        //不存在此用户
        if (index < 0) {
            System.out.println("不存在此用户名!");
            return -1;
        }
        //查询账户对应密码如果错误
        if (!password.equals(passwords[index])) {
            System.out.println("密码错误!");
            return -1;
        }
        //存在此用户，且对应密码正确
        System.out.println("登录成功!" + userNames[index] + "欢迎您的使用");
        return index;
    }

    /**
     * 个人账户取款
     *
     * @param moneys   存款数组
     * @param index    操作用户下标
     * @param putMoney 取款金额
     * @return
     */
    public static boolean subMoney(int[] moneys, int index, int putMoney) {
        //判断账户余额是否充足
        if (putMoney <= moneys[index]) {
            moneys[index] = moneys[index] - putMoney;
            System.out.println("提取操作成功!");
            return true;
        } else {
            System.out.println("您的账户余额不足!");
            return false;
        }
    }

    /**
     * 个人账户存款
     *
     * @param moneys  存款数组
     * @param index   操作用户下标
     * @param inMoney 存款金额
     */
    public static void addMoney(int[] moneys, int index, int inMoney) {
        moneys[index] = moneys[index] + inMoney;
        System.out.println("存入操作成功!");
    }

    /**
     * 个人账户转账
     *
     * @param moneys    存款数组
     * @param userNames 用户名数组
     * @param index     操作用户下标
     */
    public static void moveMoney(int[] moneys, String[] userNames, int index) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入目标用户名:");
        String userName = sc.next();
        //查询输入账户名是否存在账户名数组中，定义变量toIndex表示转账目标账户下标
        int toIndex = check(userName, userNames);
        if (toIndex < 0) {
            System.out.println("不存在此用户名!");
            return;
        }
        System.out.println("请输入您的转账金额:");
        //定义变量toMoney表示转账金额
        int toMoney = sc.nextInt();
        if (subMoney(moneys, index, toMoney)) {
            addMoney(moneys, toIndex, toMoney);
            System.out.println("转账操作成功!");
        }
    }

    /**
     * 管理员操作
     *
     * @param userNames 用户名数组
     * @param passwords 密码数组
     * @param moneys    存款数组
     */
    public static void admin(String[] userNames, String[] passwords, int[] moneys) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入管理员ID:");
        String userName = sc.next();
        System.out.println("请输入密码:");
        String password = sc.next();
        //如果管理员登录成功，则查询所有用户数据
        if (ADMIN_USERNAME.equals(userName) && ADMIN_PASSWORD.equals(password)) {
            System.out.println(Arrays.toString(userNames));
            System.out.println(Arrays.toString(passwords));
            System.out.println(Arrays.toString(moneys));
        } else {
            System.out.println("您没有此权限!");
        }
    }
}
