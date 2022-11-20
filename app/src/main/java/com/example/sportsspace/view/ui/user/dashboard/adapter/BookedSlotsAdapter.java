package com.example.sportsspace.view.ui.user.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.BookedSlotCardBinding;
import com.example.sportsspace.model.bookslot.BookSlot;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BookedSlotsAdapter extends FirebaseRecyclerAdapter<BookSlot, BookedSlotsAdapter.ViewHolder> {
    Context context ;

    public BookedSlotsAdapter(@NonNull FirebaseRecyclerOptions<BookSlot> options , Context context) {
        super(options);
        this.context = context;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        BookedSlotCardBinding bookedSlotCardViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.booked_slot_card,viewGroup,false
        );

        return new ViewHolder(bookedSlotCardViewBinding);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull BookSlot model) {
        viewHolder.bookedSlotCardViewBinding.setBookedSlot(model);
        viewHolder.bookedSlotCardViewBinding.executePendingBindings();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public BookedSlotCardBinding bookedSlotCardViewBinding;

        public ViewHolder(BookedSlotCardBinding binding) {
            super(binding.getRoot());
            this.bookedSlotCardViewBinding = binding;
        }
    }

}

