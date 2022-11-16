package com.example.sportsspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sportsspace.view.ui.admin.adminhome.AdminHome;
import com.example.sportsspace.view.ui.admin.login.AdminLogin;
import com.example.sportsspace.view.ui.user.dashboard.UserHome;
import com.example.sportsspace.view.ui.user.login.PhoneAuth;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    public void moveToLogin(View view) {
        startActivity(new Intent(this, PhoneAuth.class));
    }



    public void moveToDashboard(View view) {
        startActivity(new Intent(getBaseContext(), UserHome.class));
    }

    public void moveToAdminLogin(View view) {
        startActivity(new Intent(getBaseContext(), AdminLogin.class));
    }

    public void moveToAdminHome(View view) {
        startActivity(new Intent(getBaseContext(), AdminHome.class));
    }
}