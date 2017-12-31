package com.example.root.retrofit;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends AppCompatActivity {
    private SpaceTabLayout spaceTabLayout;
    private ViewPager viewPager;
    boolean doubleBac = false;
    @Override
    public void onBackPressed() {
        if (doubleBac)
        {
            super.onBackPressed();
        }
        this.doubleBac = true;
        Toast.makeText(this, "Tekan Tombol BACK lagi untuk Keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBac=false;
            }
        },1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new DataMahasiswaFragment());
        fragmentList.add(new BeasiswaFragment());
        fragmentList.add(new BukuFragment());
        getSupportActionBar().setTitle("Data Mahasiswa");
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        spaceTabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);
        //we need the savedInstanceState to get the position
        spaceTabLayout.initialize(viewPager, getSupportFragmentManager(),
                fragmentList, savedInstanceState);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0)
                    getSupportActionBar().setTitle("Data Mahasiswa");
                else if (position==1)
                    getSupportActionBar().setTitle("Data Buku");
                else
                    getSupportActionBar().setTitle("Data Beasiswa");
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        spaceTabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }

}
