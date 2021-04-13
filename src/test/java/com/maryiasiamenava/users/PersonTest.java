package com.maryiasiamenava.users;

import com.maryiasiamenava.atm.ATM;
import com.maryiasiamenava.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonTest {
    @Test
    void testID_AlwaysUnique() {
        Person user = new User("test", "test", "1");
        Person admin = new Admin("test", "test", "1");

        assertEquals(1, user.getID());
        assertEquals(2, admin.getID());
    }

    @Test
    void testUserGetCache_NotEnoughMoney_throwException() {
        ATM atm = new ATM();
        Person p = randomPerson();
        assertThrows(NotEnoughMoneyException.class, () -> p.takeMoneyFromATM(atm, 10));
    }


    private static Person randomPerson() {
        final String now = LocalDate.now().toString();
        return new User("test" + now, "test" + now, now);
    }
}