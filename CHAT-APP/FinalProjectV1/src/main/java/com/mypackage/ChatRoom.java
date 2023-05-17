package com.mypackage;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {

    private List<User> userList;

    private String chatRoomName;
    private StringBuilder chatHistory;

    public ChatRoom(String chatRoomName){
        this.chatRoomName = chatRoomName;
        userList = new ArrayList<>();
        chatHistory = new StringBuilder();
    }

    public void addUser(User u){
        boolean found = false;
        for (User user: userList) {
            if(user.equals(u)){
                found = true;
            }
        }
        if(found==false){
            userList.add(u);
        }
    }

    public void storeChat(String chat){
        chatHistory.append(chat);
    }

    public int chatExists(List<ChatRoom> chatRoomList){
        for(int x = 0; x<chatRoomList.size(); x++){
            if(chatRoomList.get(x).chatRoomName.equals(this.chatRoomName)){
                return x;
            }
        }
        return -1;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public void printUserList(){
        for (User user:userList) {
            System.out.println(user.getUsername());
        }
    }
}
