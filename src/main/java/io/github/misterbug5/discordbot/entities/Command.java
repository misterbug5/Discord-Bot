package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

public class Command {
    private String name;
    protected ArrayList<String> channels;
    protected ArrayList<String> notChannels;
    protected ArrayList<String> roles;
    protected ArrayList<String> notRoles;
    protected ArrayList<String> users;
    protected ArrayList<String> notUsers;

    protected Command(){
        this.name = "";
        this.channels = new ArrayList<String>();
        this.notChannels = new ArrayList<String>();
        this.roles = new ArrayList<String>();
        this.notRoles = new ArrayList<String>();
        this.users = new ArrayList<String>();
        this.notUsers = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<String> channels) {
        this.channels = channels;
    }

    public ArrayList<String> getNotChannels() {
        return notChannels;
    }

    public void setNotChannels(ArrayList<String> notChannels) {
        this.notChannels = notChannels;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public ArrayList<String> getNotRoles() {
        return notRoles;
    }

    public void setNotRoles(ArrayList<String> notRoles) {
        this.notRoles = notRoles;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public ArrayList<String> getNotUsers() {
        return notUsers;
    }

    public void setNotUsers(ArrayList<String> notUsers) {
        this.notUsers = notUsers;
    }

    public void addChannel(String id) {
        this.channels.add(id);
    }

    public void addNotChannel(String id) {
        this.notChannels.add(id);
    }

    public void addRole(String id) {
        this.roles.add(id);
    }

    public void addNotRole(String id) {
        this.notRoles.add(id);
    }

    public void addUser(String id) {
        this.users.add(id);
    }

    public void addNotUser(String id) {
        this.notUsers.add(id);
    }
}
