package com.example.sportsspace.view.ui.admin.adminhome.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.UserRequestCardBinding;
import com.example.sportsspace.model.userdata.CustomClickListener;
import com.example.sportsspace.model.userdata.UserData;
import com.firebase.ui.auth.data.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserRequestsAdapter extends FirebaseRecyclerAdapter<UserData, UserRequestsAdapter.ViewHolder> implements CustomClickListener {
    DatabaseReference reference;
    Context context ;

    public UserRequestsAdapter(@NonNull FirebaseRecyclerOptions<UserData> options , Context context) {
        super(options);
        this.context = context;
        reference = FirebaseDatabase.getInstance().getReference();
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        UserRequestCardBinding userRequestCardBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.user_request_card,viewGroup,false
        );

        return new ViewHolder(userRequestCardBinding);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull UserData model) {
        viewHolder.userRequestCardBinding.setUserRequest(model);
        viewHolder.userRequestCardBinding.executePendingBindings();
        viewHolder.userRequestCardBinding.setItemClickListener(this);
    }


    @Override
    public void userApproved(UserData f) {
        reference
                .child("admin")
                .child("user_requests")
                .child(f.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserData data = snapshot.getValue(UserData.class);

                        //Move data to new database
                        DatabaseReference approvedUser = reference.child("admin").child("approved_user");
                        if(approvedUser!=null){
                            approvedUser.child(data.getUid()).setValue(data);

                            //Remove data from this database
                            removeUser(reference.child("admin").child("user_requests").child(f.getUid()));
                            Toast.makeText(context, "User Approved", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    public void removeUser(DatabaseReference reference){
        reference.removeValue();
    }


    @Override
    public void userDeclined(UserData f) {
        removeUser(reference.child("admin").child("user_requests").child(f.getUid()));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public UserRequestCardBinding userRequestCardBinding;

        public ViewHolder(UserRequestCardBinding binding) {
            super(binding.getRoot());
            this.userRequestCardBinding = binding;
        }
    }

}
