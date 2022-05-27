package com.example.pepperluchapplication.Fragments;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pepperluchapplication.Adapter.RV_CategoryAdapter;
import com.example.pepperluchapplication.Adapter.RV_OrdersAdapter;
import com.example.pepperluchapplication.DTO.CATEGORY;
import com.example.pepperluchapplication.DTO.ORDER;
import com.example.pepperluchapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class fragment_history_orders extends Fragment {


    RecyclerView rv_orders;
    ArrayList<ORDER> values = new ArrayList<>();
    ArrayList<String> keys = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_history_orders_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_orders=view.findViewById(R.id.rv_orders);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dbpepperlunch-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference databaseReference = database.getReference("Database/TestInsertOrder");
        RV_OrdersAdapter adapter = new RV_OrdersAdapter(getContext(),values);
        rv_orders.setAdapter(adapter);
        rv_orders.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ORDER value = snapshot.getValue(ORDER.class);
                String key=snapshot.getKey();
                keys.add(key);
                values.add(keys.indexOf(key),value);
                adapter.notifyDataSetChanged();
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ORDER value = snapshot.getValue(ORDER.class);
                String key=snapshot.getKey();
                values.set(keys.indexOf(key),value);
                adapter.notifyDataSetChanged();

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
        });
    }

}