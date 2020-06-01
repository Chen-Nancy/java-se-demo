package com.nancy.tcs.model;

import java.util.Random;

/**
 * @author chen
 * @date 2020/5/28 23:12
 */
public class Fruit {
    private static Fruit JD = null;
    /**
     * 节点坐标
     */
    private String randJD;

    private Fruit() {

    }

    public static Fruit getInstance() {
        if (JD == null) {
            JD = new Fruit();
        }
        return JD;
    }

    public String getRandJD() {
        return randJD;
    }

    public void setRandJD(String randJD) {
        this.randJD = randJD;
    }

    /**
     * 初始化节点
     *
     * @param pm 地图对象
     * @param s  蛇对象
     */
    public void initial(PrintMap pm, Snake s) {
        Random rand = new Random();
        boolean isRepeat = false;
        int indX, indY;
        while (true) {
            indX = rand.nextInt(pm.getWidth() - 2) + 1;
            indY = rand.nextInt(pm.getHeight() - 2) + 1;
            for (int i = 0; i < s.getSnake().size(); i++) {
                //遍历蛇列表，看节点坐标是否与之重合
                if (s.getSnake().get(i).equals(indX + "," + indY)) {
                    isRepeat = true;
                    break;
                } else {
                    isRepeat = false;
                }
            }
            //如果不重合
            if (!isRepeat) {
                randJD = indX + "," + indY;
                break;
            }
        }
    }
}
