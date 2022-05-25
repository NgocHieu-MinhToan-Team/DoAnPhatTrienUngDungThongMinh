package com.example.pepperluchapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pepperluchapplication.DTO.CART;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    Button btn_back_cart, btn_buy_cart;
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView=findViewById(R.id.listViewCart);
        textView=findViewById(R.id.nothinginyourbag);


        Intent intent=getIntent();
        ArrayList<CART> carts=(ArrayList<CART>) intent.getSerializableExtra("cart");

        if(carts.isEmpty())
        {
            textView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
    }
}