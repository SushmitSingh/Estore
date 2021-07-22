package com.toon.estore.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.toon.estore.Adapter.CatAdapter;
import com.toon.estore.Adapter.ProAdapter;
import com.toon.estore.Interfaces.ProductApi;
import com.toon.estore.Model.ProductModel;
import com.toon.estore.R;
import com.toon.estore.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    CatAdapter adapter;
    ProAdapter adapter1;
    com.google.android.material.appbar.MaterialToolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
       tb = findViewById(R.id.toolbar);
       tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {

                   switch (item.getItemId()){
                       case R.id.profile:
                           Toast.makeText(MainActivity.this,"Opening Profile",Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(MainActivity.this,UserProfile.class));
                           break;
                       case R.id.addCart:
                           Toast.makeText(MainActivity.this,"Opening Cart",Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(MainActivity.this,addToCart.class));
                           break;
                       default:
                   }
                   return false;
               }
       });
       cataList();
       allProductList();

    }

    private void allProductList() {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);
        binding.allProductRV.setLayoutManager(linearLayoutManager);
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ProductApi.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductApi api= retrofit.create(ProductApi.class);
        Call<List<ProductModel>> call = api.getAllPro();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                List<ProductModel> list1 = response.body();
                adapter1 = new ProAdapter(list1);
                binding.allProductRV.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
    }


    private void cataList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        binding.catagoryRV.setLayoutManager(linearLayoutManager);
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ProductApi.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductApi api= retrofit.create(ProductApi.class);
        Call<List<String>> call = api.getCat();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> list = response.body();
                adapter = new CatAdapter(list);
                binding.catagoryRV.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }
}