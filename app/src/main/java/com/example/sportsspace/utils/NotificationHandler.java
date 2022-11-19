package com.example.sportsspace.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.sportsspace.R;
import com.example.sportsspace.model.userdata.UserData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

public class NotificationHandler {
    DatabaseReference reference;
    @Inject
    Auth auth;

    @Inject
    public NotificationHandler(){}

    public void userAddListener(Context context){
        reference = FirebaseDatabase.getInstance().getReference().child("admin").child("user_requests");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                UserData userData = snapshot.getValue(UserData.class);

                if(auth.typeOfUser(context).equals("Admin")){
                    if(!userData.isSeen()){
                        //Create a notification
                        notification(context);
                    }
                }else{
                    return;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }


    private void notification(Context context) {
        String CHANNEL_ID = "osmos";
        NotificationChannel channel;
        NotificationManager notificationManager;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "user update";
            String description = "if user gets registered";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.accept_user)
                .setContentTitle("SportsSpace")
                //Update text here with username
                .setContentText("A new user is added")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(1,builder.build());
    }



}
