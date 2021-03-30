import Money.Account;
import Users.User;

public class Main {
    public static void main(String[] args) {
        User u = new User ("Mary", "Smith", "1234");
        Account a = new Account();
        u.setAccount(a);
        System.out.println(u.getBalance());
    }
}
