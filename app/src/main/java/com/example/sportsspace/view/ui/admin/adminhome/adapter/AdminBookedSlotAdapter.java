package com.example.sportsspace.view.ui.admin.adminhome.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sportsspace.R;
import com.example.sportsspace.databinding.AdminBookedSlotsCardBinding;
import com.example.sportsspace.model.bookslot.BookSlot;
import com.example.sportsspace.model.bookslot.SlotsListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminBookedSlotAdapter extends FirebaseRecyclerAdapter<BookSlot, AdminBookedSlotAdapter.ViewHolder> implements SlotsListener {
    Context context ;

    public AdminBookedSlotAdapter(@NonNull FirebaseRecyclerOptions<BookSlot> options , Context context) {
        super(options);
        this.context = context;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        AdminBookedSlotsCardBinding admiBookedSlotCardViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.admin_booked_slots_card,viewGroup,false
        );

        return new ViewHolder(admiBookedSlotCardViewBinding);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull BookSlot model) {
        viewHolder.admiBookedSlotCardViewBinding.setBookedSlot(model);
        viewHolder.admiBookedSlotCardViewBinding.setItemClickListener(this);
        viewHolder.admiBookedSlotCardViewBinding.executePendingBindings();
    }

    @Override
    public void removeSlot(BookSlot b) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        String date = b.date.replace(" ","_");
        String slot_id = String.valueOf(
                date +b.startHour +""+ b.startMinute +""  + b.endHour +"" + b.endMinute);

        Log.d("slotID===",slot_id);
        reference.child("slots").child(slot_id).removeValue();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AdminBookedSlotsCardBinding admiBookedSlotCardViewBinding;

        public ViewHolder(AdminBookedSlotsCardBinding binding) {
            super(binding.getRoot());
            this.admiBookedSlotCardViewBinding = binding;
        }
    }

}