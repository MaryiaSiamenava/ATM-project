import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    private ArrayList<Admin> admins;

    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserId() {
        String newUserId;
        boolean nonUnique;

        do {
            int random = (int) (Math.random() * 1000000000);
            newUserId = random + "";

            nonUnique = false;
            for(User u : this.users) {
                if(newUserId.compareTo(u.getUserId()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return newUserId;
    }
    public String getNewAdminId() {
        String newAdminId;
        boolean nonUnique;

        do {
            int random = (int) (Math.random() * 100000);
            newAdminId = random + "";

            nonUnique = false;
            for(Admin a : this.admins) {
                if(newAdminId.compareTo(a.getAdminId()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return newAdminId;
    }

    public void addAccount(Account addAcct) {
        this.accounts.add(addAcct);
    }

    public User addUser(String firstName, String lastName, String pin) {
        User newUser = new User(firstName, lastName, this, pin);
        this.users.add(newUser);
        Account newAccount = new Account("Основной", newUser, this);
        return newUser;
    }

    public Admin addAdmin(String firstName, String lastName, String password) {
        Admin newAdmin  = new Admin(firstName, lastName, this, password);
        this.admins.add(newAdmin);
        return newAdmin;
    }

    public Admin adminLogin(String adminId, String password) {
        for(Admin a : this.admins) {
            if(a.getAdminId().compareTo(adminId) == 0 && a.validatePassword(password)) {
                return a;
            }
        }
        return null;
    }

    public User userLogin(String userId, String pin) {
        for(User u : this.users) {
            if(u.getUserId().compareTo(userId) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        return null;
    }
}
