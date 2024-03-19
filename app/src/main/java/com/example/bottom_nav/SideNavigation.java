package com.example.bottom_nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class SideNavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_navigation);

        drawerLayout = findViewById(R.id.drawer_layout);
        repFragment(new HomeFragment());

        //Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_out, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.sidenav_home);
        }


        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home){
                    repFragment(new HomeFragment());
                } else if (itemId == R.id.profile) {
                    repFragment(new ProfileFragment());
                } else if (itemId == R.id.setting) {
                    repFragment(new SettingFragment());
                }
                return true;

            }
        });

    }

   @Override
   public boolean onNavigationItemSelected(@NonNull MenuItem item){
       int itemId = item.getItemId();
       if (itemId == R.id.sidenav_home){
           repFragment(new SideHomeFragment());
       } else if (itemId == R.id.sidenav_profile) {
           repFragment(new SideProfileFragment());
       } else if (itemId == R.id.sidenav_settings) {
           repFragment(new Side_Setting_fragment_());
       }else if (itemId == R.id.sidenav_msg){
           repFragment(new SideMessageFragment());
       }

       drawerLayout.closeDrawer(GravityCompat.START);
       return true;
   }


    private void repFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_out,fragment);
        fragmentTransaction.commit();
    }
}