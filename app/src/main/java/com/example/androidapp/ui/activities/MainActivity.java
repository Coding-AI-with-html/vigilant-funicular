package com.example.androidapp.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidapp.R;
import com.example.androidapp.ui.fragments.AccountFragment;
import com.example.androidapp.ui.fragments.CameraFragment;
import com.example.androidapp.ui.fragments.ChatFragment;
import com.example.androidapp.ui.fragments.HomeFragment;
import com.example.androidapp.ui.fragments.NewsFragment;
import com.example.androidapp.ui.fragments.NotificationFragment;
import com.example.androidapp.ui.fragments.hut.HutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    private BottomNavigationView mNav;
    private FrameLayout mFrame;
    private DrawerLayout drawerLayout;

    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private NewsFragment newsFragment;
    private CameraFragment cameraFragment;
    private AccountFragment accountFragment;
    private NotificationFragment notificationFragment;
    private HutFragment hutFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrame = findViewById(R.id.mFrame);
        mNav = findViewById(R.id.bottom_nav);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Constructors
        homeFragment = new HomeFragment();
        chatFragment = new ChatFragment();
        newsFragment = new NewsFragment();
        cameraFragment = new CameraFragment();
        accountFragment = new AccountFragment();
        notificationFragment = new NotificationFragment();
        hutFragment = new HutFragment();

        setFragment(homeFragment);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.drawer_town:
                        setFragment(notificationFragment);
                    case R.id.drawer_hut:
                        setFragment(hutFragment);
                    case R.id.drawer_dump:
                        setFragment(notificationFragment);
                    case R.id.drawer_arena:
                        setFragment(notificationFragment);
                    case R.id.drawer_forest:
                        setFragment(notificationFragment);
                    case R.id.drawer_river:
                        setFragment(notificationFragment);
                    case R.id.drawer_sea:
                        setFragment(notificationFragment);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });
        mNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_chat:
                        setFragment(chatFragment);
                        return true;

                    case R.id.nav_camera:
                        setFragment(cameraFragment);
                        return true;

                    case R.id.nav_notify:
                        setFragment(notificationFragment);
                        return true;

                    case R.id.nav_news:
                        setFragment(newsFragment);
                        return true;

                    default:
                        return false;

                }
            }


        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mFrame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

//            case R.id.drawer_town:
//                setFragment(notificationFragment);
//            case R.id.drawer_hut:
//                setFragment(notificationFragment);
//            case R.id.drawer_dump:
//                setFragment(notificationFragment);
//            case R.id.drawer_arena:
//                setFragment(notificationFragment);
//            case R.id.drawer_forest:
//                setFragment(notificationFragment);
//            case R.id.drawer_river:
//                setFragment(notificationFragment);
//            case R.id.drawer_sea:
//                setFragment(notificationFragment);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
