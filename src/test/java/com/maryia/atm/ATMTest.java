package com.maryia.atm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ATMTest {

    ATM atm = new ATM();

    @Test
    void testPutCashAmount() {
        double amount = 10.0;
        atm.putCashAmount(amount);
        double balance = atm.getBalance();
        Assertions.assertEquals(10.0, balance);
    }

    @Test
    void testTakeCashAmount() {
        double amount = 10.0;
        atm.putCashAmount(amount + 5.0);
        atm.takeCashAmount(amount);
        double balance = atm.getBalance();
        Assertions.assertEquals(5.0, balance);
    }
}
