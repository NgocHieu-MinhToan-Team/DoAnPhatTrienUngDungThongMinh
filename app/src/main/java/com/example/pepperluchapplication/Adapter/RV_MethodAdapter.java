package com.example.pepperluchapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pepperluchapplication.DTO.METHOD;
import com.example.pepperluchapplication.DTO.VOUCHER;
import com.example.pepperluchapplication.R;
import com.example.pepperluchapplication.Service.MyApplication;

import java.util.ArrayList;

public class RV_MethodAdapter extends RecyclerView.Adapter<RV_MethodAdapter.ViewMethod> implements Filterable {
    Context context;
    ArrayList<METHOD> data;
    private int lastCheckedPosition = -1;

    public RV_MethodAdapter(Context context,  ArrayList<METHOD> data) {
        this.context = context;
        this.data= data;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public RV_MethodAdapter.ViewMethod onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_method_item,null);
        return new RV_MethodAdapter.ViewMethod(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RV_MethodAdapter.ViewMethod holder, int position) {
        METHOD method=data.get(position);
        holder.rb_payment.setText(method.getNAME_METHOD());
        // set default
        holder.rb_payment.setChecked(position==lastCheckedPosition);
        MyApplication.setIdMethod(method.getID_METHOD());
        if(position==0){
            holder.rb_payment.setChecked(true);
        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyApplication.setIdMethod(method.getID_METHOD());
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewMethod extends RecyclerView.ViewHolder {
        RadioButton rb_payment;
        public ViewMethod(@NonNull View itemView) {
            super(itemView);
            rb_payment=itemView.findViewById(R.id.rb_payment);
            rb_payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int copyOfLastCheckedPosition = lastCheckedPosition;
                    lastCheckedPosition = getAdapterPosition();
                    notifyItemChanged(copyOfLastCheckedPosition);
                    notifyItemChanged(lastCheckedPosition);
                    METHOD method=data.get(lastCheckedPosition);
                    MyApplication.setIdMethod(method.getID_METHOD());
                    Toast.makeText(context, MyApplication.getIdMethod(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
