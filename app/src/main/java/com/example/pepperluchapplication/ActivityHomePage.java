package com.example.pepperluchapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.Fragments.fragmentHistory;
import com.example.pepperluchapplication.Fragments.fragmentHome;
import com.example.pepperluchapplication.Fragments.fragmentMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class ActivityHomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment fragment;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageView iv_cart;
    ArrayList<CART> carts=new ArrayList<CART>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Ánh Xạ Views
        bottomNavigationView=findViewById(R.id.NavigationMenu);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        iv_cart=findViewById(R.id.iv_cart);

//        CART item= new CART(new PRODUCT("IDLMON01","MAMON01","https://firebasestorage.googleapis.com/v0/b/dbpepperlunch.appspot.com/o/image%2FPremiumSteak%2FTheGIANT.png?alt=media&token=b410306b-dfab-44f0-bb61-b465b422418d","The Giant","Bò Mỹ Thượng Hạng",(long)369000),1);
//        carts.add(item);
//        item= new CART(new PRODUCT("IDLMON01","MAMON01","https://firebasestorage.googleapis.com/v0/b/dbpepperlunch.appspot.com/o/image%2FPremiumSteak%2FTheGIANT.png?alt=media&token=b410306b-dfab-44f0-bb61-b465b422418d","The Giant","Bò Mỹ Thượng Hạng",(long)369000),2);
//        carts.add(item);
//        item= new CART(new PRODUCT("IDLMON01","MAMON01","https://firebasestorage.googleapis.com/v0/b/dbpepperlunch.appspot.com/o/image%2FPremiumSteak%2FTheGIANT.png?alt=media&token=b410306b-dfab-44f0-bb61-b465b422418d","The Giant","Bò Mỹ Thượng Hạng",(long)369000),3);
//        carts.add(item);

        // thiết lập views
        // Navigation bottom
        bottomNavigationView.setSelectedItemId(R.id.mnuHome);
        loadFragment(new fragmentHome(this));
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id)
                {
                    case R.id.mnuHome: fragment=new fragmentHome(getApplicationContext());
                        break;
                    case R.id.mnuMenu: fragment=new fragmentMenu(getApplicationContext());
                        break;
                    case R.id.mnuHistory: fragment=new fragmentHistory(getApplicationContext());
                        break;
                }
                loadFragment(fragment);
                return true;
            }
        });
        // toolbar
        this.setSupportActionBar(toolbar);
        // gan nut mo menu ra
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.icons8_menu_32);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    public void loadFragment(Fragment f)
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frmMain,f);
        transaction.commit();
    }
    public void btnCart_Click(View view)
    {
        Intent intent= new Intent(ActivityHomePage.this,CartActivity.class);
        //intent.putExtra("cart", MyApplication.getCarts());
        startActivity(intent);
    }

}