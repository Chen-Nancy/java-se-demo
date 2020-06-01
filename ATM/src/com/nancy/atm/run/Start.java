package com.nancy.atm.run;

import com.nancy.atm.model.ATM;

import java.io.IOException;

/**
 * @author chen
 * @date 2020/5/28 21:37
 */
public class Start {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ATM atm = ATM.getInstance();
        atm.startATM();
    }
}
