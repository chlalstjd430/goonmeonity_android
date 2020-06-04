package com.example.gunmunity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gunmunity.community.main.CommunityMainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GunmunityMainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gunmunity_main);
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, new CommunityMainFragment()).addToBackStack(null).commit();

        bottomNavigationBar = findViewById(R.id.main_bottom_tab);
        bottomNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_community:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_container, new CommunityMainFragment()).commit();
                        break;
                    case R.id.nav_calculator:
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.main_container, new CommunityMainFragment()).commit();
                        break;
                    case R.id.nav_asset:
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.main_container, new CommunityMainFragment()).commit();
                        break;
                }
                return true;
            }
        });
    }
}
