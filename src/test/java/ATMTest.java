import atm.ATM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ATMTest {

    ATM atm = new ATM();

    @Test
    void putCashAmountTest() {
        double balance = atm.getBalance();
        double amount = 10.0;
        atm.putCashAmount(amount);
        Assertions.assertEquals(balance, amount);
    }
}
