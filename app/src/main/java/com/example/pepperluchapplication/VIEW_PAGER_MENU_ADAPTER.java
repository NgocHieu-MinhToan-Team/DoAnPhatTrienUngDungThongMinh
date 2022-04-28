package com.example.pepperluchapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VIEW_PAGER_MENU_ADAPTER extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    ArrayList<String> tieudeTab=new ArrayList<>();

    public VIEW_PAGER_MENU_ADAPTER(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public  void addFragment(Fragment fragment,String title)
    {
        fragmentArrayList.add(fragment);
        tieudeTab.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tieudeTab.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);//tra ve fragment theo vi tri
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();//tra ve so luong fragment
    }
}
