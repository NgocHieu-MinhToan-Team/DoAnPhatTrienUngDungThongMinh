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

import com.example.pepperluchapplication.Adapter.RV_CategoryAdapter;
import com.example.pepperluchapplication.DTO.CATEGORY;
import com.example.pepperluchapplication.R;

import java.util.ArrayList;

public class fragment_category extends Fragment {

    ArrayList<CATEGORY> listOfCate;
    public fragment_category(ArrayList<CATEGORY> data){
        listOfCate=data;
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
        RV_CategoryAdapter adapter = new RV_CategoryAdapter(getContext(),listOfCate);
        rv_category.setAdapter(adapter);
        rv_category.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }
}