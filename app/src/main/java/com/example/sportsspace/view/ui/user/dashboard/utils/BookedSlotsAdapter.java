package com.example.sportsspace.view.ui.user.dashboard.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;

import java.util.List;

public class BookedSlotsAdapter extends RecyclerView.Adapter<BookedSlotsAdapter.ViewHolder> {

    private List<String> sports;
    private List<String> dateOfSlot;
    private List<String> timeOfSlot;
    private List<String> durationOfSlot;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView sportsTV ,dateOfSlotTV , timeOfSlotTV , durationOfSlotTV;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            sportsTV = (TextView) view.findViewById(R.id.selected_sports);
            dateOfSlotTV = (TextView) view.findViewById(R.id.dateofslot);
            timeOfSlotTV = (TextView) view.findViewById(R.id.timeofslot);
            durationOfSlotTV = (TextView) view.findViewById(R.id.durationofslot);
        }
    }


    public BookedSlotsAdapter(List<String> sports,
                                List<String> dateOfSlot,
                                List<String> timeOfSlot ,
                                List<String> durationOfSlot) {
        this.sports = sports;
        this.dateOfSlot = dateOfSlot;
        this.timeOfSlot = timeOfSlot;
        this.durationOfSlot = durationOfSlot;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.booked_slot_card_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.sportsTV.setText(sports.get(position));
        viewHolder.dateOfSlotTV.setText(dateOfSlot.get(position));
        viewHolder.timeOfSlotTV.setText(timeOfSlot.get(position));
        viewHolder.durationOfSlotTV.setText(durationOfSlot.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return sports.size();
    }
}

