package com.example.pepperluchapplication.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pepperluchapplication.Adapter.ViewPager2_Slider;
import com.example.pepperluchapplication.Animation.DepthPageTransformer;
import com.example.pepperluchapplication.DTO.NEWS;
import com.example.pepperluchapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class fragmentHome extends Fragment {
    Context context;
    TabLayout tabLayout;
    ViewPager viewPager;
    // khai bao cac view can thiet cho slider
    List<NEWS> listNews = new ArrayList<>();
    ;
    ViewPager2 viewPager2_slider;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2_slider.getCurrentItem() == listNews.size() - 1) {
                viewPager2_slider.setCurrentItem(0);
            } else {
                viewPager2_slider.setCurrentItem(viewPager2_slider.getCurrentItem() + 1);
            }
        }
    };
    private Handler handler = new Handler();

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
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        // add value here
        // slider
        viewPager2_slider = view.findViewById(R.id.viewPager2_slider);
        ViewPager2_Slider sliderAdapter = new ViewPager2_Slider(listNews);
        viewPager2_slider.setAdapter(sliderAdapter);

        CircleIndicator3 indicator = view.findViewById(R.id.circleIndicator3_slider);
        indicator.setViewPager(viewPager2_slider);
        sliderAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());
        viewPager2_slider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
        viewPager2_slider.setPageTransformer(new DepthPageTransformer());


        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dbpepperlunch-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Database/News");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    NEWS news = dataSnapshot.getValue(NEWS.class);
                    listNews.add(news);
                }
                sliderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        registerForContextMenu(tabLayout);
    }

    // khi ng dung thoat ung dung thi luu index cua slider
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);

    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}