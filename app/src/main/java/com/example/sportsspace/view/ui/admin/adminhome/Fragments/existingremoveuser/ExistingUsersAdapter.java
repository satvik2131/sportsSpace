package com.example.sportsspace.view.ui.admin.adminhome.Fragments.existingremoveuser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsspace.R;

import java.util.List;

public class ExistingUsersAdapter extends RecyclerView.Adapter<com.example.sportsspace.view.ui.admin.adminhome.Fragments.existingremoveuser.ExistingUsersAdapter.ViewHolder> {
    private List<String> username;
    private List<String> phoneno;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView usernameTV ,phonenoTV ;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            usernameTV = (TextView) view.findViewById(R.id.existing_user_username);
            phonenoTV = (TextView) view.findViewById(R.id.existing_user_phoneno);

        }
    }


    public ExistingUsersAdapter(List<String> username, List<String> phoneno) {
        this.username = username;
        this.phoneno = phoneno;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public com.example.sportsspace.view.ui.admin.adminhome.Fragments.existingremoveuser.ExistingUsersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_request_card, viewGroup, false);

        return new com.example.sportsspace.view.ui.admin.adminhome.Fragments.existingremoveuser.ExistingUsersAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(com.example.sportsspace.view.ui.admin.adminhome.Fragments.existingremoveuser.ExistingUsersAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.usernameTV.setText(username.get(position));
        viewHolder.phonenoTV.setText(phoneno.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return username.size();
    }
}