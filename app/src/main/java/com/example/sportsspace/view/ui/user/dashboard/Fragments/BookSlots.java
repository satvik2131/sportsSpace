package com.example.sportsspace.view.ui.user.dashboard.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.AddSportsBinding;
import com.example.sportsspace.databinding.BookSlotFragmentBinding;
import com.example.sportsspace.viewmodels.BookSlotVM;
import com.example.sportsspace.viewmodels.SportsMethod;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookSlots extends Fragment {
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BookSlotFragmentBinding bookSlot = DataBindingUtil.inflate(
                inflater, R.layout.book_slot_fragment, container, false
        );

        fragmentManager = getParentFragmentManager();

        DatabaseReference sportsReference = FirebaseDatabase.getInstance()
                .getReference().child("sports");

        sportsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> sports = new ArrayList<String>();

                for (DataSnapshot snap : snapshot.getChildren()) {
                    String sportsData = snap.getValue(String.class);
                    sports.add(sportsData);
                }

                ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.list_item, sports);
                bookSlot.selectSport.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        bookSlot.setBookSlot(new BookSlotVM(fragmentManager,getContext()));
        View view = bookSlot.getRoot();
        bookSlot.executePendingBindings();
        return view;
    }
}

