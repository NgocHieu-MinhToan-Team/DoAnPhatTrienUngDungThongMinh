package com.example.pepperluchapplication.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.pepperluchapplication.Adapter.RV_BestSellerAdapter;
import com.example.pepperluchapplication.Adapter.ViewPager_CategoryAdapter;
import com.example.pepperluchapplication.DTO.Category;
import com.example.pepperluchapplication.DTO.Product;
import com.example.pepperluchapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fragmentHome extends Fragment {
    Context context;
    RecyclerView rv_bestSeller;
    TabLayout tabLayout;
    ViewPager viewPager;

    public fragmentHome(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Mapping
        rv_bestSeller = view.findViewById(R.id.rv_bestSeller);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dbpepperlunch-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Database/Category");

        ArrayList<Category> dataOfCategory = new ArrayList<>();
        ArrayList<Product> dataOfProduct = new ArrayList<>();

        // Initialize and set adapter for ViewPager
        ViewPager_CategoryAdapter viewPager_categoryAdapter =
                new ViewPager_CategoryAdapter(((AppCompatActivity) getActivity()).getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager_categoryAdapter.addFragment(new fragment_product_item(dataOfProduct), "Tab 1");
        viewPager.setAdapter(viewPager_categoryAdapter);

        // Initialize and set adapter fo RV_BestSeller
        RV_BestSellerAdapter bestSellerAdapter = new RV_BestSellerAdapter(getActivity(), dataOfProduct);
        rv_bestSeller.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_bestSeller.setAdapter(bestSellerAdapter);

        // Create event to get data
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Category category = dataSnapshot.getValue(Category.class);
                    dataOfCategory.add(category);
                }

                for (Category item : dataOfCategory) {
                    Product pro = new Product(R.drawable.beef_sukiyaki, item.NAME_CATEGORY);
                    dataOfProduct.add(pro);
                }

                // NOTE
                bestSellerAdapter.notifyDataSetChanged();
                viewPager_categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        // Add event
        myRef.addValueEventListener(valueEventListener);

        registerForContextMenu(tabLayout);
    }


}