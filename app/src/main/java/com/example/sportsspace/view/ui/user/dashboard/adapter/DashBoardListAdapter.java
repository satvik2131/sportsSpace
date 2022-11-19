package com.example.sportsspace.view.ui.user.dashboard.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.DashboardCardViewBinding;
import com.example.sportsspace.model.dashboardmodel.DashboardModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DashBoardListAdapter extends FirebaseRecyclerAdapter<DashboardModel , DashBoardListAdapter.ViewHolder> {

    public DashBoardListAdapter(@NonNull FirebaseRecyclerOptions<DashboardModel> options) {
        super(options);
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        DashboardCardViewBinding dashboardCardViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.dashboard_card_view,viewGroup,false
        );

        return new ViewHolder(dashboardCardViewBinding);
    }


    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//
//    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull DashboardModel model) {
        viewHolder.dashboardCardViewBinding.setDashboardmodel(model);
        viewHolder.dashboardCardViewBinding.executePendingBindings();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public DashboardCardViewBinding dashboardCardViewBinding;

        public ViewHolder(DashboardCardViewBinding binding) {
            super(binding.getRoot());
            this.dashboardCardViewBinding = binding;

        }
    }

}

