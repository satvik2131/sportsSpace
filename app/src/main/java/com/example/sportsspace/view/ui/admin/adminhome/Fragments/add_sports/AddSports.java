package com.example.sportsspace.view.ui.admin.adminhome.Fragments.add_sports;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.AddSportsBinding;
import com.example.sportsspace.model.sportsModel.SportsData;
import com.example.sportsspace.model.sportsModel.SportsMethod;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddSports extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AddSportsBinding addSportsBindig = DataBindingUtil.inflate(
                inflater , R.layout.add_sports ,container , false
        );

        DatabaseReference sportsReference = FirebaseDatabase.getInstance()
                .getReference().child("sports");

        sportsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> sports = new ArrayList<String>();

                for (DataSnapshot snap : snapshot.getChildren()){
                    String sportsData = snap.getValue(String.class);
                    sports.add(sportsData);
                }

                ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.list_item, sports);
                addSportsBindig.existingSports.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        addSportsBindig.setSportsModel(new SportsMethod());
        View view = addSportsBindig.getRoot();
        addSportsBindig.executePendingBindings();
        return view;
    }
}
