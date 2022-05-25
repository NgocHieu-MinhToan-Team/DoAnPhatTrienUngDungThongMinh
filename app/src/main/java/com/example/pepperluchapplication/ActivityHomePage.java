package com.example.pepperluchapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.Fragments.fragmentHistory;
import com.example.pepperluchapplication.Fragments.fragmentHome;
import com.example.pepperluchapplication.Fragments.fragmentMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class ActivityHomePage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ImageView iv_cart;
    private ArrayList<CART> carts = new ArrayList<CART>();

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Ánh Xạ Views
        bottomNavigationView = findViewById(R.id.NavigationMenu);
        toolbar = findViewById(R.id.toolbar);
        iv_cart = findViewById(R.id.iv_cart);

        CART item = new CART(new PRODUCT("IDLMON01", "MAMON01", "https://firebasestorage.googleapis.com/v0/b/dbpepperlunch.appspot.com/o/image%2FPremiumSteak%2FTheGIANT.png?alt=media&token=b410306b-dfab-44f0-bb61-b465b422418d", "The Giant", "Bò Mỹ Thượng Hạng", (long) 369000), 1);
        carts.add(item);

        // Thiết lập views
        // Navigation bottom
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
                }
                loadFragment(fragment);
                return true;
            }
        });

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.icons8_menu_32);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Assign navigation view
        // Find our drawer view
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle
        drawerLayout.addDrawerListener(drawerToggle);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
    }

    // 'onPostCreate' called when activity start-up is complete after `onStart()`
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        // Pass any configuration change to the drawer toggles.
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadFragment(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frmMain, f);
        transaction.commit();
    }

    public void btnCart_Click(View view) {
        Intent intent = new Intent(ActivityHomePage.this, CartActivity.class);
        intent.putExtra("cart", carts);
        startActivity(intent);
    }

}