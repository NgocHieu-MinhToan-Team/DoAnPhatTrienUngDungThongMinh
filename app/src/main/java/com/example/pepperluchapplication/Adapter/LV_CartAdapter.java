package com.example.pepperluchapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.R;
import com.example.pepperluchapplication.Service.MyApplication;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LV_CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<CART> data;
    TextView textView;
    ListView listView;
    CardView buy_carview;
    public LV_CartAdapter(Context context,ArrayList<CART> data,TextView textView, ListView listView, CardView buy_carview)
    {
        this.context=context;
        this.data=data;
        this.textView=textView;
        this.listView=listView;
        this.buy_carview=buy_carview;
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

        Button btn_delete_cart=convertView.findViewById(R.id.btn_delete_cart);

        //tao su kien click
        btn_seemore_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CART cart=data.get(position);
                View viewDialog = LayoutInflater.from(context).inflate(R.layout.item_seemore_cart,null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(viewDialog);
                bottomSheetDialog.show();
                ImageView iv_detail_product_image = viewDialog.findViewById(R.id.image_product_seemore_cart);
                TextView tv_detail_product_name=viewDialog.findViewById(R.id.name_product_seemore_cart);
                TextView tv_detail_product_price = viewDialog.findViewById(R.id.price_seemore_cart);
                TextView tv_detail_product_quantity  = viewDialog.findViewById(R.id.product_count_seemore_cart);
                TextView note_product_seemore_cart= viewDialog.findViewById(R.id.note_product_seemore_cart);
                RadioGroup rdbGroup_product_seemore_spicy=viewDialog.findViewById(R.id.rdbGroup_product_seemore_spicy);
                RadioButton radio_a_seemore=viewDialog.findViewById(R.id.radio_a_seemore);
                RadioButton radio_b_seemore=viewDialog.findViewById(R.id.radio_b_seemore);
                RadioButton radio_c_seemore=viewDialog.findViewById(R.id.radio_c_seemore);
                //btn
                Button btn_cancel_item_cart  = viewDialog.findViewById(R.id.btn_cancel_item_cart);
                Button btn_update_item_cart=viewDialog.findViewById(R.id.btn_update_item_cart);
                ImageButton btnIncrease = viewDialog.findViewById(R.id.btn_detail_product_increase);
                ImageButton btnDecrease = viewDialog.findViewById(R.id.btn_detail_product_decrease);
                final int[] quantity={cart.getSoluong()};

                Picasso.get().load(cart.getProduct().getIMAGE_PRODUCT()).into(iv_detail_product_image);
                tv_detail_product_name.setText(cart.getProduct().getNAME_PRODUCT_EN());
                tv_detail_product_price.setText(cart.getProduct().getPRICE_PRODUCT().toString());
                tv_detail_product_quantity.setText(Integer.toString(cart.getSoluong()));
                note_product_seemore_cart.setText(cart.getNote());
                if(radio_a_seemore.getText().toString().trim().equals(cart.getSpicy().trim()))
                    radio_a_seemore.setChecked(true);
                else if(radio_b_seemore.getText().toString().trim().equals(cart.getSpicy().trim()))
                    radio_b_seemore.setChecked(true);
                else if(radio_c_seemore.getText().toString().trim().equals(cart.getSpicy().trim()))
                    radio_c_seemore.setChecked(true);

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
                btn_cancel_item_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                btn_update_item_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CART cart2=data.get(position);
                        cart2.setSoluong(Integer.parseInt(tv_detail_product_quantity.getText().toString()));
                        RadioButton radioButton= viewDialog.findViewById(rdbGroup_product_seemore_spicy.getCheckedRadioButtonId());
                        cart2.setSpicy(radioButton.getText().toString());
                        cart2.setNote(note_product_seemore_cart.getText().toString());
                        MyApplication.updateItem(cart2);
                        Toast.makeText(context,"Update complete!",Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        btn_delete_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CART delCart=data.get(position);
                MyApplication.delItem(delCart);

                if(MyApplication.getCarts().isEmpty())
                {
                    textView.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    buy_carview.setVisibility(View.GONE);

                }
                notifyDataSetChanged();
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
