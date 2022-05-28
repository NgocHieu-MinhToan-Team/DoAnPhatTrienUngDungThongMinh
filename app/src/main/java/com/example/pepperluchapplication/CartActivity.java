package com.example.pepperluchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pepperluchapplication.Adapter.LV_CartAdapter;
import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.MyApplication;
import com.example.pepperluchapplication.DTO.ORDER;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    Button btn_back_cart, btn_buy_cart;
    ListView listView;
    TextView textView;
    CardView buy_carview;
    LV_CartAdapter lv_cartAdapter;
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
                HashMap<String,CART> listOfCart=new HashMap<String,CART>();
                float totalPayment=0;
                for (CART cart : carts){
                    listOfCart.put(cart.getProduct().getID_PRODUCT(), cart);
                    totalPayment+=cart.getProduct().getPRICE_PRODUCT()*cart.getSoluong();
                }
                ORDER order = new ORDER(listOfCart,"KH003","","",0,totalPayment);
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://dbpepperlunch-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("Database/TestInsertOrder");
                myRef.child(order.getID_CUSTOMER()).setValue(order);
                //clear carts
                MyApplication.clearCart();
                finish();

            }
        });
    }
    public void btn_back_cart_click(View view)
    {
        finish();
    }
    public  void btn_buy_cart_click(View view)
    {
        Intent intent=new Intent(CartActivity.this,ActivityCartPayment.class);
        startActivity(intent);
    }
}