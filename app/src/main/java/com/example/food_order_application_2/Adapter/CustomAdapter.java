package com.example.food_order_application_2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_order_application_2.Model.food;
import com.example.food_order_application_2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    ArrayList<food> data;
    Context context;


    public CustomAdapter(ArrayList<food> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_menu,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int min = 3;
        int max = 5;
        double random = new Random().nextDouble()*(max - min) +min;

        String link = data.get(position).getImg();
        holder.name.setText(data.get(position).getName());
        holder.price.setText(data.get(position).getPrice() + "$");
        holder.rate.setText(String.format("%.1f", random));
        Picasso.get().load(link).resizeDimen(R.dimen.image_size, R.dimen.image_size).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, rate;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameProduct);
            price = itemView.findViewById(R.id.priceProduct);
            imageView = itemView.findViewById(R.id.imageViewFood);
            rate = itemView.findViewById(R.id.tv_rate);
        }
    }
}
