package com.example.sportsspace.model.sportsModel;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SportsMethod {

    public String sport;

    public void addSports(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("sports");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int countOfSports = (int) snapshot.getChildrenCount() + 1;
                String count = String.valueOf(countOfSports);
                reference.child(count).setValue(sport);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }
}
