package com.example.pepperluchapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPager_Login extends FragmentPagerAdapter {
    ArrayList<Fragment> listFrag = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    public ViewPager_Login(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(Fragment fragment , String title){
        listFrag.add(fragment);
        titles.add(title);
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
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
