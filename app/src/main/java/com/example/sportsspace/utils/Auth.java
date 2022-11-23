package com.example.sportsspace.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sportsspace.MainActivity;
import com.example.sportsspace.view.ui.admin.adminhome.AdminHome;
import com.example.sportsspace.view.ui.admin.login.AdminLogin;
import com.example.sportsspace.view.ui.user.dashboard.UserHome;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;


//Authentication and Authorization based functions (Firebase)
public class Auth {
    public String username, password;
    SharedData sharedData;

    @Inject
    public Auth() {
        sharedData = new SharedData();
    }


    public String typeOfUser(Context context) {
        String result = sharedData.getTypeOfUser(context);
        return result;
    }


    public void userLoginSetUser(Context context){
        sharedData.setTypeOfUser(context,"user");
    }

    //Firebase User logout
    public void userLogout(Context context) {
        FirebaseAuth.getInstance().signOut();
        sharedData.setUserLoggedIn(context,false);
        sharedData.setTypeOfUser(context,"");
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public void isUserLoggedIn(Context context){
        if(context.getClass().equals("MainActivity")){
            context.startActivity(new Intent(context, UserHome.class));
        }
    }


    public void checkUserIsAuthorized(Context context) {
        Log.d("method 2","Check if user authorized");

        DatabaseReference adminRef = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("admin");

        String className = context.getClass().getSimpleName();
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        Intent userHomeIntent = new Intent(context, UserHome.class);

        //Check if the user is logged in
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //this other && condition is for preventing recursion on activities
        if (user == null && className.equals("MainActivity") == false) {
            context.startActivity(mainActivityIntent);
        }

        String uid = user.getUid();

        adminRef.child("approved_user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isUserApproved = snapshot.child(uid).exists();
                if(isUserApproved){
                    sharedData.setTypeOfUser(context,"user");
                    sharedData.setUserLoggedIn(context,true);
                }

                if (isUserApproved && className.equals("UserHome") == false) {
                    context.startActivity(userHomeIntent);
                } else if (isUserApproved == false && className.equals("MainActivity") == false) {
                    context.startActivity(mainActivityIntent);
                } else {
                    //it is in the same class it has to be
                    //do nothing
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }



    /*********************** Admin Authentication *************************************/
    //Admin Login
    public void adminLogin(Context context) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("admin").child("credentials").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dbUsername = snapshot.child("username").getValue(String.class);
                String dbPassword = snapshot.child("password").getValue(String.class);
                boolean finalResult = dbUsername.equals(username) && dbPassword.equals(password) ? true : false;

                if (finalResult) {
                    //Authenticated
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                    //Save admin auth status
                    sharedData.setUserLoggedIn(context,true);
                    sharedData.setTypeOfUser(context,"admin");
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


    //Logout from admin
    public void adminLogout(Context context) {
        sharedData.setAdminLoggedIn(context , false);
        sharedData.setTypeOfUser(context ,"");
        context.startActivity(new Intent(context, MainActivity.class));

    }

    //Admin AUTH check
    public void isAdminLoggedIn(Context context) {
        boolean result = sharedData.isAdminLoggedIn(context);
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


    /*****************************************************************************************/
}
