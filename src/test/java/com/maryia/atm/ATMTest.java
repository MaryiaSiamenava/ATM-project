package com.maryia.atm;

import com.maryia.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;

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
    void whenEnoughCash_thenATMBalanceDecreases() {
        double amount = 10.0;
        atm.putCashAmount(amount + 5.0);
        atm.takeCashAmount(amount);
        double balance = atm.getBalance();
        Assertions.assertEquals(5.0, balance);
    }

    @Test
    void whenMotEnoughCash_thenTrowNotEnoughMoneyException() {
        double amount = 1000.0;
        boolean exp = false;
        try {
            atm.takeCashAmount(amount);
            System.out.println("Method should throw NotEnoughMoneyException");
        } catch (NotEnoughMoneyException ex) {
            exp = true;
        }

        Assertions.assertTrue(exp);
    }
}
