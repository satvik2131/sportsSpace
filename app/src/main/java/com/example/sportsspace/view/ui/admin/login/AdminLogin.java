package com.example.sportsspace.view.ui.admin.login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.AdminloginBinding;
import com.example.sportsspace.utils.Auth;

import javax.inject.Inject;

public class AdminLogin extends AppCompatActivity {


    @Inject
    Auth auth;

    @Override
    protected void onStart() {
        super.onStart();
        auth.isAdminLoggedIn(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdminloginBinding adminloginBinding = DataBindingUtil.setContentView(this, R.layout.adminlogin);
        adminloginBinding.setAdminLogin(new Auth());
        adminloginBinding.executePendingBindings();
    }
}
