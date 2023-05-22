import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    private String name;
    private String email;
    private String password;
    private Scanner scanner;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void signUp() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter your Name: ");
        name = scanner.nextLine();
        System.out.println("Please enter your Email: ");
        email = scanner.nextLine();
        System.out.println("Please enter your Password: ");
        PasswordHandling P = new PasswordHandling();
        password = scanner.nextLine();
        password = P.encrypt(password);
    }

    public Boolean signIn(String filename) throws FileNotFoundException {
        File file = new File(filename);
        scanner = new Scanner(System.in);
        Scanner fileScanner = new Scanner(file);
        System.out.println("Please enter your Email: ");
        email = scanner.nextLine();
        System.out.println("Please enter your Password: ");
        password = scanner.nextLine();
        PasswordHandling passwordHandler = new PasswordHandling();
        password = passwordHandler.encrypt(password);
        boolean found = false;
            while (fileScanner.hasNextLine()) {
                String Name = fileScanner.nextLine();
                String fileEmail = fileScanner.nextLine();
                String filePassword = fileScanner.nextLine();
                if (fileEmail.equals(email) && filePassword.equals(password)) {
                    name = Name;
                    found = true;
                    break;
            }
        }
        return found;
    }
}
