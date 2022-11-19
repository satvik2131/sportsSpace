package com.example.sportsspace.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sportsspace.MainActivity;
import com.example.sportsspace.R;
import com.example.sportsspace.view.ui.admin.adminhome.AdminHome;
import com.example.sportsspace.view.ui.admin.login.AdminLogin;
import com.example.sportsspace.view.ui.user.dashboard.UserHome;
import com.example.sportsspace.view.ui.user.login.PhoneAuth;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;


//Authentication and Authorization based functions (Firebase)
public class Auth {
    public String username, password;

    @Inject
    public Auth() {
    }

    //User Authorization
    public boolean isUserLoggedIn(Context context) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            //IF current activity is not already UserHome then redirect to Userhome
            if (context.getClass().getSimpleName().equals("UserHome") != true) {
                context.startActivity(new Intent(context, UserHome.class));
            }

            // User is signed in
            return true;
        } else {
            // No user is signed in
            context.startActivity(new Intent(context, PhoneAuth.class));
            return false;
        }
    }

    public String typeOfUser(Context context) {
        //Getting user and admin auth points , if any one of them is valid return that
        //Admin
        SharedPreferences sharedPreferences = context.
                getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean("adminIsLoggedIn", false);

        //User
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (result && (user != null)) {
            //If logged in as both user role , you will be logged out from both
            Toast.makeText(context, " logged in from both as user and admin ,you will be logout from both", Toast.LENGTH_LONG).show();
            adminLogout(context);
            userLogout(context);
        }

        if (result) {
            return "Admin";
        } else if (user != null) {
            return "User";
        }

        return "NO USER";
    }


    //Firebase User logout
    public void userLogout(Context context) {
        FirebaseAuth.getInstance().signOut();
        context.startActivity(new Intent(context, MainActivity.class));
    }

    //Logout from admin
    public void adminLogout(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("adminIsLoggedIn", false);
        editor.apply();

        context.startActivity(new Intent(context, MainActivity.class));

    }

    //Admin AUTH check
    public void isAdminLoggedIn(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean("adminIsLoggedIn", false);

        if (result) {
            String simpleName = context.getClass().getSimpleName();

            //IF current activity is not Admin home already then redirect
            if (simpleName.equals("AdminHome") != true) {
                Intent moveToAdminHome = new Intent(context, AdminHome.class);
                context.startActivity(moveToAdminHome);
                return;
            }

            return;
        } else {
            context.startActivity(new Intent(context, AdminLogin.class));
        }
    }


    public void checkIfUserIsApproved(String uid, DatabaseReference reference, Context context) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isApproved = snapshot.child(uid).child("status").getValue(Boolean.class);
                if (isApproved) {
                    context.startActivity(new Intent(context, UserHome.class));
                } else {
                    Toast.makeText(context, "Admin will review your profile then you can login", Toast.LENGTH_LONG).show();
                    context.startActivity(new Intent(context, MainActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    //Admin Login
    public void adminLogin(Context context) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("admin").child("credentials").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dbUsername = snapshot.child("username").getValue(String.class);
                String dbPassword = snapshot.child("password").getValue(String.class);
                boolean finalResult = dbUsername.equals(username) && dbPassword.equals(password) ? true : false;
                Log.d("FinalResult -", String.valueOf(finalResult));

                if (finalResult) {
                    //Authenticated
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
//                    //Save admin auth status
                    SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("adminIsLoggedIn", true);
                    editor.apply();

                    context.startActivity(new Intent(context, AdminHome.class));

                } else {
                    //Wrong Credentials
                    Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
