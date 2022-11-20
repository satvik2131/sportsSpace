package com.example.sportsspace.view.ui.admin.adminhome.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sportsspace.R;
import com.example.sportsspace.databinding.ExistingUserCardBinding;
import com.example.sportsspace.model.userdata.CustomClickListener;
import com.example.sportsspace.model.userdata.UserData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class ExistingUsersAdapter extends FirebaseRecyclerAdapter<UserData, ExistingUsersAdapter.ViewHolder> implements CustomClickListener {
    DatabaseReference reference;
    Context context ;

    public ExistingUsersAdapter(@NonNull FirebaseRecyclerOptions<UserData> options , Context context) {
        super(options);
        this.context = context;
        reference = FirebaseDatabase.getInstance().getReference();
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ExistingUserCardBinding existingUserCardBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.existing_user_card,viewGroup,false
        );

        return new ViewHolder(existingUserCardBinding);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull UserData model) {
        viewHolder.existingUserCardBinding.setExistingRequest(model);
        viewHolder.existingUserCardBinding.executePendingBindings();
        viewHolder.existingUserCardBinding.setItemClickListener(this);
    }


    public void removeUser(DatabaseReference reference){
        reference.removeValue();
    }


    @Override
    public void userApproved(UserData f) {

    }

    @Override
    public void userDeclined(UserData f) {
        removeUser(reference.child("admin").child("approved_user").child(f.getUid()));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ExistingUserCardBinding existingUserCardBinding;

        public ViewHolder(ExistingUserCardBinding binding) {
            super(binding.getRoot());
            this.existingUserCardBinding = binding;
        }
    }

}