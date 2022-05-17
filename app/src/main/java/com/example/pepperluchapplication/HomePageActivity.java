package com.example.pepperluchapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePageActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bottomNavigationView = findViewById(R.id.btnNav);
        bottomNavigationView.setSelectedItemId(R.id.mnuHome);
        loadFragment(new fragmentHome(this));

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.mnuHome:
                        fragment = new fragmentHome(getApplicationContext());
                        break;
                    case R.id.mnuMenu:
                        fragment = new fragmentMenu(getApplicationContext());
                        break;
                    case R.id.mnuHistory:
                        fragment = new fragmentHistory(getApplicationContext());
                        break;
                    case R.id.mnuProfile:
                        fragment = new FragmentProfile(getApplicationContext());
                        break;

                }
                loadFragment(fragment);
                return true;
            }
        });
    }

    public void loadFragment(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frmMain, f);
        transaction.commit();
    }
}