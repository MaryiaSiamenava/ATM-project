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
}
