package com.example.datesreminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransitionImpl;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Toolbar toolbar;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawerLayout , toolbar , R.string.navigation_drawer_open ,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId == R.id.button_today) {
                    openFragment(new TodayFragment());
                    return true;
                }   else if(itemId == R.id.button_all) {
                    openFragment(new AllFragment());
                    return true;
                }   else if(itemId == R.id.button_postponed) {
                    openFragment(new AllFragment());
                    return true;
                }   else if(itemId == R.id.button_settings) {
                    openFragment(new SettingsFragment());
                    return true;
                }

                return false;
            }
        });
        fragmentManager = getSupportFragmentManager();
        openFragment(new TodayFragment());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this , "Go To Add New", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.nav_agents) {
            openFragment(new AgentsFragment());
        } else if (itemId == R.id.nav_add_agent) {
            openFragment(new AddAgentFragment());
        } else if (itemId == R.id.nav_dependencies) {
            openFragment(new DependenciesFragment());
        } else if (itemId == R.id.nav_add_dependency) {
            openFragment(new AddDependencyFragment());
        } else if (itemId == R.id.nav_report) {
            openFragment(new ReportFragment());
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }



    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();

        }
    }
    private  void openFragment(Fragment fragment) {
        FragmentTransaction transaction =  fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container , fragment);
        transaction.commit();

    }
}