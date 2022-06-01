package com.example.pepperluchapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperluchapplication.Adapter.LV_CartAdapter;
import com.example.pepperluchapplication.Adapter.RV_MethodAdapter;
import com.example.pepperluchapplication.Adapter.RV_VoucherAdapter;
import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.CUSTOMER;
import com.example.pepperluchapplication.DTO.METHOD;
import com.example.pepperluchapplication.DTO.VOUCHER;
import com.example.pepperluchapplication.Interface.onClickInterface;
import com.example.pepperluchapplication.Service.MyApplication;
import com.example.pepperluchapplication.DTO.ORDER;
import com.example.pepperluchapplication.Service.MyService;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CartActivity extends AppCompatActivity {

    Button btn_back_cart, btn_buy_cart;
    ListView listView;
    TextView textView;
    CardView buy_carview;
    TextView tv_voucher_name;
    ImageButton btn_delete_voucher;
    LV_CartAdapter lv_cartAdapter;
    onClickInterface onClickInterface;
    RV_VoucherAdapter voucherAdapter;
    EditText text_address;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://dbpepperlunch-default-rtdb.asia-southeast1.firebasedatabase.app/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView=findViewById(R.id.listViewCart);
        textView=findViewById(R.id.nothinginyourbag);
        btn_back_cart=findViewById(R.id.btn_back_cart);
        btn_buy_cart=findViewById(R.id.btn_buy_cart);
        buy_carview=findViewById(R.id.buy_carview);

//        Intent intent=getIntent();
//        ArrayList<CART> carts=(ArrayList<CART>) intent.getSerializableExtra("cart");
        ArrayList<CART> carts= MyApplication.getCarts();
        if(carts.isEmpty())
        {
            textView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            buy_carview.setVisibility(View.GONE);
            return;
        }
        lv_cartAdapter=new LV_CartAdapter(this,carts,textView,listView,buy_carview);
        listView.setAdapter(lv_cartAdapter);


        // buy now here
        btn_buy_cart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                View viewDialog = LayoutInflater.from(CartActivity.this).inflate(R.layout.bottom_sheet_payment,null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CartActivity.this);
                bottomSheetDialog.setContentView(viewDialog);
                bottomSheetDialog.show();
                TextView tv_payment_total=viewDialog.findViewById(R.id.tv_payment_total);
                TextView tv_payment_yourAddress = viewDialog.findViewById(R.id.tv_payment_yourAddress);
                TextView tv_payment_discount = viewDialog.findViewById(R.id.tv_payment_discount);
                TextView tv_payment_pay = viewDialog.findViewById(R.id.tv_payment_pay);
                RadioButton tv_payment_otherAddress=viewDialog.findViewById(R.id.tv_payment_otherAddress);
                RecyclerView rv_voucher= viewDialog.findViewById(R.id.rv_voucher);
                RecyclerView rv_method= viewDialog.findViewById(R.id.rv_method);
                CardView edt_other_address=viewDialog.findViewById(R.id.edt_other_address);
                text_address=viewDialog.findViewById(R.id.text_address);
                tv_voucher_name=viewDialog.findViewById(R.id.tv_voucher_name);
                btn_delete_voucher=viewDialog.findViewById(R.id.btn_delete_voucher);

                btn_delete_voucher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_voucher_name.setText("No Voucher Choosen");
                        MyApplication.setIdVoucher(null);
                        tv_payment_discount.setText("0");
                        tv_payment_pay.setText(tv_payment_total.getText());
                    }
                });
                //btn

                Button btn_pay  = viewDialog.findViewById(R.id.btn_pay);
                tv_payment_total.setText(Double.toString(MyApplication.getTotalPriceOfCart()));
                CUSTOMER customer = MyApplication.getCustomer();

                tv_payment_otherAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(tv_payment_otherAddress.isChecked())
                        {
                            edt_other_address.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            edt_other_address.setVisibility(View.GONE);
                        }
                    }
                });

                //get data voucher
                //interface
                onClickInterface = new onClickInterface() {
                    @Override
                    public void setClick(int abc) {

                        voucherAdapter.notifyDataSetChanged();
                        HashMap<String,CART> listOfCart=new HashMap<String,CART>();
                        float totalPayment=0;
                        for (CART cart : carts){
                            listOfCart.put(cart.getProduct().getID_PRODUCT(), cart);
                            totalPayment+=cart.getProduct().getPRICE_PRODUCT()*cart.getSoluong();
                        }
                        float percent =MyApplication.getPercentDiscount();
                        float maxDiscount = MyApplication.getAmountDiscount();
                        float discounted=0;
                        final float[] totalPay = {0};
                        if(percent>0){
                            // tinh giam
                            discounted=totalPayment*(percent/100);
                            if(discounted>maxDiscount){
                                totalPay[0]=totalPayment-maxDiscount;
                                tv_payment_discount.setText(Float.toString(maxDiscount));
                                tv_payment_pay.setText(Float.toString(totalPay[0]));
                            }
                            else{
                                totalPay[0]=totalPayment-discounted;
                                tv_payment_discount.setText(Float.toString(discounted));
                                tv_payment_pay.setText(Float.toString(totalPay[0]));
                            }
                        }
                        else{
                            totalPay[0]=totalPayment-maxDiscount;
                            tv_payment_discount.setText(Float.toString(discounted));
                            tv_payment_pay.setText(Float.toString(totalPay[0]));
                        }
                        voucherAdapter.notifyDataSetChanged();
                        //Toast.makeText(CartActivity.this,"Position is"+abc,Toast.LENGTH_LONG).show();

                    }
                };

                ArrayList<VOUCHER> listVoucher= new ArrayList<>();
                voucherAdapter= new RV_VoucherAdapter(CartActivity.this,listVoucher,onClickInterface,tv_voucher_name);
                rv_voucher.setAdapter(voucherAdapter);
                rv_voucher.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.HORIZONTAL, false));

                //get method payment
                ArrayList<METHOD> listOfMethod=new ArrayList<>();
                RV_MethodAdapter methodAdapter = new RV_MethodAdapter(CartActivity.this,listOfMethod);
                rv_method.setAdapter(methodAdapter);
                rv_method.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));


                DatabaseReference refVoucher= database.getReference("Database/Voucher");
                refVoucher.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data : snapshot.getChildren()){
                            VOUCHER voucher = data.getValue(VOUCHER.class);
                            listVoucher.add(voucher);
                            voucherAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                DatabaseReference refMethod= database.getReference("Database/Method");
                refMethod.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data : snapshot.getChildren()){
                            METHOD method = data.getValue(METHOD.class);
                            listOfMethod.add(method);
                        }
                        methodAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                rv_voucher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CartActivity.this, Float.toString(MyApplication.getPercentDiscount()), Toast.LENGTH_SHORT).show();

                    }
                });


                HashMap<String,CART> listOfCart=new HashMap<String,CART>();
                float totalPayment=0;
                for (CART cart : carts){
                    listOfCart.put(cart.getProduct().getID_PRODUCT(), cart);
                    totalPayment+=cart.getProduct().getPRICE_PRODUCT()*cart.getSoluong();
                }



                float percent =MyApplication.getAmountDiscount();
                float maxDiscount = MyApplication.getAmountDiscount();
                float discounted=0;
                final float[] totalPay = {0};
                if(percent>0){
                    // tinh giam
                    discounted=totalPayment*(percent/100);
                    if(discounted>maxDiscount){
                        totalPay[0]=totalPayment-maxDiscount;

                    }
                    else{
                        totalPay[0]=totalPayment-discounted;
                    }
                }
                else{
                    totalPay[0]=totalPayment-maxDiscount;
                }
                tv_payment_discount.setText(Float.toString(discounted));
                tv_payment_pay.setText(Float.toString(totalPayment));



                tv_payment_yourAddress.setText(customer.getADDRESS_CUSTOMER());
                btn_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String address;
                        if(tv_payment_otherAddress.isChecked())
                            address=text_address.getText().toString();
                        else
                            address=MyApplication.getCustomer().getADDRESS_CUSTOMER();

                        ORDER order = new ORDER(listOfCart,customer.getID_CUSTOMER(),MyApplication.getIdVoucher(),MyApplication.getIdMethod(),0,totalPay[0], address);
                        DatabaseReference myRef = database.getReference("Database/Order");
                        myRef.child(order.getID_CUSTOMER()).push().setValue(order);


                            // khởi chạy service
                            beginService(order);
                            //clear carts
                            MyApplication.clearCart();
                            finish();

                    }


                });
            }

        });


    }


    private void beginService(ORDER order) {
        Intent intent = new Intent(this, MyService.class);
        // push data lên notification
        Bundle bundle = new Bundle();
        bundle.putSerializable("YourOrder",order);
        intent.putExtras(bundle);
        startService(intent);
    }
    public void btn_back_cart_click(View view)
    {
        finish();
    }

}