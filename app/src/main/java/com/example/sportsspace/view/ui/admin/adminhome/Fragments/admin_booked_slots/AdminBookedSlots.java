package com.example.sportsspace.view.ui.admin.adminhome.Fragments.admin_booked_slots;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.example.sportsspace.R;
import com.example.sportsspace.databinding.AdminBookedSlotsBinding;
import com.example.sportsspace.model.bookslot.BookSlot;
import com.example.sportsspace.view.ui.admin.adminhome.adapter.AdminBookedSlotAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminBookedSlots extends Fragment {
    DatabaseReference adminBookedSlotRef;
    AdminBookedSlotAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        AdminBookedSlotsBinding adminBookedSlotBinding = DataBindingUtil.inflate(
                inflater, R.layout.admin_booked_slots, container, false
        );

        adminBookedSlotRef = FirebaseDatabase.getInstance().getReference().child("slots");

        FirebaseRecyclerOptions<BookSlot> options
                = new FirebaseRecyclerOptions
                .Builder<BookSlot>()
                .setQuery(adminBookedSlotRef, BookSlot.class)
                .build();

        adapter = new AdminBookedSlotAdapter(options,getContext());
        adminBookedSlotBinding.setBookedSlotAdapter(adapter);
        View view = adminBookedSlotBinding.getRoot();
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
