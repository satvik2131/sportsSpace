package com.example.sportsspace.view.ui.user.dashboard.Fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;
import com.example.sportsspace.view.ui.user.dashboard.utils.DashBoardListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Fragment {
    RecyclerView recyclerView;

    public Dashboard() {
        super(R.layout.dashboard_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.dashboard_list);

        //SAMPLE DATA
        List<String> heading = new ArrayList<>();
        heading.add("Notice");
        heading.add("Warning");
        List<String> content = new ArrayList<>();
        content.add("All the students are informed today is holiday");
        content.add("Ayush Verma is resticated for his misbehaviour");
        List<String> date = new ArrayList<>();
        date.add("22-02-21");
        date.add("05-12-21");
        List<String> time = new ArrayList<>();
        time.add("9:30 AM");
        time.add("10:00 PM");

        DashBoardListAdapter adapter = new DashBoardListAdapter(heading,content,date,time);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
