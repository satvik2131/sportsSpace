package com.example.sportsspace.view.ui.admin.adminhome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.sportsspace.R;
import com.example.sportsspace.view.ui.admin.adminhome.Fragments.add_info.AddInfoDashboard;
import com.example.sportsspace.view.ui.admin.adminhome.Fragments.add_sports.AddSports;
import com.example.sportsspace.view.ui.admin.adminhome.Fragments.existingremoveuser.ExistingRemoveUser;
import com.example.sportsspace.view.ui.admin.adminhome.Fragments.user_requests.UserRequests;
import com.example.sportsspace.view.ui.common.DashboardFragment;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AdminHome extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminhome);

        //Setup for opening and closing drawer
        drawerLayout = findViewById(R.id.admin_drawer_layout);
        toolbar = findViewById(R.id.admin_toolbar);
        navigationView = findViewById(R.id.admin_nav_view);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
        }


        //Fragments Graphing
        //Drawer Items listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("Titel--",item.getTitle().toString());

                switch (item.getTitle().toString()) {
                    case "Dashboard":
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.admin_fragment, DashboardFragment.class, null)
                                .setReorderingAllowed(true)
                                .commit();

                        drawerLayout.close();
                        break;
                    case "User Requests":
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.admin_fragment, UserRequests.class, null)
                                .setReorderingAllowed(true)
                                .commit();
                        drawerLayout.close();
                        break;
                    case "Add Sports":
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.admin_fragment, AddSports.class, null)
                                .setReorderingAllowed(true)
                                .commit();
                        drawerLayout.close();
                        break;

                    case "Add Info (Dashboard)":
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.admin_fragment, AddInfoDashboard.class, null)
                                .setReorderingAllowed(true)
                                .commit();
                        drawerLayout.close();
                        break;

                    case "Existing/Remove User":
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.admin_fragment, ExistingRemoveUser.class, null)
                                .setReorderingAllowed(true)
                                .commit();
                        drawerLayout.close();
                        break;

                    case "Logout":
                        //Implement Logout functionality here
                        break;
                }
                return false;
            }
        });



        //setting menu listener
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isOpen()) {
                    drawerLayout.close();
                } else {
                    drawerLayout.open();
                }
            }
        });

    }
}
