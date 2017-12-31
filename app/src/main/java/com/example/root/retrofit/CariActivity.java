package com.example.root.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

public class CariActivity extends AppCompatActivity {
    private EditText ed_nama,ed_nrp,ed_ukt;
    private Button btn_cari;
    private DotProgressBar dotProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);
        dotProgressBar = (DotProgressBar)findViewById(R.id.dot_progress_bar7);
        dotProgressBar.setVisibility(View.GONE);
        btn_cari = (Button)findViewById(R.id.btn_cari2);
        btn_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_nama = (EditText)findViewById(R.id.et_nama2);
                ed_nrp = (EditText)findViewById(R.id.et_nrp2);
                ed_ukt = (EditText)findViewById(R.id.et_ukt2);
                Intent intent = new Intent(CariActivity.this,ResultActivity.class);
                intent.putExtra("nama",ed_nama.getText().toString());
                intent.putExtra("nrp",ed_nrp.getText().toString());
                intent.putExtra("ukt",ed_ukt.getText().toString());
                startActivity(intent);
            }
        });


    }
}
