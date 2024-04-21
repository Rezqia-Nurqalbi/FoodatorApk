package com.example.aplikasimakanan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.aplikasimakanan.Adaptor.CartListAdapter;
import com.example.aplikasimakanan.Helper.ManagementCart;
import com.example.aplikasimakanan.Interface.ChangeNumberItemsListener;
import com.example.aplikasimakanan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt;
    private double tax;
    private NestedScrollView nestedScrollViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart=new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
    }

    private void initView() {
        recyclerViewList=findViewById(R.id.recyclerView);
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        nestedScrollViews=findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);

    }
    private void initList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            nestedScrollViews.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            nestedScrollViews.setVisibility(View.VISIBLE);
        }
    }
    private void CalculateCart(){
        double parcentTax=0.02;
        double delivery=10;

        tax=Math.round((managementCart.getTotalfee()*parcentTax)*100)/100;
        double total=Math.round((managementCart.getTotalfee()+tax+delivery)*100)/100;
        double itemTotal=Math.round(managementCart.getTotalfee()*100)/100;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }
}