package com.example.sportsspace.viewmodels;

import java.util.Observable;

public class AddInfoDashVM extends Observable {
    public String title , description;
    DashboardMethods dashboardModel;

    public AddInfoDashVM(DashboardMethods dashboardModel ){
        this.dashboardModel = dashboardModel;
    }

    public void addToDashboard(){
        dashboardModel.addToDashboard(title,description);
    }
}
