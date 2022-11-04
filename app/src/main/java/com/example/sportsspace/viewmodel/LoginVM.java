package com.example.sportsspace.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.ViewModel;

import com.example.sportsspace.utils.Auth;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.components.SingletonComponent;


public class LoginVM extends BaseObservable {
    public String userPhoneno;
    private Auth auth;

    public LoginVM(Auth auth){
        this.auth = auth;
    }


    public void OtpSent(Context context){
        auth.sendOtp(userPhoneno,context);

    }

}
