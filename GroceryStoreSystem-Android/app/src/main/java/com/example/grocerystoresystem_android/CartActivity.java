package com.example.grocerystoresystem_android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class CartActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        /*
        * Use this code in some other activity, to switch to this activity
        * startActivity(new Intent(this, CartActivity.class));
        */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase);

//        ListView cartList = (ListView)findViewById(R.id.cartListView);

//        cartList.setAdapter(new CartAdapter());
    }
}
