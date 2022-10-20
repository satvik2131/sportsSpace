package com.example.sportsspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sportsspace.view.ui.dashboard.UserDashboard;
import com.example.sportsspace.view.ui.login.OTPVerify;
import com.example.sportsspace.view.ui.login.PhoneAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void moveToLogin(View view) {
        startActivity(new Intent(this, PhoneAuth.class));
    }

    public void moveToOtp(View view) {
        startActivity(new Intent(this, OTPVerify.class));
    }

    public void moveToDashboard(View view) {startActivity(new Intent(getBaseContext(), UserDashboard.class));}
}