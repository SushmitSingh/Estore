package com.toon.estore.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.toon.estore.Adapter.ProAdapter;
import com.toon.estore.Interfaces.ProductApi;
import com.toon.estore.Model.ProductModel;
import com.toon.estore.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatActivity extends AppCompatActivity {
RecyclerView recyclerView;
private ProAdapter adapter1;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        Intent intent = getIntent();
        int temp = intent.getIntExtra("index", 0);

        recyclerView=findViewById(R.id.catRv);
        toolbar=findViewById(R.id.toolbarCat);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(linearLayoutManager);




        switch (temp){
            case 0:
                electronics();
                break;
            case 1:
                jewelery();
                break;
            case 2:
                men();
                break;
            case 3:
                women();
                break;
            default:
                break;

        }

    }

    private void women() {
        toolbar.setTitle("women's clothing");
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ProductApi.url2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductApi api= retrofit.create(ProductApi.class);
        Call<List<ProductModel>> call = api.getWomen();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                List<ProductModel> list1 = response.body();
                adapter1 = new ProAdapter(list1);
               recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
    }

    private void men() {
        toolbar.setTitle("men's clothing");

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ProductApi.url2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductApi api= retrofit.create(ProductApi.class);
        Call<List<ProductModel>> call = api.getMens();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                List<ProductModel> list1 = response.body();
                adapter1 = new ProAdapter(list1);
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
    }

    private void electronics() {
        toolbar.setTitle("Electronics");


        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ProductApi.url2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductApi api= retrofit.create(ProductApi.class);
        Call<List<ProductModel>> call = api.getelectronics();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                List<ProductModel> list1 = response.body();
                adapter1 = new ProAdapter(list1);
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
    }

    private void jewelery() {
        toolbar.setTitle("Jewelery");

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ProductApi.url2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductApi api= retrofit.create(ProductApi.class);
        Call<List<ProductModel>> call = api.getjewelery();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                List<ProductModel> list1 = response.body();
                adapter1 = new ProAdapter(list1);
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
    }
}