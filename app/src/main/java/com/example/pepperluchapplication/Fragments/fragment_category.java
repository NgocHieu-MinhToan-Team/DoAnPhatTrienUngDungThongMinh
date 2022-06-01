package com.example.pepperluchapplication.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.pepperluchapplication.Adapter.RV_CategoryAdapter;
import com.example.pepperluchapplication.DTO.CATEGORY;
import com.example.pepperluchapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class fragment_category extends Fragment {

    ArrayList<CATEGORY> dataOfCATEGORY;
    RV_CategoryAdapter adapter;
    String type_category;


    public fragment_category(String text){
        type_category=text;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
    RecyclerView rv_category;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_category=view.findViewById(R.id.rv_category);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dbpepperlunch-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference databaseReference = database.getReference("Database/Category_Dish");
        dataOfCATEGORY = new ArrayList<>();
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CATEGORY item = snapshot.getValue(CATEGORY.class);
                if(item.getGROUP_CATEGORY().equals(type_category)){
                    dataOfCATEGORY.add(item);
                    adapter.notifyDataSetChanged();
                }

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
        databaseReference.addChildEventListener(childEventListener);
        //set animation
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_anim_up_to_down);
        rv_category.setLayoutAnimation(layoutAnimationController);

        adapter = new RV_CategoryAdapter(getContext(),dataOfCATEGORY);
        rv_category.setAdapter(adapter);
        rv_category.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    ArrayList<CATEGORY> filterByCondition(ArrayList<CATEGORY> data, String condition){
        return new ArrayList<CATEGORY>(data.parallelStream().filter(entry->entry.getGROUP_CATEGORY().equals(condition)).collect(Collectors.toList()));
    }
}