package com.example.pepperluchapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RV_BestSellerAdapter extends RecyclerView.Adapter<KHUNGNHIN> implements Filterable {
    Context activity;
    ArrayList<Product> data;

    public RV_BestSellerAdapter(Context activity, ArrayList<Product> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.rv_bestseller_item, null);
        return new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
        Product kq = data.get(position);
        holder.tv_dishName.setText(kq.getName());
        holder.iv_image.setImageResource(kq.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class KHUNGNHIN extends RecyclerView.ViewHolder {
    TextView tv_dishName;
    ImageView iv_image;

    public KHUNGNHIN(@NonNull View itemView) {
        super(itemView);
        tv_dishName = itemView.findViewById(R.id.tv_dishName);
        iv_image = itemView.findViewById(R.id.iv_image);

    }
}
