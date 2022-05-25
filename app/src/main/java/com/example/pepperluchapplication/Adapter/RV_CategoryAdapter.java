package com.example.pepperluchapplication.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pepperluchapplication.DTO.CATEGORY;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.R;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RV_CategoryAdapter extends RecyclerView.Adapter<ViewHolderCategory> implements Filterable {
    Context context;
    ArrayList<CATEGORY> listCate;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RV_CategoryAdapter(Context context, ArrayList<CATEGORY> data) {
        this.context = context;
        this.listCate = data;
    }
    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ViewHolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_category_item,null);
        return new ViewHolderCategory(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolderCategory holder, int position) {
        CATEGORY kq=listCate.get(position);
        holder.tv_category_name.setText(kq.getNAME_CATEGORY());
        ArrayList<PRODUCT> listProduct
                =  kq.getDishes().values().stream().collect(
                Collectors.toCollection(ArrayList::new));
        RV_ProductAdapter adapter = new RV_ProductAdapter(context,listProduct);
        holder.rv_product_item_by_cate.setAdapter(adapter);
        holder.rv_product_item_by_cate.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false));


    }
    @Override
    public int getItemCount() {
        return listCate.size();
    }
}

class ViewHolderCategory extends RecyclerView.ViewHolder{
    TextView tv_category_name;
    RecyclerView rv_product_item_by_cate;
    public ViewHolderCategory(@NonNull View itemView) {
        super(itemView);
        tv_category_name=itemView.findViewById(R.id.tv_category_name);
        rv_product_item_by_cate=itemView.findViewById(R.id.rv_product_item_by_cate);
    }
}
