import java.util.*;
public class ProfileEditor {
    Scanner scan=new Scanner(System.in);
    public void EditProfile(Vector<Trainee> trainees,Trainee T, String choice) {
        if (choice.equals("1")) {
            String name;
            System.out.println("Enter the new name");
            name = scan.nextLine();
            for (int i = 0; i < trainees.size(); i++) {
                if (trainees.get(i).getEmail().equals(T.getEmail())) {
                    trainees.get(i).setName(name);
                }
            }
        }
            else if (choice.equals("2")) {
                String email;
                System.out.println("Enter the new email");
                email = scan.nextLine();
                for (int i = 0; i < trainees.size(); i++) {
                    if (trainees.get(i).getEmail().equals(T.getEmail())) {
                        trainees.get(i).setEmail(email);
                        T.setEmail(email);
                    }
                }
            } else if (choice.equals("3")) {
                PasswordHandling P = new PasswordHandling();
                String password;
                System.out.println("Enter the new password");
                password = scan.nextLine();
                for (int i = 0; i < trainees.size(); i++) {
                    if (trainees.get(i).getEmail().equals(T.getEmail())) {
                        trainees.get(i).setPassword(P.encrypt(password));
                    }
                }
            }
        }
    }