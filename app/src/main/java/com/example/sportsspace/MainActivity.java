package com.example.sportsspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sportsspace.utils.Auth;
import com.example.sportsspace.view.ui.admin.adminhome.AdminHome;
import com.example.sportsspace.view.ui.admin.login.AdminLogin;
import com.example.sportsspace.view.ui.user.dashboard.UserHome;
import com.example.sportsspace.view.ui.user.login.PhoneAuth;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    Auth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String typeOfUser = auth.typeOfUser(this);

        if(typeOfUser.equals("Admin")){
            auth.isAdminLoggedIn(this);
            finish();
        }else if(typeOfUser.equals("User")){
            auth.isUserLoggedIn(this);
            finish();
        }
    }

    public void moveToLogin(View view) {
        startActivity(new Intent(this, PhoneAuth.class));
    }


    public void moveToAdminLogin(View view) {
        startActivity(new Intent(getBaseContext(), AdminLogin.class));
    }
}