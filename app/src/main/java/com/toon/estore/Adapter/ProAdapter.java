package com.toon.estore.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.toon.estore.Model.ProductModel;
import com.toon.estore.R;
import com.toon.estore.View.CatActivity;
import com.toon.estore.View.ProductInfoActivity;

import java.util.List;

public class ProAdapter extends RecyclerView.Adapter<ProAdapter.ProHolder> {
    List<ProductModel> list1;

    public ProAdapter(List<ProductModel> list1) {
        this.list1 = list1;
    }

    @NonNull
    @Override
    public ProHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProHolder holder, int position) {
          holder.TvTitle.setText(list1.get(position).getTitle());
          holder.TvPrice.setText(list1.get(position).getPrice().toString());
          Glide.with(holder.imageView).load(list1.get(position).getImage()).into(holder.imageView);
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();
                  Intent intent=new Intent(appCompatActivity, ProductInfoActivity.class);
                  intent.putExtra("Title",list1.get(position).getTitle());
                  intent.putExtra("Rate",list1.get(position).getPrice().toString());
                  intent.putExtra("Desc",list1.get(position).getDescription());
                  intent.putExtra("Category",list1.get(position).getCategory());
                  intent.putExtra("img",list1.get(position).getImage());
                  intent.putExtra("id",list1.get(position).getId().toString());
                  appCompatActivity.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class ProHolder extends  RecyclerView.ViewHolder{
        TextView TvPrice,TvTitle;
        ImageView imageView;
        public ProHolder(@NonNull View itemView) {
            super(itemView);
            TvPrice = itemView.findViewById(R.id.proPrice);
            TvTitle = itemView.findViewById(R.id.proTitle);
            imageView = itemView.findViewById(R.id.proImg);
        }
    }
}
