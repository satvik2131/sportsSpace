package com.example.sportsspace.view.ui.user.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.sportsspace.R;
import com.example.sportsspace.view.ui.user.dashboard.Fragments.BookSlots;
import com.example.sportsspace.view.ui.user.dashboard.Fragments.BookedSlots;
import com.example.sportsspace.view.ui.user.dashboard.Fragments.Dashboard;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserHome extends AppCompatActivity {

    BottomNavigationView navigationView;
    FragmentManager fragmentManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
        }


        //Setting NavBar Listener
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("Itm--", item.getTitle().toString());

                switch (item.getTitle().toString()) {
                    case "Dashboard":
                        item.setChecked(true);
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_container_view, Dashboard.class, null)
                                .setReorderingAllowed(true)
                                .commit();
                        break;
                    case "Book Slots":
                        item.setChecked(true);
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_container_view, BookSlots.class, null)
                                .setReorderingAllowed(true)
                                .commit();
                        break;
                    case "Booked Slots":
                        item.setChecked(true);
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_container_view, BookedSlots.class, null)
                                .setReorderingAllowed(true)
                                .commit();
                        break;
                }

                return false;
            }
        });
    }

}