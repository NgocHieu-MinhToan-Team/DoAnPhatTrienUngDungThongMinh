package com.example.pepperluchapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ActivityLogin extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // anh xa
        tabLayout = findViewById(R.id.tablayout_login);
        viewPager = findViewById(R.id.viewpager_login);
        tabLayout.setupWithViewPager(viewPager);
        // đang làm tới phần gắn viewpager adapter cho login activity


    }

    public void login(View view) {
        Intent intent = new Intent(ActivityLogin.this, ActivityHomePage.class);
        startActivity(intent);
    }
}