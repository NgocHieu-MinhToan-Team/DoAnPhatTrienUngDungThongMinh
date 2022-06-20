package com.example.pepperluchapplication.Adapter;

import android.content.Context;
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

import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.ORDER;
import com.example.pepperluchapplication.DTO.PRODUCT;
import com.example.pepperluchapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RV_OrdersAdapter extends RecyclerView.Adapter<RV_OrdersAdapter.ViewOrders> implements Filterable {
    Context context;
    ArrayList<ORDER> data;

    public RV_OrdersAdapter(Context context,  ArrayList<ORDER> data) {
        this.context = context;
        this.data= data;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ViewOrders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_order_item,null);
        return new ViewOrders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewOrders holder, int position) {
        ORDER order=data.get(position);
        holder.tv_order_title.setText(order.getID_ORDER());
        String state ;
        switch ((int) order.getSTATUS()){
            case 0:{
                state="Đang Xử Lý";
            };break;
            case 1:{
                state="Đã nhận đơn";
            }break;
            case 2:{
                state="Đang Giao Hàng";
            };break;
            case 3:{
                state="Hoàn Tất Giao Hàng";
            };break;
            case 4:{
                state="Đơn hàng bị huỷ";
            };break;
            default:
                state="Đơn hàng của bạn đã xảy ra lỗi";break;
        }
        holder.tv_order_content.setText(state);
        int count=0;
        for(Map.Entry<String, CART> cart : order.getLIST_CART().entrySet()) {
            count+= cart.getValue().getSoluong();
        }
        String strcount = "Tổng Cộng Có: "+count + "món";
        holder.tv_count.setText(strcount);
        holder.tv_totalPrice.setText(Float.toString(order.getTOTAL_PAYMENT()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewOrders extends RecyclerView.ViewHolder {
        TextView tv_order_title,tv_order_content,tv_count,tv_totalPrice;
        LinearLayout linearLayout_order;
        public ViewOrders(@NonNull View itemView) {
            super(itemView);
            tv_order_title=itemView.findViewById(R.id.tv_order_title);
            tv_order_content=itemView.findViewById(R.id.tv_order_content);
            tv_count=itemView.findViewById(R.id.tv_order_count);
            tv_totalPrice=itemView.findViewById(R.id.tv_order_totalPrice);

            linearLayout_order=itemView.findViewById(R.id.linearLayout_order);
        }
    }

}
