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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.pepperluchapplication.DTO.CATEGORY;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
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
        DatabaseReference myRef = database.getReference("Database/CATEGORY");

        //Initialize ListArray
        ArrayList<PRODUCT> dataOfPRODUCT = new ArrayList<>();

        ArrayList<CATEGORY> dataOfCATEGORY = new ArrayList<>();
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CATEGORY itemCate = (CATEGORY) snapshot.getValue();
                dataOfCATEGORY.add(itemCate);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


        // Initialize and set adapter for ViewPager
//        ViewPager_CategoryAdapter viewPager_categoryAdapter =
//                new ViewPager_CategoryAdapter(((AppCompatActivity) getActivity()).getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        viewPager_categoryAdapter.addFragment(new fragment_product_item(dataOfProduct), "Tab 1");
//        viewPager.setAdapter(viewPager_categoryAdapter);
//
//
//        // Initialize and set adapter fo RV_BestSeller
//        RV_BestSellerAdapter bestSellerAdapter = new RV_BestSellerAdapter(getActivity(), dataOfProduct);
//        rv_bestSeller.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        rv_bestSeller.setAdapter(bestSellerAdapter);

        // Create event to get data
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CATEGORY category = dataSnapshot.getValue(CATEGORY.class);
                    dataOfCATEGORY.add(category);
                }

                // NOTE
//                bestSellerAdapter.notifyDataSetChanged();
//                viewPager_categoryAdapter.notifyDataSetChanged();
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