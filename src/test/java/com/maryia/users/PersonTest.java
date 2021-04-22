package com.maryia.users;

import com.maryia.atm.ATM;
import com.maryia.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PersonTest {

    @Test
    @Order(1)
    void testID_AlwaysUnique() {
        Person admin = new Admin("123", "123", "1234");
        Person user = new User("123", "123", "1234");

        Assertions.assertEquals(1, admin.getID());
        Assertions.assertEquals(2, user.getID());
    }

    @Test
    void whenTakeMoneyFromATM_thenATMBalanceDecreases() {
        Person admin = new Admin("123", "123", "1234");
        Person user = new User("123", "123", "1234");
        ATM atm = new ATM();
        atm.putCashAmount(100.0);
        admin.takeMoneyFromATM(atm, 20.0);
        Assertions.assertEquals(80.0, atm.getBalance());
        user.takeMoneyFromATM(atm, 20.0);
        Assertions.assertEquals(60.0, atm.getBalance());
    }

    @Test
    void whenAccessCodeIsCorrect_thenReturnTrue() {
        String userPin = "1234";
        String adminPassword = "123456789";
        Person user = new User("123", "123", userPin);
        Person admin = new Admin("123", "123", adminPassword);
        Assertions.assertTrue(user.validateAccessCode("1234"));
        Assertions.assertTrue(admin.validateAccessCode("123456789"));
    }
}
