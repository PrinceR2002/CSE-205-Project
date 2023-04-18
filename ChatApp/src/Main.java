import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class ChatApplication {
    private List<User> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;

    public void run() {
        while (true) {
            System.out.println("Please select from the following options: "+ "\n\n(R)egister, (L)ogin, logout, (C)hat, (Q)uit)"
            		+ "\n_____________________________________________");
            String command = scanner.nextLine();

            switch (command) {
                case "R":
                    register();
                    break;
                case "L":
                    login();
                    break;
                case "logout":
                    logout();
                    break;
                case "C":
                    chat();
                    break;
                case "Q":
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private void register() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already taken");
                return;
            }
        }

        User user = new User(username, password);
        users.add(user);
        System.out.println("Registration successful!"+ "\n_____________________________________________");
    }

    private void login() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful"+ "\n_____________________________________________");
                return;
            }
        }

        System.out.println("Invalid username or password"+ "\n_____________________________________________");
    }

    private void logout() {
        if (currentUser == null) {
            System.out.println("You are not logged in" + "\n_____________________________________________");
        } else {
            currentUser = null;
            System.out.println("Logout successful"+ "\n_____________________________________________");
        }
    }

    private void chat() {
        if (currentUser == null) {
            System.out.println("Please log in first"+ "\n_____________________________________________");
            return;
        }

        System.out.println("Enter message: ");
        String message = scanner.nextLine();

        System.out.println(currentUser.getUsername() + ": " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        ChatApplication chatApplication = new ChatApplication();
        chatApplication.run();
    	Connection c = null;
    	Statement stmt = null;
    	
    	
    }
}
