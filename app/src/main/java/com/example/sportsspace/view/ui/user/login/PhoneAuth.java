package com.example.sportsspace.view.ui.user.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportsspace.MainActivity;
import com.example.sportsspace.model.userdata.UserData;
import com.example.sportsspace.utils.Auth;
import com.example.sportsspace.utils.SharedData;
import com.example.sportsspace.view.ui.user.dashboard.UserHome;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class PhoneAuth extends AppCompatActivity {

    DatabaseReference reference;
    String name;
    SharedData data;
    @Inject
    Auth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new SharedData(this);
        reference = FirebaseDatabase.getInstance().getReference().child("admin");

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build()
        );

        //Dialog box to get username
        loginPage(providers);

    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String key = user.getUid();
            UserData userData = new UserData(name, user.getPhoneNumber(), key, false, false);
            //If user is already registered , don't save new node to database
            checkIfUserExist(key, userData);


            auth.checkUserIsAuthorized(this);


        } else {
            Toast.makeText(this, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    //If user exists , don't create redundant data
    public void checkIfUserExist(String key, UserData userData) {
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean userReqExistenceCheck = (snapshot.child("user_requests").child(key).exists());
                boolean approvedUserExistenceCheck = (snapshot.child("approved_user").child(key).exists());

                if (userReqExistenceCheck == false &&
                        approvedUserExistenceCheck == false
                ) {
                    reference.child("user_requests").child(key).setValue(userData);
                } else {
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    //Login ui with username popup
    public void loginPage(List<AuthUI.IdpConfig> providers) {
        AlertDialog.Builder boite;
        boite = new AlertDialog.Builder(this);
        boite.setTitle("Enter Your Name");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        boite
                .setView(input)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (input.toString().isEmpty()) {
                            Toast.makeText(PhoneAuth.this, "Enter name", Toast.LENGTH_SHORT).show();
                        } else {
                            name = input.getText().toString();

                            // Create and launch sign-in intent
                            Intent signInIntent = AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build();
                            signInLauncher.launch(signInIntent);

                        }
                    }
                }).show();
    }
}
