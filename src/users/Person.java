package users;

import bank.ATM;
import exceptions.NotEnoughMoneyException;

public abstract class Person {
    private static long ID_COUNTER = 1;
    protected long id;

    public Person() {
        this.id = ID_COUNTER++;
    }

    public long getId() {
        return id;
    }

    public double giveMeMyMoney(ATM atm, double cashAmount) {
        try {
            return atm.takeCashAmount(cashAmount);
        } catch (NotEnoughMoneyException ex) {
            System.out.println("Not enough money. You can only take " + ex.getAtmBalance());
            throw ex;
        }
    }
}
