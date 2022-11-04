package com.example.sportsspace.view.ui.user.login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.LoginBinding;
import com.example.sportsspace.utils.Auth;
import com.example.sportsspace.viewmodel.LoginVM;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class PhoneAuth extends AppCompatActivity{

    @Inject
    Auth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginBinding loginBinding = DataBindingUtil.setContentView(
                this,R.layout.login);
        loginBinding.setLoginVM(new LoginVM(auth));
        loginBinding.executePendingBindings();
    }
}
