package com.example.sportsspace.view.ui.user.dashboard.Fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;
import com.example.sportsspace.view.ui.user.dashboard.utils.BookedSlotsAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookedSlots extends Fragment {
    RecyclerView recyclerView;

    public BookedSlots(){
        super(R.layout.booked_slots_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.booked_slot_recyclerview);

        //SAMPLE DATA
        List<String> sports = new ArrayList<>();
        sports.add("Cricket");
        sports.add("Football");
        List<String> date = new ArrayList<>();
        date.add("23-12-22");
        date.add("24-12-22");
        List<String> time = new ArrayList<>();
        time.add("9:09 AM");
        time.add("10:23 AM");
        List<String> duration = new ArrayList<>();
        duration.add("2 hours");
        duration.add("3 hours");

        BookedSlotsAdapter adapter = new BookedSlotsAdapter(sports,date,time,duration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
}
