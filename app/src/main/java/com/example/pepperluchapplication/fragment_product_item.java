package com.example.pepperluchapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fragment_product_item extends Fragment {
    ArrayList<Product> data;
    RecyclerView rv_product;

    public fragment_product_item(ArrayList<Product> data) {
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // anh xa
        rv_product = view.findViewById(R.id.rv_product);
        // binding data by adapter
        RV_ProductAdapter adapter = new RV_ProductAdapter(this.getContext(), data);
        rv_product.setAdapter(adapter);
        rv_product.setLayoutManager(new GridLayoutManager(this.getContext(), 2, GridLayoutManager.VERTICAL, false));
    }
}