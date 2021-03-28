import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ATM {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Bank tbb = new Bank("The Best Bank");
        User currentUser = new User("Шаповалова", "Елена", tbb, "1432");
        String currentUserID = currentUser.getUserId();

        while(true) {
            System.out.println("Введите ID пользователя");
            String userID = reader.readLine();
            if(userID.compareTo(currentUserID) == 0) {
                System.out.println("Введите ПИН-код");
                String currentUserPin = reader.readLine();
                if(currentUser.validatePin(currentUserPin) == true) {
                    System.out.println("Здравствуйте " + currentUser.getFirstName() + " " + currentUser.getLastName());
                } else {
                    System.out.println("ПИН-код введен неверно.");
                }
            } else {
                System.out.println("Пользователя с таким ID не существует.");
            }
        }


    }
}
