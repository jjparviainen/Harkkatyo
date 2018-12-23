package com.example.ett15084.harkkatyo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    static Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        System.out.println("###################################################################### Folder is: " + context.getFilesDir());

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FloorballFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_floorball:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FloorballFragment()).commit();
                    break;

                case R.id.nav_badminton:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BadmintonFragment()).commit();
                    break;

                case R.id.nav_squash:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SquashFragment()).commit();
                    break;

                case R.id.nav_myInfo:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyInfoFragment()).commit();
                    break;
            }
            return true;
        }
    };

    // Returns the context which is needed in many of the fragments
    public static Context getInstance(){
        return context;
    }

}
