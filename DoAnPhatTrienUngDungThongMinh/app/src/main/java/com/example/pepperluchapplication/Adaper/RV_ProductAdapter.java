package com.example.pepperluchapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pepperluchapplication.DTO.Product;
import com.example.pepperluchapplication.DetailProduct;
import com.example.pepperluchapplication.R;

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
        Product pro = data.get(position);
        holder.tv_product_name.setText(pro.getName());
        holder.iv_product_image.setImageResource(pro.getImage());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickItemProduct(pro);
            }
        });
    }

    private void OnClickItemProduct(Product pro) {
        Intent intent = new Intent(activity, DetailProduct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", pro);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}

class ViewProduct extends RecyclerView.ViewHolder {
    TextView tv_product_name;
    ImageView iv_product_image;
    LinearLayout item;


    public ViewProduct(@NonNull View itemView) {
        super(itemView);
        tv_product_name = itemView.findViewById(R.id.tv_product_name);
        iv_product_image = itemView.findViewById(R.id.iv_product_image);
        item = itemView.findViewById(R.id.product_item);

    }

}
