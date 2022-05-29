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

public class RV_ProductAdapter extends RecyclerView.Adapter<ViewProduct> implements Filterable {
    Context activity;
    ArrayList<Product> data;

    public RV_ProductAdapter(Context activity, ArrayList<Product> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ViewProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.rv_product_item, null);
        return new ViewProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProduct holder, int position) {
        Product kq = data.get(position);
        holder.tv_product_name.setText(kq.getName());
        holder.iv_product_image.setImageResource(kq.image);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}

class ViewProduct extends RecyclerView.ViewHolder {
    TextView tv_product_name;
    ImageView iv_product_image;

    public ViewProduct(@NonNull View itemView) {
        super(itemView);
        tv_product_name = itemView.findViewById(R.id.tv_product_name);
        iv_product_image = itemView.findViewById(R.id.iv_product_image);

    }
}
