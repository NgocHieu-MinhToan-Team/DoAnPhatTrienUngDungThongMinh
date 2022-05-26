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

import java.util.ArrayList;

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