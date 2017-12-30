package com.example.root.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView tv_nama,tv_nrp,tv_ukt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle(R.string.detail);
        Intent data = getIntent();
        tv_nama = (TextView)findViewById(R.id.tv_isinama);
        tv_nrp = (TextView)findViewById(R.id.tv_isinrp);
        tv_ukt = (TextView)findViewById(R.id.tv_isiukt);
        tv_nama.setText(data.getStringExtra("nama"));
        tv_nrp.setText(data.getStringExtra("nrp"));
        tv_ukt.setText(data.getStringExtra("penghasilan"));
    }
}
