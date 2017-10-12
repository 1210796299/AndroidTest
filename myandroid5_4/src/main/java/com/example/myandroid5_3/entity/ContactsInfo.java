package com.example.myandroid5_3.entity;

/**
 * Created by O on 2017-10-10.
 */

public class ContactsInfo {
    private int head;
    private String name;
    private String phone;
    private String time;
    private boolean mChecked;
    public ContactsInfo(){

    }

    public boolean ismChecked() {
        return mChecked;
    }

    public void setmChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
