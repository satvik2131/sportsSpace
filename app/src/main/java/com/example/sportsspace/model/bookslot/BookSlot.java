package com.example.sportsspace.model.bookslot;

public class BookSlot {
    public String uid, tag , sport , date;
    public int startMinute , startHour , endMinute , endHour;


    public BookSlot(String uid , String tag , String sport , String date, int startMinute , int startHour , int endMinute , int endHour){
        this.uid = uid;
        this.tag = tag;
        this.sport = sport;
        this.date = date;
        this.startMinute = startMinute;
        this.startHour = startHour;
        this.endMinute = endMinute;
        this.endHour = endHour;
    }

    public BookSlot(){

    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
}
