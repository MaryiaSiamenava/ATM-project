import com.maryia.atm.ATM;
import com.maryia.users.Admin;
import com.maryia.users.Person;
import com.maryia.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MainTest {

    public static Map<Long, Person> personVsIds = new HashMap<>();

    @BeforeAll
    public static void createCharacters(){
        Person firstUser = new User("123", "123", "1234");
        personVsIds.put(firstUser.getID(), firstUser);
        Person secondUser = new User("123", "123", "4321");
        personVsIds.put(secondUser.getID(), secondUser);
        Person admin = new Admin("123", "123", "123456789");
        personVsIds.put(admin.getID(), admin);
    }

    @Test
    void whenLoggedIn_thenReturnTrue() {
        boolean isNotSignedIn = false;
        long ID = 1;

        if (personVsIds.containsKey(ID)) {
            Person currentPerson = personVsIds.get(ID);

            for (int i = 3; i > 0; i--) {
                if (currentPerson.validateAccessCode("1234")) {
                    isNotSignedIn = true;
                    break;
                } else {
                    if (i == 1) System.exit(0);
                }
            }
            Assertions.assertTrue(isNotSignedIn);
        }
    }

    @Test
    void whenNotLoggedIn_thenReturnFalse() {
        boolean logInFailure = true;
        long ID = 1;

        if (personVsIds.containsKey(ID)) {
            Person currentPerson = personVsIds.get(ID);

            if (currentPerson.validateAccessCode("2341")) {
                logInFailure = false;
            }
            Assertions.assertTrue(logInFailure);
        }
    }

    @Test
    void whenEnoughMoneyForTransferring_thenTransferMoneyToOtherUser() {
        User currentPerson = new User("123", "123", "1234");
        currentPerson.addMoneyToAccount(150.0);
        long otherUserID = 2;
        if (personVsIds.containsKey(otherUserID)) {
            Person otherU = personVsIds.get(otherUserID);

            if (otherU instanceof User) {
                double amount = 100.0;
                if (Double.compare((currentPerson).getBalance(), amount) > 0) {
                    currentPerson.takeMoneyFromAccount(amount);
                    ((User) otherU).addMoneyToAccount(amount);
                }
                Assertions.assertEquals(50.0, currentPerson.getBalance());
                Assertions.assertEquals(100.0, ((User) otherU).getBalance());
            }
        }
    }

    @Test
    void whenNotEnoughMoneyForTransferring_thenReturnFalse() {
        boolean moneyTransfer = true;
        User currentPerson = new User("123", "123", "1234");
        currentPerson.addMoneyToAccount(100.0);
        long otherUserID = 2;
        if (personVsIds.containsKey(otherUserID)) {
            Person otherU = personVsIds.get(otherUserID);

            if (otherU instanceof User) {
                double amount = 150.0;
                if (Double.compare((currentPerson).getBalance(), amount) < 0) {
                    moneyTransfer = false;
                }
                Assertions.assertFalse(moneyTransfer);
            }
        }
    }

    @Test
    void whenEnoughMoneyForWithdrawal_thenATMAndAccountBalanceDecrease() {
        User currentUser = new User("123", "123", "1234");
        currentUser.addMoneyToAccount(100.0);
        ATM atm = new ATM();
        atm.putCashAmount(110.0);
        double amount = 50.0;
        if (Double.compare(currentUser.getBalance(), amount) > 0) {
            currentUser.takeMoneyFromATM(atm, amount);
            currentUser.takeMoneyFromAccount(amount);
        }
        Assertions.assertEquals(50.0, currentUser.getBalance());
        Assertions.assertEquals(60.0, atm.getBalance());
    }

    @Test
    void whenNotEnoughMoneyForWithdrawal_thenReturnFalse() {
        boolean withdrawalOfMoney = true;
        User currentUser = new User("123", "123", "1234");
        currentUser.addMoneyToAccount(50.0);
        ATM atm = new ATM();
        double amount = 100.0;
        if (Double.compare(currentUser.getBalance(), amount) < 0) {
            withdrawalOfMoney = false;
        }
        Assertions.assertFalse(withdrawalOfMoney);
    }

    @Test
    void whenTopUpAccount_thenATMAndAccountBalanceIncrease() {
        User currentPerson = new User("123", "123", "1234");
        currentPerson.addMoneyToAccount(150.0);
        ATM atm = new ATM();
        double amount = 50.0;
        currentPerson.addMoneyToAccount(amount);
        atm.putCashAmount(amount);
        Assertions.assertEquals(200.0, currentPerson.getBalance());
        Assertions.assertEquals(50.0, atm.getBalance());
    }
}
