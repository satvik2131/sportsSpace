package com.example.sportsspace.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sportsspace.R;

public class SharedData {
    boolean isUserLoggedIn;
    boolean isAdminLoggedIn;
    String typeOfUser;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SharedData(Context context){
        preferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public boolean isUserLoggedIn() {
        isUserLoggedIn = preferences.getBoolean("userLoggedIn",false);
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        editor.putBoolean("userLoggedIn",userLoggedIn);
        editor.apply();
    }

    public boolean isAdminLoggedIn() {
        isUserLoggedIn = preferences.getBoolean("adminLoggedIn",false);
        return isAdminLoggedIn;
    }

    public void setAdminLoggedIn(boolean adminLoggedIn) {
        editor.putBoolean("adminLoggedIn",adminLoggedIn);
        editor.apply();
    }

    public String getTypeOfUser() {
        String typeOfUser = preferences.getString("typeOfUser","");
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        editor.putString("typeOfUser",typeOfUser);
        editor.apply();
    }
}
