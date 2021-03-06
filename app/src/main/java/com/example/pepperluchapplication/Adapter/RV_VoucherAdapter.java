package com.example.pepperluchapplication.Adapter;

import static com.example.pepperluchapplication.R.color.colorAccent;
import static com.example.pepperluchapplication.R.color.white;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pepperluchapplication.CartActivity;
import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.ORDER;
import com.example.pepperluchapplication.DTO.VOUCHER;
import com.example.pepperluchapplication.Interface.onClickInterface;
import com.example.pepperluchapplication.R;
import com.example.pepperluchapplication.Service.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RV_VoucherAdapter extends RecyclerView.Adapter<RV_VoucherAdapter.ViewVoucher> implements Filterable {
    Context context;
    ArrayList<VOUCHER> data;
    //HashMap<String,VOUCHER> data;
    onClickInterface onClickInterface;
    TextView tv_voucher_name;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public RV_VoucherAdapter(Context context, ArrayList<VOUCHER> data, onClickInterface onClickInterface, TextView tv_voucher_name) {
        this.context = context;
        this.data= data;
        this.onClickInterface=onClickInterface;
        this.tv_voucher_name=tv_voucher_name;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public RV_VoucherAdapter.ViewVoucher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_voucher_item,null);
        return new RV_VoucherAdapter.ViewVoucher(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewVoucher holder, @SuppressLint("RecyclerView") int position) {
        VOUCHER vou=data.get(position);
        holder.tv_voucher_code.setText(vou.getTYPE_REDUCTION());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                MyApplication.setIdVoucher(vou.getID_VOUCHER());
                MyApplication.setPercentDiscount(vou.getPERCENT_REDUCTION());
                MyApplication.setAmountDiscount(vou.getAMOUNT_REDUCTION());
                onClickInterface.setClick(position);
                //v.setBackgroundColor(colorAccent);
                tv_voucher_name.setText(vou.getTYPE_REDUCTION().toString());

            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewVoucher extends RecyclerView.ViewHolder {
        TextView tv_voucher_code;
        public ViewVoucher(@NonNull View itemView) {
            super(itemView);
            tv_voucher_code=itemView.findViewById(R.id.tv_voucher_code);

        }
    }
}
