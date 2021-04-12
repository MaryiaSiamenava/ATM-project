import atm.ATM;
import money.Account;
import users.Admin;
import users.Person;
import users.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final long SECRET_OPERATION = 7762323;
    private static Long currentPersonID;

    public static void main(String[] args) {
        ATM atm = new ATM();
        Map<Long, Person> personVsIds = new HashMap<>();

        User curUser = new User("Александр", "Александров", "1234");
        Account curUserAccount = new Account();
        curUser.setAccount(curUserAccount);
        System.out.println(curUser.getID());
        personVsIds.put(curUser.getID(), curUser);
        System.out.println(curUser.getFirstName() + " " + curUser.getLastName());

        User otherUser = new User("Дарья", "Попова", "4321");
        Account otherUserAccount = new Account();
        otherUser.setAccount(otherUserAccount);
        System.out.println(otherUser.getID());
        personVsIds.put(otherUser.getID(), otherUser);
        System.out.println(otherUser.getFirstName() + " " + otherUser.getLastName());

        Admin curAdmin = new Admin("Николай", "Николаев", "123456789");
        System.out.println(curAdmin.getID());
        personVsIds.put(curAdmin.getID(), curAdmin);
        System.out.println(curAdmin.getFirstName() + " " + curAdmin.getLastName());

        Scanner scan = new Scanner(System.in);

        atm.putCashAmount(1000000.0);

        boolean isNotSignedIn = false;
        while (!isNotSignedIn) {
            System.out.println("Введите ID пользователя: ");
            long ID = scan.nextLong();

            if (personVsIds.containsKey(ID)) {
                Person currentPerson = personVsIds.get(ID);
                currentPersonID = ID;

                System.out.println("Введите ПИН-код:");
                for (int i = 3; i > 0; i--) {
                    String pin = scan.next();
                    if (currentPerson.validateAccessCode(pin)) {
                        System.out.println("Идентификация произведена успешно.");
                        isNotSignedIn = true;
                        break;
                    } else {
                        System.out.println("Неверный ПИН-код. Оставшееся количество попыток: " + (i - 1));
                        if (i == 1) System.exit(0);
                    }
                }

            } else {
                System.out.println("Пользователя с таким ID не существует.");
            }
        }

        while (isNotSignedIn) {
            Person currentPerson = personVsIds.get(currentPersonID);
            Account currentUserAccount = currentPerson.getAccount();
            System.out.println("Выберите операцию:\n1 - пополнить счет\n2 - снять деньги со счета\n3 - перевести деньги дрйгому пользователю\n4 - выйти ");
            int userChoice = scan.nextInt();
            if (userChoice == 1) {
                if (topUpAccount(atm, scan, currentUserAccount)) break;
            } else if (userChoice == 2) {
                if (withdrawalOfMoney(atm, curUser, scan, currentUserAccount)) break;
            } else if (userChoice == 3) {
                if (moneyTransfer(personVsIds, scan, currentUserAccount)) break;
            } else if (userChoice == 4) {
                break;
            } else if (currentPerson instanceof Admin && userChoice == SECRET_OPERATION) {
                System.out.println("Здравствуйте администратор.\nВведите 1 если желаете попожить деньги в банкомат\nВведите 2 если желаете извлечь деньги из банкомата: ");
                int adminChoice = scan.nextInt();
                if (adminChoice == 1) {
                    depositMoneyToATM(atm, scan);
                } else if (adminChoice == 2) {
                    takeMoneyToTheBank(atm, curAdmin, scan);
                }
            }
        }
    }

    private static void takeMoneyToTheBank(ATM atm, Admin curAdmin, Scanner scan) {
        System.out.println("Введите сумму, которую желаете извлечь: ");
        double amount = scan.nextDouble();
        curAdmin.takeMoneyFromATM(atm, amount);
        System.out.println("Операция произведена успешно. Текущий запас наличных средств: " + atm.getBalance());
    }

    private static void depositMoneyToATM(ATM atm, Scanner scan) {
        System.out.println("Введите сумму, которую желаете внести: ");
        double amount = scan.nextDouble();
        atm.putCashAmount(amount);
        System.out.println("Операция произведена успешно. Текущий запас наличных средств: " + atm.getBalance());
    }

    private static boolean moneyTransfer(Map<Long, Person> personVsIds, Scanner scan, Account currentUserAccount) {
        System.out.println("Введите ID пользователя, которому желаете первести деньги: ");
        long otherUserID = scan.nextLong();
        if (personVsIds.containsKey(otherUserID)) {
            User otherU = (User) personVsIds.get(otherUserID);
            Account otherUAccount = otherU.getAccount();

            System.out.println("Введите сумму, которую желаете перевести: ");
            double amount = scan.nextDouble();
            if (Double.compare(currentUserAccount.getBalance(), amount) > 0) {
                currentUserAccount.takeMoneyFromAccount(amount);
                otherUAccount.addMoneyToAccount(amount);
                System.out.println("Операция произведена успешно. Остаток на счете равен " + currentUserAccount.getBalance());
                return (KepOrQuit(scan)) != 1;
            } else {
                System.out.println("На вашем счете недостаточно средств. Текущий баланс: " + currentUserAccount.getBalance());
            }
        } else {
            System.out.println("Пользователя с таким ID не существует");
        }
        return false;
    }

    private static boolean withdrawalOfMoney(ATM atm, User curUser, Scanner scan, Account currentUserAccount) {
        System.out.println("Введите сумму, которую желаете снять."); //FIXME . -> :
        double amount = scan.nextDouble();
        if (Double.compare(currentUserAccount.getBalance(), amount) > 0) {
            curUser.takeMoneyFromATM(atm, amount);
            currentUserAccount.takeMoneyFromAccount(amount);
            System.out.println("Операция произведена успешно. Остаток на счете равен " + currentUserAccount.getBalance());
            return (KepOrQuit(scan)) != 1;
        } else {
            System.out.println("На счете недостаточно средств. Текущий баланс: " + currentUserAccount.getBalance());
        }
        return false;
    }

    //FIXME return boolean instead of int
    private static int KepOrQuit(Scanner scan) {
        System.out.println("Желаете совершить другую операцию?\n1 - да\n2 - нет");
        return scan.nextInt();
    }

    private static boolean topUpAccount(ATM atm, Scanner scan, Account currentUserAccount) {
        System.out.println("Введите сумму, на которую желаете пополнить счет: ");
        double amount = scan.nextDouble();
        currentUserAccount.addMoneyToAccount(amount);
        atm.putCashAmount(amount);
        System.out.println("Ваш счет пополнен на " + amount + " рублей. Текущий баланс равен " + currentUserAccount.getBalance());
        return (KepOrQuit(scan)) != 1;
    }
}
