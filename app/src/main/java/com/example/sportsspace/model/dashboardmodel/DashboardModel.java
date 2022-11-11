package com.example.sportsspace.model.dashboardmodel;

import java.util.Date;


//Dashboard data class
public class DashboardModel {
    public String title , description;
    public Date timestamp ;

    public DashboardModel(String title, String description, Date timestamp){
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
    }

    public DashboardModel(){

    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getTimestamp() {
        return timestamp;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
