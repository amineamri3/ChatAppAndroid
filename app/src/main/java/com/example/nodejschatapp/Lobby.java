package com.example.nodejschatapp;

import java.util.List;

public class Lobby {
    int membersNum;
    List<User> members;
    String name;
    boolean locked;

    public int getMembersNum() {
        return membersNum;
    }

    public void setMembersNum(int membersNum) {
        this.membersNum = membersNum;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Lobby(int membersNum, String name, boolean locked) {
        this.membersNum = membersNum;
        this.name = name;
        this.locked = locked;
    }
}
