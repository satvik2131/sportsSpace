package com.example.sportsspace.model.dashboardmodel;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class DashboardMethods {
    public String title , description ;
    public Date timestamp;
    private DatabaseReference reference;
    private String dashboardPath;
    private Context context;

    @Inject
    public DashboardMethods(@ApplicationContext Context appContext){
        this.context = appContext;
        reference = FirebaseDatabase.getInstance().getReference();
        dashboardPath = "/dashboard";
    }


    //Admin -> Add Information to dashboard
    //Implement date sorting
    public boolean addToDashboard(String title, String description){
        try{

            Date date = new Date();
            DashboardModel data = new DashboardModel(title,description,date);
            //getting the key first with a push then uploading data to the key
            String key = reference.child(dashboardPath).push().getKey();
            reference.child(dashboardPath).child(key).setValue(data);

            //Success
            Toast.makeText(context, "Added to Dashboard", Toast.LENGTH_SHORT).show();

            return true;
        }catch (Exception e){
            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }



    //Get Dashboard information
    public void getDashboardInfo(){
        reference.child(dashboardPath).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<DashboardModel> dashboardModelList = new ArrayList<>();
                for(DataSnapshot snap: snapshot.getChildren()){
                    DashboardModel data = snap.getValue(DashboardModel.class);
                    Date date = new Date();
                    dashboardModelList.add(new DashboardModel(data.getTitle(),data.getDescription(),date));
                }

                Log.d("hogya--",dashboardModelList.get(0).getTitle());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
