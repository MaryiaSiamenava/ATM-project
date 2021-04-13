package com.maryiasiamenava.atm;

import com.maryiasiamenava.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ATMTest {
    ATM atm;

    @BeforeEach
    void putCash() {
        atm = new ATM(10);
    }

    @Test
    void testGetBalance() {
        assertEquals(10.0, atm.getBalance());
    }

    @Test
    void testPutCash() {
        atm.putCashAmount(10);
        assertEquals(20.0, atm.getBalance());
    }

    @Test
    void testNotEnoughMoney_throwsException() {
        assertThrows(NotEnoughMoneyException.class, () -> atm.takeCashAmount(100));
    }
}