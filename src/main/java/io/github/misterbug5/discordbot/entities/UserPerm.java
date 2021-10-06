package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

public class UserPerm extends Perm {
    private ArrayList<String> users;

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
    
}
