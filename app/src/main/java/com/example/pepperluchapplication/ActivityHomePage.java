package com.example.pepperluchapplication;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.CATEGORY;
import com.example.pepperluchapplication.DTO.CUSTOMER;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.Fragments.fragmentHistory;
import com.example.pepperluchapplication.Fragments.fragmentHome;
import com.example.pepperluchapplication.Fragments.fragmentMenu;
import com.example.pepperluchapplication.Fragments.fragment_change_password;
import com.example.pepperluchapplication.Fragments.fragment_customer_information;
import com.example.pepperluchapplication.Service.MyApplication;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ActivityHomePage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ImageView iv_cart;
    private ArrayList<CART> carts = new ArrayList<CART>();
    private Button buttonSignOut;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Ánh Xạ Views
        bottomNavigationView = findViewById(R.id.NavigationMenu);
        toolbar = findViewById(R.id.toolbar);
        iv_cart = findViewById(R.id.iv_cart);

        // Get customer information from shared preferences
        Gson gson = new Gson();
        String json = getSharedPreferences("USER", MODE_PRIVATE).getString("CUSTOMER", "");
        CUSTOMER customer = gson.fromJson(json, CUSTOMER.class);
        MyApplication.setCustomer(customer);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_information:
                    loadFragment(new fragment_customer_information(customer),
                            new fragmentHome(getApplicationContext()));
                    drawerLayout.closeDrawers();
                    break;
                case R.id.nav_change_password:
                    loadFragment(new fragment_change_password(), new fragmentHome(getApplicationContext()));
                    drawerLayout.closeDrawers();
                    break;
                case R.id.nav_loyalty:
                    new AlertDialog.Builder(this)
                            .setTitle("Điểm tích luỹ")
                            .setMessage("Điểm tích luỹ hiện tại của bạn là: " + customer.getPOINT())
                            .setPositiveButton("OK", null)
                            .show();
                    break;
                case R.id.nav_sign_out:
                    new AlertDialog.Builder(this)
                            .setTitle("Xác nhận đăng xuất")
                            .setMessage("Bạn chắc chắn muốn đăng xuất?")
                            .setPositiveButton("Có",
                                    (dialog, whichButton) -> {
                                        // Clear customer information
                                        getSharedPreferences("USER", MODE_PRIVATE).edit().remove(
                                                "CUSTOMER").commit();
                                        startActivity(new Intent(ActivityHomePage.this,
                                                ActivityLogin.class));
                                        finish();
                                    })
                            .setNegativeButton("Không", null).show();
                    break;
                default:
                    Toast.makeText(this, "Invalid selection", Toast.LENGTH_SHORT);
                    break;
            }
            return true;
        });
        // Set full name from customer login
        View hView = navigationView.getHeaderView(0);
        TextView fullNameUser = hView.findViewById(R.id.txt_full_name_nav);
        fullNameUser.setText("Xin chào, " + customer.getNAME_CUSTOMER());
        ImageView imageAvatar = hView.findViewById(R.id.pepperlunch_logo_nav);
        // Using Glide to fix error 'Canvas: trying to draw too large bitmap'
        Glide.with(this).load(R.drawable.pepperlunch_logo).into(imageAvatar);
//
//        CART item = new CART(new PRODUCT("IDLMON01", "MAMON01", "https://firebasestorage.googleapis.com/v0/b/dbpepperlunch.appspot.com/o/image%2FPremiumSteak%2FTheGIANT.png?alt=media&token=b410306b-dfab-44f0-bb61-b465b422418d", "The Giant", "Bò Mỹ Thượng Hạng", (long) 369000), 1);
//        carts.add(item);

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

    public void loadFragment(Fragment fragment, Fragment fragmentPrev) {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction().add(fragmentPrev,
                        fragmentPrev.getId() + "").addToBackStack(null);
        transaction.replace(R.id.frmMain, fragment);
        transaction.commit();
    }

    public void btnCart_Click(View view) {
        Intent intent = new Intent(ActivityHomePage.this, CartActivity.class);
        intent.putExtra("cart", carts);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "Popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "Nothing on backstack, so calling super");
            super.onBackPressed();
        }
    }
}