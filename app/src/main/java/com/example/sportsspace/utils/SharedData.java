package com.example.sportsspace.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sportsspace.R;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class SharedData {
    boolean isUserLoggedIn;
    boolean isAdminLoggedIn;
    String typeOfUser;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SharedData(){
    }

    public boolean isUserLoggedIn(Context context) {
        preferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        isUserLoggedIn = preferences.getBoolean("userLoggedIn",false);
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(Context context,boolean userLoggedIn) {
        preferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean("userLoggedIn",userLoggedIn);
        editor.apply();
    }

    public boolean isAdminLoggedIn(Context context) {
        preferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        isUserLoggedIn = preferences.getBoolean("adminLoggedIn",false);
        return isAdminLoggedIn;
    }

    public void setAdminLoggedIn(Context context , boolean adminLoggedIn) {
        preferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean("adminLoggedIn",adminLoggedIn);
        editor.apply();
    }

    public String getTypeOfUser(Context context) {
        preferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        typeOfUser = preferences.getString("typeOfUser","");
        return typeOfUser;
    }

    public void setTypeOfUser(Context context , String typeOfUser) {
        preferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("typeOfUser",typeOfUser);
        editor.apply();
    }
}
