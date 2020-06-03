package com.example.food_order_application_2.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.food_order_application_2.Model.FoodHistory;
import com.example.food_order_application_2.R;
import com.example.food_order_application_2.Adapter.custom_adapter_cart_history_detail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_cart_history_detail extends AppCompatActivity {
    RecyclerView recyclerView;
    custom_adapter_cart_history_detail adapter;
    ArrayList<FoodHistory> data;
    DatabaseReference mData;
    Toolbar toolbar;

    int order_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_history_detail);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.recycler_view_history_detail);
        initView();

    }

    private void initView() {

        recyclerView.setHasFixedSize(true);
        data = new ArrayList<>();
        data = getIntent().getParcelableArrayListExtra("cart_food_detail");
        order_count = getIntent().getIntExtra("order_count",123);
        mData = FirebaseDatabase.getInstance().getReference("food_menu");
        getSupportActionBar().setTitle("Order " + order_count);
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(int i =0; i< data.size(); i++) {

                    String name = dataSnapshot.child(data.get(i).getProductId()).child("name").getValue().toString();
                    String img = dataSnapshot.child(data.get(i).getProductId()).child("img").getValue().toString();
                    data.get(i).setProductId(name);
                    data.get(i).setImg(img);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(
                activity_cart_history_detail.this,
                LinearLayoutManager.VERTICAL,
                false));
        adapter = new custom_adapter_cart_history_detail(activity_cart_history_detail.this, data);
        recyclerView.setAdapter(adapter);
    }
}
