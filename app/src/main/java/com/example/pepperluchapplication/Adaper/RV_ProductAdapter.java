package com.example.pepperluchapplication.Adaper;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.DetailProduct;
import com.example.pepperluchapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RV_ProductAdapter extends RecyclerView.Adapter<ViewProduct> implements Filterable {
    Context context;
    ArrayList<PRODUCT> data;

    public RV_ProductAdapter(Context context,ArrayList<PRODUCT> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ViewProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_product_item,null);
        return new ViewProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProduct holder, int position) {
        PRODUCT pro=data.get(position);
        holder.tv_product_name.setText(pro.getNAME_PRODUCT_VN());
        Picasso.get().load(pro.getIMAGE_PRODUCT()).into(holder.iv_product_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, DetailProduct.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product",pro);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
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
        tv_product_name=itemView.findViewById(R.id.tv_product_name);
        iv_product_image=itemView.findViewById(R.id.iv_product_image);
        item=itemView.findViewById(R.id.product_item);

    }

}
