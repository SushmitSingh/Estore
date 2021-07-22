package com.toon.estore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.toon.estore.R;
import com.toon.estore.View.CatActivity;
import com.toon.estore.View.LogSign;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatHolder> {
    List<String> list;


    public CatAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_cart,parent,false);
        return new CatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatHolder holder, int position) {
        String title=String.valueOf(list.get(position));
             holder.tv.setText(list.get(position));
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();
                     Intent intent=new Intent(appCompatActivity, CatActivity.class);
                     intent.putExtra("index",position);
                     appCompatActivity.startActivity(intent);
                 }
             });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CatHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public CatHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.catText);
        }
    }
}
