package com.example.sportsspace.view.ui.admin.adminhome.Fragments.existingremoveuser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.example.sportsspace.R;
import com.example.sportsspace.databinding.ExistingRemoveUserBinding;
import com.example.sportsspace.model.userdata.UserData;
import com.example.sportsspace.view.ui.admin.adminhome.adapter.ExistingUsersAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ExistingRemoveUser extends Fragment {
    DatabaseReference existingUserReference;
    ExistingUsersAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ExistingRemoveUserBinding existingUserBiding = DataBindingUtil.inflate(
                inflater, R.layout.existing_remove_user, container, false
        );

        existingUserReference = FirebaseDatabase.getInstance().getReference().child("/admin/approved_user");

        FirebaseRecyclerOptions<UserData> options
                = new FirebaseRecyclerOptions
                .Builder<UserData>()
                .setQuery(existingUserReference,UserData.class)
                .build();

        adapter = new ExistingUsersAdapter(options,getContext());
        existingUserBiding.setExistingUserAdapter(adapter);
        View view = existingUserBiding.getRoot();
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
