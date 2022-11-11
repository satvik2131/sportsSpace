package com.example.sportsspace.viewmodels;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.example.sportsspace.utils.Auth;


public class LoginVM extends BaseObservable {
    public String userPhoneno;
    private Auth auth;
    private Context context;

    public LoginVM(Auth auth , Context context){
        this.context = context;
        this.auth = auth;
    }

    public void OtpSent(){
        auth.sendOtp(userPhoneno,context);
    }

}
