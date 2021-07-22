package com.toon.estore.Interfaces;

import com.toon.estore.Model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    String url= "https://fakestoreapi.com/";
    String url2= "https://fakestoreapi.com/products/category/";


    @GET("products/categories")
    Call<List<String>> getCat();

    @GET("products")
    Call<List<ProductModel>> getAllPro();

    @GET("jewelery")
    Call<List<ProductModel>> getjewelery();

    @GET("electronics")
    Call<List<ProductModel>> getelectronics();

    @GET("men's%20clothing")
    Call<List<ProductModel>> getMens();

    @GET("women's%20clothing")
    Call<List<ProductModel>> getWomen();


}
