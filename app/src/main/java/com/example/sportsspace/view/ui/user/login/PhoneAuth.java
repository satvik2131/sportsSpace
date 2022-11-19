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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportsspace.model.userdata.UserData;
import com.example.sportsspace.utils.Auth;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class PhoneAuth extends AppCompatActivity {

    DatabaseReference reference;
    String name;
    @Inject
    Auth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reference = FirebaseDatabase.getInstance().getReference().child("admin").child("user_requests");

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
            Log.d("name---",name);

            UserData userData = new UserData(name, user.getPhoneNumber(), key, false, false);
            reference.child(key).setValue(userData);

            //Create a check function which checks if the user is approved by the admin
            auth.checkIfUserIsApproved(user.getUid(), reference, this);

        } else {

            Toast.makeText(this, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


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
