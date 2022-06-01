package com.example.pepperluchapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.CATEGORY;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.R;
import com.example.pepperluchapplication.Service.MyApplication;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LV_FPGrowthAdapter extends BaseAdapter {
    Context context;
    ArrayList<CATEGORY> data;
    public LV_FPGrowthAdapter(Context context, ArrayList<CATEGORY> data)
    {
        this.context=context;
        this.data=data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(context).inflate(R.layout.lv_item_fpgrowth,null);
        RecyclerView rv_item_fpgrowth=convertView.findViewById(R.id.rv_item_fpgrowth);
        ImageButton btn_Choose_FPGrowth=convertView.findViewById(R.id.btn_Choose_FPGrowth);

        ArrayList<PRODUCT> listProduct
                =  data.get(position).getDishes().values().stream().collect(
                Collectors.toCollection(ArrayList::new));
        RV_ProductAdapter rv_productAdapter=new RV_ProductAdapter(context,listProduct);
        rv_item_fpgrowth.setAdapter(rv_productAdapter);
        rv_item_fpgrowth.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        //them su kien cho btn add gio hang
        btn_Choose_FPGrowth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(PRODUCT pro:listProduct)
                {
                    CART cart=new CART(pro,1,"","No Spicy");
                    MyApplication.setItem(cart);

                }
                Toast.makeText(context,"Add success",Toast.LENGTH_SHORT).show();

            }
        });

        return convertView;
    }
}
