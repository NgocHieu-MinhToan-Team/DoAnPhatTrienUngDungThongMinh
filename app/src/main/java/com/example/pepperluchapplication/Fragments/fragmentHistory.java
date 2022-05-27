package com.example.pepperluchapplication.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pepperluchapplication.Adapter.ViewPager_HistoryAdapter;
import com.example.pepperluchapplication.R;
import com.google.android.material.tabs.TabLayout;

public class fragmentHistory extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    String[] title = {"Tab 1","Tab 2","Tab 3"};
    Context context;
    public fragmentHistory(Context applicationContext) {
        this.context=applicationContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout=view.findViewById(R.id.tablayout_history);
        viewPager= view.findViewById(R.id.viewpager_history);
        // set up
        tabLayout.setupWithViewPager(viewPager);
        ViewPager_HistoryAdapter adapter = new ViewPager_HistoryAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new fragment_history_orders(),"Orders");
        adapter.addFragment(new fragment_history_receipts(),"History");
        viewPager.setAdapter(adapter);
        //set icon
        registerForContextMenu(tabLayout);

    }
}