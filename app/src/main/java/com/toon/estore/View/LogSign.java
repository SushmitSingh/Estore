package com.toon.estore.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.toon.estore.Adapter.PageAdapter;
import com.toon.estore.R;

public class LogSign extends AppCompatActivity {
 private PageAdapter pgadpter;
 TabLayout tb;
 ViewPager vpage;
 FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sign);

        tb = findViewById(R.id.logSignTab);
        vpage = findViewById(R.id.vPager);



        pgadpter  = new PageAdapter(getSupportFragmentManager(),tb.getTabCount());
        vpage.setAdapter(pgadpter);
        tb.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpage.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0 || tab.getPosition()==1)
                    pgadpter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // For Scroll Page Change
       vpage.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb));

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(this,MainActivity.class));
        }

    }
}