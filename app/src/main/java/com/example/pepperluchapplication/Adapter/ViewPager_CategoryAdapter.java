package com.example.pepperluchapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPager_CategoryAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> listFrag = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();


    public ViewPager_CategoryAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    public void addFragment(Fragment fragment , String title){
        listFrag.add(fragment);
        titles.add(title);
    }
    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFrag.get(position);
    }

    @Override
    public int getCount() {
        return listFrag.size();
    }
}