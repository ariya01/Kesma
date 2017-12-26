package com.example.root.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_tambah, btn_update,btn_delete,btn_lihat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_lihat = (Button)findViewById(R.id.btn_lihat);
        btn_tambah = (Button)findViewById(R.id.btn_tambah);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_lihat.setOnClickListener(op);
        btn_delete.setOnClickListener(op);
        btn_tambah.setOnClickListener(op);
        btn_update.setOnClickListener(op);
    }

    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_tambah:
                    Intent intent = new Intent(MainActivity.this,InsertActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_lihat:
                    Intent intent1 = new Intent(MainActivity.this,ReadActivity.class);
                    startActivity(intent1);
                    break;

            }
        }
    };
}
