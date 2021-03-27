import java.util.ArrayList;

public class Bank {
    private String name;

    public Bank(String name){
        this.name = name;
    }

    public String getNewUserId() {
        int random = (int) (Math.random() * 1000000000);
        String newUserId = random + "";
        return newUserId;
    }
    public String getNewAdminId() {
        int random = (int) (Math.random() * 1000000);
        String newAdminId = random + "";
        return newAdminId;
    }
}
