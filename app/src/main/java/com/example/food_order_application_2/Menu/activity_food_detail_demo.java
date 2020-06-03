package com.example.food_order_application_2.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.food_order_application_2.Model.Cart;
import com.example.food_order_application_2.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.example.food_order_application_2.Model.food;
public class activity_food_detail_demo extends AppCompatActivity {

    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btn_cart, btn_back;
    ElegantNumberButton numberButton;
    DatabaseReference foods;
    String foodID = "";
    Integer quantity;
    int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail_demo);

        //Firebase
        foods = FirebaseDatabase.getInstance().getReference("food_menu");

        numberButton = findViewById(R.id.number_button);
        btn_cart = findViewById(R.id.btnCart);
        btn_back = findViewById(R.id.btnBack);

        food_description = findViewById(R.id.food_description);
        food_name = findViewById(R.id.food_name);
        food_price = findViewById(R.id.food_price);
        food_image = findViewById(R.id.img_food);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        quantity = Integer.parseInt(numberButton.getNumber());

        //Check intent
        if (getIntent() != null) {
            foodID = getIntent().getStringExtra("FoodID");
        }
        if (!foodID.isEmpty()) {
            getDetailFood(foodID);
        }

        numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = Integer.parseInt(numberButton.getNumber());
            }
        });

        //Back to menu food intent
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart  = new Cart(foodID, quantity, price);
                Intent replyIntent = new Intent();
                replyIntent.putExtra("result",cart);
                setResult(RESULT_OK,replyIntent);
                finish();
            }
        });
    }

    private void getDetailFood(String foodID) {
        foods.child(foodID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                food food = dataSnapshot.getValue(food.class);

                Picasso.get().load(food.getImg()).into(food_image);

                collapsingToolbarLayout.setTitle(food.getName());
                food_price.setText(food.getPrice() +"");
                food_name.setText(food.getName());
                food_description.setText(food.getDetail());
                price = food.getPrice();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
