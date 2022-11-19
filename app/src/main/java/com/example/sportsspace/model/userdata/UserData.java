package com.example.sportsspace.model.userdata;

public class UserData {
    String phoneno;
    String uid;


    String name;
    boolean status , seen;


    public UserData( String name, String phoneno , String uid , boolean seen, boolean status ){
        this.phoneno = phoneno;
        this.status = status;
        this.seen = seen;
        this.name = name;
        this.uid = uid;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UserData(){}


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getPhoneno() {
        return phoneno;
    }

    public boolean isSeen(){return seen;}

    public void setSeen(boolean seen){this.seen = seen;}

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
