package com.example.sportsspace.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SportsMethod {

    public String sport;

    public void addSports(Context context){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("sports");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int countOfSports = (int) snapshot.getChildrenCount() + 1;
                String count = String.valueOf(countOfSports);
                reference.child(count).setValue(sport);

                Toast.makeText(context, "Sports Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }
}
