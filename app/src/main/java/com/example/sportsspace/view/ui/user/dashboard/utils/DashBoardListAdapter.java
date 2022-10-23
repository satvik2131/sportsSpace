package com.example.sportsspace.view.ui.user.dashboard.utils;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;

import java.util.List;

public class DashBoardListAdapter extends RecyclerView.Adapter<DashBoardListAdapter.ViewHolder> {

    private List<String> heading;
    private List<String> content;
    private List<String> date;
    private List<String> time;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView headerTV , contentTV , dateTV, timeTV;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            headerTV = (TextView) view.findViewById(R.id.headerTV);
            contentTV = (TextView) view.findViewById(R.id.contentTV);
            dateTV = (TextView) view.findViewById(R.id.dateTV);
            timeTV = (TextView) view.findViewById(R.id.timeTV);
        }
    }


    public DashBoardListAdapter(List<String> heading,
                                List<String> content,
                                List<String> date ,
                                List<String> time) {
        this.heading = heading;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dashboard_card_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.headerTV.setText(heading.get(position));
        viewHolder.contentTV.setText(content.get(position));
        viewHolder.dateTV.setText(date.get(position));
        viewHolder.timeTV.setText(time.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return heading.size();
    }
}

