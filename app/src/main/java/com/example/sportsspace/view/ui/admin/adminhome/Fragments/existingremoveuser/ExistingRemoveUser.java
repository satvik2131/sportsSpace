package com.example.sportsspace.view.ui.admin.adminhome.Fragments.existingremoveuser;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;
import com.example.sportsspace.view.ui.admin.adminhome.Fragments.user_requests.UserRequestsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExistingRemoveUser extends Fragment {

    RecyclerView existingUsers;

    public ExistingRemoveUser(){
        super(R.layout.existing_remove_user);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        existingUsers = view.findViewById(R.id.existing_user_recycler_view);

        List<String> usernames = new ArrayList<>();
        usernames.add("Harsh");
        usernames.add("Rahul");

        List<String> phoneno = new ArrayList<>();
        phoneno.add("899889345");
        phoneno.add("234234234");


        UserRequestsAdapter adapter = new UserRequestsAdapter(usernames,phoneno);
        existingUsers.setLayoutManager(new LinearLayoutManager(getContext()));
        existingUsers.setHasFixedSize(true);
        existingUsers.setAdapter(adapter);
    }
}
