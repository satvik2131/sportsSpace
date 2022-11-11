package com.example.sportsspace.view.ui.user.dashboard.utils;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.DashboardCardViewBinding;
import com.example.sportsspace.model.dashboardmodel.DashboardModel;

import java.util.List;

public class DashBoardListAdapter extends RecyclerView.Adapter<DashBoardListAdapter.ViewHolder> {

    private List<DashboardModel> allNotice;
    public DashBoardListAdapter(List<DashboardModel> allNotice) {
        this.allNotice = allNotice;
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
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        DashboardModel dataModel = allNotice.get(position);
        viewHolder.dashboardCardViewBinding.setDashboardmodel(dataModel);
        viewHolder.dashboardCardViewBinding.executePendingBindings();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public DashboardCardViewBinding dashboardCardViewBinding;

        public ViewHolder(DashboardCardViewBinding binding) {
            super(binding.getRoot());
            this.dashboardCardViewBinding = binding;

        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return allNotice.size();
    }
}

