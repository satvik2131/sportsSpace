package com.example.sportsspace.view.ui.admin.adminhome.Fragments.user_requests;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;

import java.util.ArrayList;
import java.util.List;

public class UserRequests extends Fragment {
    RecyclerView userRequests;

    public UserRequests(){
        super(R.layout.user_requests);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userRequests = view.findViewById(R.id.user_requests_recycler_view);

        List<String> usernames = new ArrayList<>();
        usernames.add("Harsh");
        usernames.add("Rahul");

        List<String> phoneno = new ArrayList<>();
        phoneno.add("899889345");
        phoneno.add("234234234");


        UserRequestsAdapter adapter = new UserRequestsAdapter(usernames,phoneno);
        userRequests.setLayoutManager(new LinearLayoutManager(getContext()));
        userRequests.setHasFixedSize(true);
        userRequests.setAdapter(adapter);
    }
}
