package com.example.sportsspace.view.ui.user.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.sportsspace.R;
import com.example.sportsspace.view.ui.user.dashboard.Fragments.BookSlots;
import com.example.sportsspace.view.ui.user.dashboard.Fragments.BookedSlots;
import com.example.sportsspace.view.ui.common.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
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


        //User Navigation
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getTitle().toString()) {
                    case "Dashboard":
                        item.setChecked(true);
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_container_view, DashboardFragment.class, null)
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
