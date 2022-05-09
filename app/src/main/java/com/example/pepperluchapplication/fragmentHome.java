package com.example.pepperluchapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class fragmentHome extends Fragment {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    Context context;
    RecyclerView rv_bestSeller;
    TabLayout tabLayout;
    ViewPager viewPager;
    String[] category = {"Premium Steak","Pepper Rice","Combo Special"};
    public fragmentHome(Context context)
    {
        this.context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // anh xa
        toolbar = view.findViewById(R.id.toolbar);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        rv_bestSeller=view.findViewById(R.id.rv_bestSeller);

        tabLayout=view.findViewById(R.id.tablayout);
        viewPager= view.findViewById(R.id.viewpager);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        // gan nut mo menu ra
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.icons8_menu_32);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // create data test
        ArrayList<Product> data = new ArrayList<>();
        data.add(new Product(R.drawable.beef_sukiyaki,"Beef PPR"));
        data.add(new Product(R.drawable.beef_sukiyaki,"Chicken Salmon"));
        data.add(new Product(R.drawable.beef_sukiyaki,"Beef Steak"));
        data.add(new Product(R.drawable.beef_sukiyaki,"Cuoi Cung"));
        // binding data by adapter
        RV_BestSellerAdapter adapter = new RV_BestSellerAdapter(this.getContext(),data);
        rv_bestSeller.setAdapter(adapter);
        rv_bestSeller.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        // data for fragment 1 or category 1
        ArrayList<Product> data1 = new ArrayList<>();
        data1.add(new Product(R.drawable.beef_sukiyaki,"Beef PPR 1 "));
        data1.add(new Product(R.drawable.beef_sukiyaki,"Chicken Salmon 1"));
        data1.add(new Product(R.drawable.beef_sukiyaki,"Beef Steak 1"));
        data1.add(new Product(R.drawable.beef_sukiyaki,"Cuoi Cung 1"));
        // data for fragment 2 or category 2
        ArrayList<Product> data2 = new ArrayList<>();
        data2.add(new Product(R.drawable.beef_sukiyaki,"Beef PPR 2"));
        data2.add(new Product(R.drawable.beef_sukiyaki,"Chicken Salmon 2 "));
        data2.add(new Product(R.drawable.beef_sukiyaki,"Beef Steak 2"));
        data2.add(new Product(R.drawable.beef_sukiyaki,"Cuoi Cung 2"));
        // data for fragment 2 or category 2
        ArrayList<Product> data3 = new ArrayList<>();
        data3.add(new Product(R.drawable.beef_sukiyaki,"Beef PPR 3"));
        data3.add(new Product(R.drawable.beef_sukiyaki,"Chicken Salmon 3"));
        data3.add(new Product(R.drawable.beef_sukiyaki,"Beef Steak 3"));
        data3.add(new Product(R.drawable.beef_sukiyaki,"Cuoi Cung 3"));
        List<ArrayList<Product>> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        list.add(data3);
        // set up
        tabLayout.setupWithViewPager(viewPager);
        ViewPager_CategoryAdapter categoryAdapter = new ViewPager_CategoryAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        // loop through array title to create n title , n fragment
        for (int i=0;i<category.length;i++){
            categoryAdapter.addFragment(new fragment_product_item(list.get(i)),category[i]);
        }
        viewPager.setAdapter(categoryAdapter);
        //set icon
        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_bookmark_24);
        registerForContextMenu(tabLayout);
    }

}