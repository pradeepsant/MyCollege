package com.example.pradeep.mycollage.staff.model;

/**
 * Created by pradeep on 03/08/2016.
 */
public class MyBatchModel {

    public MyBatchModel(String name, String roll_number, String emailid) {
        this.name = name;
        this.roll_number = roll_number;
        this.emailid = emailid;
    }

    String name;

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String roll_number;
        String emailid;


}
