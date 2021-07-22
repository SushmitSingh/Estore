package com.toon.estore.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.toon.estore.Adapter.CartViewAdapter;

import com.toon.estore.Model.productinfo;
import com.toon.estore.R;
import com.toon.estore.databinding.ActivityAddToCartBinding;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Map;

public class addToCart extends AppCompatActivity {
    ActivityAddToCartBinding binding;
    RecyclerView ryView;
    CartViewAdapter myAdapter;
    FirebaseAuth firebaseAuth;
    DatabaseReference dbref;
    Double sum1 = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_to_cart);
        firebaseAuth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("Estore").child(firebaseAuth.getCurrentUser().getUid());
        ryView = findViewById(R.id.cartRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(addToCart.this, LinearLayoutManager.VERTICAL, false);
        FirebaseRecyclerOptions<productinfo> options = new FirebaseRecyclerOptions.Builder<productinfo>()
                .setQuery(dbref, productinfo.class)
                .build();

        // added data from arraylist to adapter class.
        myAdapter = new CartViewAdapter(options);
        // at last set adapter to recycler view.
        ryView.setLayoutManager(linearLayoutManager);
        ryView.setAdapter(myAdapter);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Double sum = 0.0;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("number");
                    Object price2 = map.get("rate");
                    Double total = Double.parseDouble(String.valueOf(price));
                    Double total2 = Double.parseDouble(String.valueOf(price2));
                    sum += total * total2;
                    sum1 = sum;
                    DecimalFormat df = new DecimalFormat("#.##");
                    binding.finalprice.setText(String.valueOf(df.format(sum1)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePayment();
            }
        });

    }

    private void makePayment() {
            /**
             * Instantiate Checkout
             */
            Checkout checkout = new Checkout();
            checkout.setKeyID("rzp_test_fWZqRsA0G2lJCW");


            /**
             * Set your logo here
             */
            checkout.setImage(R.drawable.common_full_open_on_phone);

            /**
             * Reference to current activity
             */
            final Activity activity = this;

            /**
             * Pass your payment options to the Razorpay Checkout as a JSONObject
             */
            try {
                JSONObject options = new JSONObject();

                options.put("name", "To Quick Shop");
                options.put("description", "Reference No. #123456");
                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                options.put("theme.color", "#3399cc");
                options.put("currency", "INR");
                int amount = (int) (sum1*100);
                options.put("amount", amount);//pass amount in currency subunits
                options.put("prefill.email", "gaurav.kumar@example.com");
                options.put("prefill.contact","7905646998");
                JSONObject retryObj = new JSONObject();
                retryObj.put("enabled", true);
                retryObj.put("max_count", 4);
                options.put("retry", retryObj);

                checkout.open(activity, options);

            } catch(Exception e) {
                Log.e("TAG", "Error in starting Razorpay Checkout", e);
            }
        }


    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}
