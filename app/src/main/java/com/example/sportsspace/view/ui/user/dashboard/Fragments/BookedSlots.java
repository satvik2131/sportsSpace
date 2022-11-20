package com.example.sportsspace.view.ui.user.dashboard.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.BookedSlotsBinding;
import com.example.sportsspace.model.bookslot.BookSlot;
import com.example.sportsspace.view.ui.user.dashboard.adapter.BookedSlotsAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookedSlots extends Fragment {
    DatabaseReference userRequestReference;
    BookedSlotsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        BookedSlotsBinding bookedSlots = DataBindingUtil.inflate(
                inflater, R.layout.booked_slots, container, false
        );

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        userRequestReference = FirebaseDatabase.getInstance().getReference().child("slots");
        DatabaseReference finalRef = userRequestReference.orderByChild("uid").equalTo(uid).getRef();


        FirebaseRecyclerOptions<BookSlot> options
                = new FirebaseRecyclerOptions
                .Builder<BookSlot>()
                .setQuery(finalRef,BookSlot.class)
                .build();

        adapter = new BookedSlotsAdapter(options,getContext());
        bookedSlots.setBookedSlotAdapter(adapter);
        View view = bookedSlots.getRoot();
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
