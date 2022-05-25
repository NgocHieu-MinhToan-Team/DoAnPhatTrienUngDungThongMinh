package com.example.pepperluchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pepperluchapplication.Adapter.ViewPager_Login;
import com.example.pepperluchapplication.Fragments.fragmentFormLogin;
import com.example.pepperluchapplication.Fragments.fragmentFormRegister;
import com.google.android.material.tabs.TabLayout;

public class ActivityLogin extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // anh xa
        tabLayout=findViewById(R.id.tablayout_login);
        viewPager= findViewById(R.id.viewpager_login);
        tabLayout.setupWithViewPager(viewPager);
        // đang làm tới phần gắn viewpager adapter cho login activity

        ViewPager_Login LoginAdapter = new ViewPager_Login(this.getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        LoginAdapter.addFragment(new fragmentFormLogin(),"Login");
        LoginAdapter.addFragment(new fragmentFormRegister(),"Register");
        viewPager.setAdapter(LoginAdapter);
        registerForContextMenu(tabLayout);

    }
    public void login(View view)
    {
        Intent intent=new Intent(ActivityLogin.this, ActivityHomePage.class);
        startActivity(intent);
    }
}