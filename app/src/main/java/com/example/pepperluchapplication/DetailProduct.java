package com.example.pepperluchapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pepperluchapplication.DTO.Category;

public class DetailProduct extends AppCompatActivity {

    ImageView iv_image,iv_back,iv_cart;
    TextView tv_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        // get bundle
        Bundle bundle = getIntent().getExtras();
        if(bundle==null)
            return;
        Category cate = (Category) bundle.getSerializable("product");
        iv_image=findViewById(R.id.iv_product_detail_image);
        iv_back=findViewById(R.id.iv_product_detail_back);
        iv_cart=findViewById(R.id.iv_product_detail_cart);

        tv_name=findViewById(R.id.tv_product_detail_name);


        iv_image.setImageResource(R.drawable.beef_sukiyaki);
        tv_name.setText(cate.getGROUP_CATEGORY());

        // on click
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent =new Intent(DetailProduct.this,fragmentHome.class);
//                startActivity(intent);
            }
        });

    }


}