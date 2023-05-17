package com.mypackage;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;



    }
    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
    }

    public boolean userExists(List<User> userList){

        for(int x = 0; x < userList.size(); x++){
            if(userList.get(x).getUsername().equals(username) && userList.get(x).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(User u){
        if(this.username.equals(u.username) && this.password.equals(u.password)){
            return true;
        }
        return false;
    }

    public void saveUserInfo(File User_file) {
        try {
            // create a PrintStream that writes to User_file in case of crash
            PrintStream saved = new PrintStream(new FileOutputStream(User_file, true));

            // write username and password to the file
            saved.printf("%s %s%n", username, password);

            // close the PrintStream
            saved.close();

        } catch (Exception e) {
            System.out.println("Error saving user to file: " + e.getMessage());
        }

    }
}







