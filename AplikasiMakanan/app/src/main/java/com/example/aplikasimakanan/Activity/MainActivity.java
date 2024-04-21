package com.example.aplikasimakanan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.aplikasimakanan.Adaptor.CategoryAdaptor;
import com.example.aplikasimakanan.Adaptor.PoplurarAdaptor;
import com.example.aplikasimakanan.Domain.CategoryDomain;
import com.example.aplikasimakanan.Domain.FoodDomain;
import com.example.aplikasimakanan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });
    }

    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Burger", "cat_2"));
        category.add(new CategoryDomain("Hotdog", "cat_3"));
        category.add(new CategoryDomain("Drink", "cat_4"));
        category.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni pizza","pop_1","slices pepperoni, mozzerella cheese,fresh oregano,ground black pepper,pizza sauce",9.00));
        foodList.add(new FoodDomain("Cheese Burger","pop_2","beef, Goude Cheese, Special Sauce, Lettuce, tomato",8.79));
        foodList.add(new FoodDomain("Vegetable pizza","pop_3","olive oil, Vegetable oil, pittd kalamata, cherry tomatoes,fresh oregano,basil", 8.50));
        foodList.add(new FoodDomain("Donut","donut","Terbuat dari terigu",6.59));
        foodList.add(new FoodDomain("Hotdog","hotdog","Terbuat dari sosis dan sayuran",9.89));
        foodList.add(new FoodDomain("Drink","cocktail","Sebuah minuman",4.99));

        adapter2 = new PoplurarAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}