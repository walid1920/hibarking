package com.example.hibarking.garage_manager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hibarking.MainActivity;
import com.example.hibarking.R;
import com.example.hibarking.driver.google_map.MapsFragment;
import com.example.hibarking.driver.profile.Fragments.ContactFragment;
import com.example.hibarking.driver.profile.Fragments.EmergancyFragment;
import com.example.hibarking.driver.profile.Fragments.SettingFragment;
import com.example.hibarking.driver.profile.ProfileFragment;
import com.example.hibarking.garage_manager.adapters.garage_show_adapter;
import com.example.hibarking.garage_manager.adapters.recycler_show_garage_info;
import com.example.hibarking.user_acess.login;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class main_garage_manager extends AppCompatActivity {
    private String user_id;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FloatingActionButton add_new_garage;
    private ArrayList<recycler_show_garage_info> arrayList;
    private RecyclerView recyclerview;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_garage);
       firebase_describtion();
        toolpar_intialize();
        recyclerview_method();
        FloatingActionButton_method();
        navigation_items();
    }

    private void firebase_describtion()
    {
        auth=FirebaseAuth.getInstance();
        user_id=auth.getCurrentUser().getUid();
    }
    private void toolpar_intialize() {
        toolbar = findViewById(R.id.garage_manager_appbar_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.garage_manager_drawer);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,  drawerLayout, toolbar, R.string.close, R.string.open);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void recyclerview_method() {
        recyclerview=findViewById(R.id.grarage_manager_recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        arrayList=new ArrayList<>();
        arrayList.add(new recycler_show_garage_info("garage 1","cairo"));
        arrayList.add(new recycler_show_garage_info("garage 1","cairo"));
        arrayList.add(new recycler_show_garage_info("garage 1","cairo"));
        arrayList.add(new recycler_show_garage_info("garage 1","cairo"));
        arrayList.add(new recycler_show_garage_info("garage 1","cairo"));
        arrayList.add(new recycler_show_garage_info("garage 1","cairo"));
        arrayList.add(new recycler_show_garage_info("garage 1","cairo"));
        arrayList.add(new recycler_show_garage_info("garage 1","cairo"));
        arrayList.add(new recycler_show_garage_info("garage 1","cairo"));
        garage_show_adapter adapter=new  garage_show_adapter(arrayList,this);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void FloatingActionButton_method()
    {
        add_new_garage=findViewById(R.id.add_new_garage);
        add_new_garage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              move_fragment(new add_garage_info());
            }
        });
    }
    private void navigation_items() {
        navigationView = (NavigationView) findViewById(R.id.g_nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigation_item_click(item);
                return false;
            }
        });
    }
    private void navigation_item_click(MenuItem item)
    {

      if(item.getItemId()==R.id.g_navigation_logout) {
            auth.signOut();
            startActivity(new Intent(main_garage_manager.this, login.class));

        }
    }
    private void move_fragment(Fragment Fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.grarage_manager_frameLayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }

}
