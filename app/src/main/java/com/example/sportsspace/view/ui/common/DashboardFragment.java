package com.example.sportsspace.view.ui.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.DashboardFragmentBinding;
import com.example.sportsspace.model.dashboardmodel.DashboardModel;
import com.example.sportsspace.viewmodels.DashboardMethods;
import com.example.sportsspace.view.ui.user.dashboard.adapter.DashBoardListAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragment extends Fragment {

    @Inject
    DashboardMethods dashboardMethods;

    DatabaseReference dashboardRef;
    DashBoardListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DashboardFragmentBinding dashboardBinding = DataBindingUtil.inflate(
                inflater, R.layout.dashboard_fragment, container, false
        );

        dashboardRef = FirebaseDatabase.getInstance().getReference().child("dashboard");

        FirebaseRecyclerOptions<DashboardModel> options
                = new FirebaseRecyclerOptions
                .Builder<DashboardModel>()
                .setQuery(dashboardRef,DashboardModel.class)
                .build();

        adapter = new DashBoardListAdapter(options);
        dashboardBinding.setMyAdapter(adapter);
        View view = dashboardBinding.getRoot();

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
