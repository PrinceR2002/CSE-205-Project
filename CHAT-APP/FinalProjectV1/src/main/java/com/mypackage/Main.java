package com.mypackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mypackage.User.isValidPassword;

public class Main {
    /*
    Group Members:
    Sanyam_Jaiswal,
    Prince Rwamatwara,
    Samaria Springfield,
    Suhaas Pedapati,
    Naotao Shimuzi
    */

    /**
     * HOW TO COMPILE ON COMMAND LINE
     * cd Desktop/FinalProjectV1/src/main/java
     * javac -cp . com/mypackage/*.java
     * java -cp . com/mypackage/Main
     *
     * @param args
     */

    public static void main(String[] args) {

        User suhaas = new User("Suhaas", "123");
        List<User> userList = new ArrayList<>();
        List<ChatRoom> chatRoomList = new ArrayList<>();
        userList.add(suhaas);



        Scanner input = new Scanner(System.in);
        String choice = "";

        System.out.println("Welcome to my chat room");
        while(!choice.equals("q")) {
            System.out.println("\nPlease select from the following options: ");
            System.out.println("(R)egister, (L)ogin, (Q)uit");
            System.out.println("-------------------------------------------");

            choice = input.next();

            switch (choice) {
                case "r":
                    System.out.print("Username: ");
                    String username = input.next();

                    System.out.print("Enter Password (Minimum of 1 Uppercase, 1 Lowercase and no special characters): ");
                    String password = input.next();

                    while (!isValidPassword(password)) {
                        System.out.print("Invalid password. Please try again:");
                        password = input.next();
                    }

                   // System.out.println("Your password is: " + password);

            User u1 = new User(username, password);

            if (!u1.userExists(userList)) {
                userList.add(u1);
                System.out.println("\nWelcome " + u1.getUsername() + "!");
            } else {
                System.out.println("\nError, username already exists.");
            }

            break;

            case "l":
                System.out.print("Username: ");
                String lu = input.next();

                System.out.print("Password: ");
                String lp = input.next();

                User u = new User(lu, lp);

                boolean found = u.userExists(userList);

                if (found == true) {
                    loginView(u, userList, chatRoomList);
                } else {
                    System.out.println("\nFailed to login (Wrong username/password)");
                }
                break;
            case "q":
                System.out.println("\nBye!");
                break;
            default:
                System.out.println("\nNot a valid input. Input r or l or q");
                break;

                }
            }
            //System.out.println("Bye!");
        }








    public static void loginView(User u, List<User> userList, List<ChatRoom> chatRoomList){
        Scanner input = new Scanner(System.in);
        System.out.println("\nWelcome "+u.getUsername()+"!");

        String choice2 = "";
        while(!choice2.equals("l")) {
            System.out.println("\nPlease select from the following options:");
            System.out.println("(J)oin, (C)reate, (A)ccount, (L)ogout");
            System.out.println("-------------------------------------------");

            choice2 = input.next();

            switch (choice2){
                case "j":
                    System.out.print("Enter chat name to join: ");
                    String inputName = input.next();

                    ChatRoom c1 = new ChatRoom(inputName);
                    int found1 = c1.chatExists(chatRoomList);
                    if(found1 != -1){
                        chatRoomList.get(found1).addUser(u);
                        System.out.println("\nWelcome to "+c1.getChatRoomName()+" "+u.getUsername());
                        System.out.println("Type /help for help");
                        System.out.println("-------------------------------------------");
                        chatRoomView(u, chatRoomList.get(found1));
                    }
                    else{
                        System.out.println("\nChat room "+c1.getChatRoomName()+" not found!");
                    }
                    break;
                case "c":
                    System.out.print("Enter chat name to create: ");
                    String inputName2 = input.next();

                    ChatRoom c2 = new ChatRoom(inputName2);
                    int found2 = c2.chatExists(chatRoomList);
                    if(found2 != -1){
                        System.out.println("\nChat room with this name already exists!");
                    }
                    else{
                        chatRoomList.add(c2);
                        System.out.println("\nChat room "+c2.getChatRoomName()+" created!");
                        chatRoomList.get(chatRoomList.size()-1).addUser(u);
                        System.out.println("Welcome to "+c2.getChatRoomName()+" "+u.getUsername());
                        System.out.println("Type /help for help");
                        System.out.println("-------------------------------------------");
                        chatRoomView(u, chatRoomList.get(chatRoomList.size()-1));
                    }
                    break;
                case "a":
                    accountView(u, userList);
                    break;
                case "l":
                    break;
                default:
                    System.out.println("\nInvalid input. Enter j c a or l");
                    break;
            }
        }
    }

    public static void accountView(User u, List<User> userList) {
        Scanner input = new Scanner(System.in);
        System.out.println(u.getUsername()+"'s account");
        System.out.println("-------------------------------------------");

        String choice3 = "";

        while (!choice3.equals("quit")) {
            ;
            System.out.println("Change (U)sername or (P)assword (enter quit to leave): ");
            System.out.println("------------------------------------------------------");
            choice3 = input.next();

            switch (choice3) {
                case "u":
                    System.out.print("New username : ");
                    String newusername = input.next();

                    if(newusername.equals(u.getUsername())){
                        System.out.println("\nNew username cannot be same as old one");
                    } else {
                        u.setUsername(newusername);
                        System.out.println("\nYour new username is "+u.getUsername()+" !");

                        for (int i=0; i<userList.size(); i++) {
                            if(userList.get(i).getUsername().equals(u.getUsername())) {
                                userList.set(i, u);
                                break;
                            }
                        }
                    }

                    break;

                case "p":
                    System.out.println("New password (Minimum 1 Uppercase, 1 Lowercase and no special characters): ");
                    String newpassword = input.next();

                    while (!isValidPassword(newpassword)) {
                        System.out.print("Invalid password. Please try again:");
                        newpassword = input.next();
                    }

                    if (newpassword.equals(u.getPassword())) {
                        System.out.println("\nNew password cannot be same as old one");
                    } else {
                        u.setPassword(newpassword);
                        System.out.println("\nYour new password is "+u.getPassword()+" !");

                        for(int f=0; f<userList.size();f++) {
                            if(userList.get(f).getPassword().equals(u.getPassword())) {
                                userList.set(f, u);
                                break;
                            }
                        }
                    }
                    break;
            }

        }


    }
    public static void chatRoomView(User u, ChatRoom chatRoom){
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.equals("/leave")){

            input = scanner.next();


            switch(input){

                case "/list":
                    chatRoom.printUserList();
                    break;
                case "/leave":
                    break;
                case "/history":
                    break;
                case"/help":
                    System.out.println("\n/list (Return a list of users currently in this chat room.)");
                    System.out.println("/leave (Exits the chat room)");
                    System.out.println("/history (Prrint all past messages for the room)");
                    System.out.println("/help (Print the list of available commands)");
                    break;
            }
        }
    }
    //public static void accountView(User u){

    }
