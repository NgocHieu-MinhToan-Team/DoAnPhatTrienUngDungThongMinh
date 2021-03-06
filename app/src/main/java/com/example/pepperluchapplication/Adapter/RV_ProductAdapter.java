package com.example.pepperluchapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.Service.MyApplication;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
        Picasso.get().load(pro.getIMAGE_PRODUCT()).placeholder(R.drawable.teppan_null).error(R.drawable.teppan_null).into(holder.iv_product_image);
        holder.tv_product_name.setText(pro.getNAME_PRODUCT_EN());
        //Picasso.get().load(pro.getIMAGE_PRODUCT()).into(holder.iv_product_image);
        holder.tv_product_price.setText(Long.toString(pro.getPRICE_PRODUCT()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open bottom sheet
                clickOpenBottomSheetDialog(pro);
            }
            int[] quantity = {0};

            private void clickOpenBottomSheetDialog(PRODUCT item) {
                View viewDialog = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_detail_product,null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(viewDialog);
                bottomSheetDialog.show();

                ImageView iv_detail_product_image = viewDialog.findViewById(R.id.iv_product_detail_image);
                TextView tv_detail_product_name=viewDialog.findViewById(R.id.tv_product_detail_name);
                TextView tv_detail_product_price = viewDialog.findViewById(R.id.tv_product_detail_price);
                TextView tv_detail_product_quantity  = viewDialog.findViewById(R.id.tv_detail_product_quantity);
                //btn
                Button btnAddToCart  = viewDialog.findViewById(R.id.btn_detail_product_addToCart);
                ImageButton btnIncrease = viewDialog.findViewById(R.id.btn_detail_product_increase);
                ImageButton btnDecrease = viewDialog.findViewById(R.id.btn_detail_product_decrease);
                EditText edt_note_product_detail=viewDialog.findViewById(R.id.edt_note_product_detail);
                RadioGroup rdbGroup_product_detail_spicy=viewDialog.findViewById(R.id.rdbGroup_product_detail_spicy);

                Picasso.get().load(pro.getIMAGE_PRODUCT()).into(iv_detail_product_image);
                tv_detail_product_name.setText(pro.getNAME_PRODUCT_EN());
                tv_detail_product_price.setText(pro.getPRICE_PRODUCT().toString());


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
                btnAddToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioButton radioButton=viewDialog.findViewById(rdbGroup_product_detail_spicy.getCheckedRadioButtonId());
                        CART cart=new CART(pro,Integer.parseInt(tv_detail_product_quantity.getText().toString()),edt_note_product_detail.getText().toString(),radioButton.getText().toString());
                        MyApplication.setItem(cart);
                        Toast.makeText(context,"AddSucess!",Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
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
    TextView tv_product_price;
    public ViewProduct(@NonNull View itemView) {
        super(itemView);
        tv_product_name=itemView.findViewById(R.id.tv_product_name);
        iv_product_image=itemView.findViewById(R.id.iv_product_image);
        item=itemView.findViewById(R.id.product_item);
        tv_product_price=itemView.findViewById(R.id.tv_product_price);

    }
}
