package com.example.pepperluchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pepperluchapplication.Adapter.LV_CartAdapter;
import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.Service.MyApplication;
import com.example.pepperluchapplication.DTO.ORDER;
import com.example.pepperluchapplication.Service.MyService;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    Button btn_back_cart, btn_buy_cart;
    ListView listView;
    TextView textView;
    CardView buy_carview;
    LV_CartAdapter lv_cartAdapter;
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
        lv_cartAdapter=new LV_CartAdapter(this,carts);
        listView.setAdapter(lv_cartAdapter);


        // buy now here
        btn_buy_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewDialog = LayoutInflater.from(CartActivity.this).inflate(R.layout.bottom_sheet_payment,null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CartActivity.this);
                bottomSheetDialog.setContentView(viewDialog);
                bottomSheetDialog.show();
                TextView tv_payment_total=viewDialog.findViewById(R.id.tv_payment_total);
                TextView tv_payment_yourAddress = viewDialog.findViewById(R.id.tv_payment_yourAddress);
                RecyclerView rv_voucher= viewDialog.findViewById(R.id.rv_voucher);
                //btn
                Button btn_pay  = viewDialog.findViewById(R.id.btn_pay);
                tv_payment_total.setText(Double.toString(MyApplication.getTotalPriceOfCart()));
                // get data khách hàng từ MyApplication
                //get data voucher
                DatabaseReference refCustomer = database.getReference("Database/Voucher");


                btn_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String,CART> listOfCart=new HashMap<String,CART>();
                        float totalPayment=0;
                        for (CART cart : carts){
                            listOfCart.put(cart.getProduct().getID_PRODUCT(), cart);
                            totalPayment+=cart.getProduct().getPRICE_PRODUCT()*cart.getSoluong();
                        }
                        ORDER order = new ORDER(listOfCart,"KH003","","",0,totalPayment);
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
}