package com.maryia.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void whenAddMoneyToAccount_thenBalanceIncreases() {
        User user = new User("123", "123", "1234");
        user.addMoneyToAccount(50.0);
        Assertions.assertEquals(50.0, user.getBalance());
    }

    @Test
    void whenEnoughMoney_thenBalanceDecreases() {
        User user = new User("123", "123", "1234");
        user.addMoneyToAccount(100.0);
        user.takeMoneyFromAccount(20.0);
        Assertions.assertEquals(80.0, user.getBalance());
    }

    @Test
    void whenNotEnoughMoney_thenBalanceDoesNotChange() {
        User user = new User("123", "123", "1234");
        user.addMoneyToAccount(20.0);
        user.takeMoneyFromAccount(1000.0);
        Assertions.assertEquals(20.0, user.getBalance());
    }
}
