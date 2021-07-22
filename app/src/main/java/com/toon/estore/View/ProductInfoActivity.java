package com.toon.estore.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toon.estore.Model.productinfo;
import com.toon.estore.R;
import com.toon.estore.databinding.ActivityProductInfoBinding;

public class ProductInfoActivity extends AppCompatActivity {
  ActivityProductInfoBinding binding;
    DatabaseReference mstorageref;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    productinfo productinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product_info);
        productinfo = new productinfo();
        Intent intent = getIntent();
        String title= intent.getStringExtra("Title");
        String Rate= intent.getStringExtra("Rate");
        String Desc= intent.getStringExtra("Desc");
        String category= intent.getStringExtra("Category");
        String img= intent.getStringExtra("img");
        String id= intent.getStringExtra("id");
        binding.carttoolbar.setTitle(title);
            firebaseAuth=FirebaseAuth.getInstance();
//        mstorageref = firebaseDatabase.getReference("cartItems/"+firebaseAuth.getCurrentUser().getUid()).child(id);

        mstorageref = FirebaseDatabase.getInstance().getReference("Estore").child(firebaseAuth.getCurrentUser().getUid()).child(id);
        Glide.with(binding.itemimage).load(img).into(binding.itemimage);
        binding.itemtitle.setText(title);
        binding.itemprice.setText(Rate);
        binding.desp.setText(Desc);
        binding.addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCartProducts(title, Rate, img,id);
            }
        });
    }

    private void addCartProducts(String title, String rate, String img, String id) {
        productinfo.setTitle(title);
        productinfo.setRate(rate);
        productinfo.setImg(img);
        productinfo.setNumber(1L);
        productinfo.setId(id);
    mstorageref.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        mstorageref.setValue(productinfo);
        Toast.makeText(ProductInfoActivity.this, "Item added in cart...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(ProductInfoActivity.this, "Failed to added Item...", Toast.LENGTH_SHORT).show();
    }
});

    }
}