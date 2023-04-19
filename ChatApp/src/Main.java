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

class ID {
	private String chatroom;

	public ID(String chatroom) {
		this.chatroom = chatroom;
	}

	public String getChatroom() {
		return chatroom;
	}
}

class ChatApplication {
	private List<User> users = new ArrayList<>();
	private List<ID> IDs = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	private User currentUser;
	private ID currentID;
	// private Object chatroom;

	public void run() {
		while (true) {
			System.out.println("Please select from the following options: "
					+ "\n(R)egister, (L)ogin, (Q)uit) "
					+"\n_______________________________________________________");
			String command = scanner.nextLine();

			switch (command) {
			case "R":
				register();
				break;
			case "L":
				login();
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
		System.out.println("_______________________________________________________"+"\nRegistration successful!");
	}

	private void create() {
		if (currentUser == null) {
            System.out.println("You are not logged in!");
            return;
        }
		
		System.out.println("Enter chatroom ID: ");
		String chatroom = scanner.nextLine();

		for (ID ID : IDs) {
			if (ID.getChatroom().equals(chatroom)) {
				System.out.println("Chatroom already exists");
				return;
			}
		}
		ID ID = new ID(chatroom);
		IDs.add(ID);
		System.out.println("Chatroom created!" + "\n_______________________________________________________");
		return;

	}

	private void login() {
		System.out.println("Enter username: ");
		String username = scanner.nextLine();
		System.out.println("Enter password: ");
		String password = scanner.nextLine();

		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				currentUser = user;
				System.out.println("_______________________________________________________"+"\nLogin successful!");
				System.out.println("Please select from the following options: "+ "\n(C)reate chatroom, (J)oin chatroom"+ "\n_______________________________________________________");
				String command = scanner.nextLine();

	            switch (command) {
	                case "C":
	                    create();
	                    break;
	                case "J":
	                	join();
	                    return;
	                default:
	                    System.out.println("Invalid command");
	            }
				return;
			}
			
		}

		System.out.println("Invalid credentials or account does not exist" + "\n_______________________________________________________");
	}

	/// *
	private void join() {
		System.out.println("Enter Chatroom ID: "+ "\n_______________________________________________________");
		String chatroom = scanner.nextLine();

		for (ID ID : IDs) {
			if (ID.getChatroom().equals(chatroom)) {
				currentID = ID;
				System.out.println("Welcome to " + currentID.getChatroom()+"!"+ "\n_______________________________________________________");
				return;		
			}
		}
		System.out.println("Chatroom not found, create one first");
	}
	// */

	private void logout() {
		if (currentUser == null) {
			System.out.println("You are not logged in" + "\n_____________________________________________");
		} else {
			currentUser = null;
			System.out.println("Logout successful" + "\n_____________________________________________");
		}
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
