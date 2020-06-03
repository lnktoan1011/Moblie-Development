package com.example.food_order_application_2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.food_order_application_2.Menu.activity_menu;
import com.example.food_order_application_2.Model.food;
import com.example.food_order_application_2.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private DatabaseReference mData;
    private ImageView imgDiscount, imgSell, imgRecommend;
    public Button btnOrderDiscount, btnOrderSell, btnOrderRecommend;
    private String food1 ="-M3esVSduynBCsiu9ryy";
    private String food2 ="-M4HPmGtn5jo5LgWZSxt";
    private String food3 = "-M3nQ1M1K6o4OQzUQWk6";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        imgDiscount = view.findViewById(R.id.image_viewDiscount);
        imgSell = view.findViewById(R.id.image_viewSeller);
        imgRecommend = view.findViewById(R.id.image_viewRecommend);
        btnOrderDiscount = view.findViewById(R.id.btnOrderDiscount);
        btnOrderSell = view.findViewById(R.id.btnOrderSeller);
        btnOrderRecommend = view.findViewById(R.id.btnOrderRecommend);
        viewFlipper = view.findViewById(R.id.flipper);
        viewFlipper.setFlipInterval(5000); //5 sec
        viewFlipper.setAutoStart(true);
        flipperImages();
        initView();

        return view;
    }

    private void initView() {
        mData = FirebaseDatabase.getInstance().getReference("food_menu");

        //Food Discount
        mData.child(food1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.child("img").getValue(String.class);
                Picasso.get().load(link).resizeDimen(R.dimen.image_size, R.dimen.image_size).into(imgDiscount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Food Seller
        mData.child(food2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.child("img").getValue(String.class);
                Picasso.get().load(link).resizeDimen(R.dimen.image_size, R.dimen.image_size).into(imgSell);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Food Recommend
        mData.child(food3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.child("img").getValue(String.class);
                Picasso.get().load(link).resizeDimen(R.dimen.image_size, R.dimen.image_size).into(imgRecommend);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void flipperImages() {
        //viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        //viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }
}
