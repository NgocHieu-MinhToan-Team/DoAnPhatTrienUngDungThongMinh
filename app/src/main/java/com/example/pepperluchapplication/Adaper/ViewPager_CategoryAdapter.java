package com.example.pepperluchapplication.Adaper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
<<<<<<< HEAD:app/src/main/java/com/example/pepperluchapplication/ViewPager_CategoryAdapter.java

    public void addFragment(Fragment fragment, String title) {
=======
    public void addFragment(Fragment fragment , String title){
>>>>>>> f9f290355de84b4d2cd648d84f54e48016f36b21:app/src/main/java/com/example/pepperluchapplication/Adaper/ViewPager_CategoryAdapter.java
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
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}