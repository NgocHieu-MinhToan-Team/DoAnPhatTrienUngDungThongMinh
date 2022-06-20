package com.example.pepperluchapplication.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pepperluchapplication.Adapter.RV_OrdersAdapter;
import com.example.pepperluchapplication.DTO.ORDER;
import com.example.pepperluchapplication.R;
import com.example.pepperluchapplication.Service.MyApplication;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class fragmentHistory extends Fragment {
    RecyclerView rv_orders,rv_receipt;
    ArrayList<ORDER> listOfOrders = new ArrayList<>();
    ArrayList<ORDER> listOfReceipt = new ArrayList<>();
    RV_OrdersAdapter adapterOrder,adapterReceipt;
    Context context;
    public fragmentHistory(Context applicationContext) {
        this.context=applicationContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_orders=view.findViewById(R.id.rv_orders);
        rv_receipt=view.findViewById(R.id.rv_receipts);


        //set adapter cho orders
        adapterOrder=new RV_OrdersAdapter(context,listOfOrders);
        rv_orders.setAdapter(adapterOrder);
        rv_orders.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //set adapter cho receipt
        adapterReceipt=new RV_OrdersAdapter(context,listOfReceipt);
        rv_receipt.setAdapter(adapterReceipt);
        rv_receipt.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dbpepperlunch-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference databaseReference = database.getReference("Database/Order");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //check child
                    if(snapshot.hasChild(MyApplication.getCustomer().getID_CUSTOMER())) {
                        listOfReceipt.clear();
                        listOfOrders.clear();
                        for(DataSnapshot record : snapshot.child(MyApplication.getCustomer().getID_CUSTOMER()).getChildren()){
                            ORDER value = record.getValue(ORDER.class);
                            value.setID_ORDER(record.getKey());
                            // 0 : đang xử lý ,thì thêm vào list đơn hàng
                            if(value.getSTATUS()>=0 && value.getSTATUS()<3){
                                listOfOrders.add(value);
                            }
                            else{
                                listOfReceipt.add(value);
                            }
                            // 1 : Dã xử lý xong ,thì thêm vào list lịch sử giao dịch
                            adapterOrder.notifyDataSetChanged();
                            adapterReceipt.notifyDataSetChanged();
                        }
                        // check don hang neu la khach hang moi chua dat hang thi hien thi lich su trong

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

}