package com.example.pepperluchapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LV_CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<CART> data;
    public LV_CartAdapter(Context context,ArrayList<CART> data)
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(context).inflate(R.layout.item_cart,null);

        //lay thuoc tinh
        ImageView image_product_cart=convertView.findViewById(R.id.image_product_cart);
        TextView name_product_cart=convertView.findViewById(R.id.name_product_cart);
        TextView price_product_cart=convertView.findViewById(R.id.price_product_cart);
        TextView product_count_cart=convertView.findViewById(R.id.product_count_cart);
        TextView total_price_product_cart=convertView.findViewById(R.id.total_price_product_cart);
        Button btn_seemore_cart=convertView.findViewById(R.id.btn_seemore_cart);
        Button btn_buy_cart=convertView.findViewById(R.id.btn_buy_cart);

        //tao su kien click
        btn_seemore_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewDialog = LayoutInflater.from(context).inflate(R.layout.item_seemore_cart,null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(viewDialog);
                bottomSheetDialog.show();
                ImageView iv_detail_product_image = viewDialog.findViewById(R.id.image_product_seemore_cart);
                TextView tv_detail_product_name=viewDialog.findViewById(R.id.name_product_seemore_cart);
                TextView tv_detail_product_price = viewDialog.findViewById(R.id.price_seemore_cart);
                TextView tv_detail_product_quantity  = viewDialog.findViewById(R.id.product_count_seemore_cart);
                //btn
                Button btn_cancel_item_cart  = viewDialog.findViewById(R.id.btn_cancel_item_cart);
                ImageButton btnIncrease = viewDialog.findViewById(R.id.btn_detail_product_increase);
                ImageButton btnDecrease = viewDialog.findViewById(R.id.btn_detail_product_decrease);
                final int[] quantity={0};
                CART cart=data.get(position);
                Picasso.get().load(cart.getProduct().getIMAGE_PRODUCT()).into(iv_detail_product_image);
                tv_detail_product_name.setText(cart.getProduct().getNAME_PRODUCT_EN());
                tv_detail_product_price.setText(cart.getProduct().getPRICE_PRODUCT().toString());

                btnIncrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quantity[0]=quantity[0]+1;
                        tv_detail_product_quantity.setText( Integer.toString(quantity[0]));
                    }
                });

                btnDecrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(quantity[0]>1){
                            quantity[0] =quantity[0]-1;
                            tv_detail_product_quantity.setText(Integer.toString(quantity[0]));
                        }
                    }
                });
            }
        });


        //gan gia tri
        CART cart=data.get(position);
        PRODUCT pro=cart.getProduct();
        Picasso.get().load(pro.getIMAGE_PRODUCT()).into(image_product_cart);
        name_product_cart.setText(pro.getNAME_PRODUCT_EN());
        price_product_cart.setText(pro.getPRICE_PRODUCT().toString());
        product_count_cart.setText(Integer.toString(cart.getSoluong()));
        long total=pro.getPRICE_PRODUCT()*cart.getSoluong();
        total_price_product_cart.setText(Long.toString(total));
        return convertView;
    }
}
