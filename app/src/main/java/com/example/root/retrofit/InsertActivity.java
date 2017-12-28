package com.example.root.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.circulardialog.CDialog;
import com.example.circulardialog.extras.CDConstants;
import com.nipunbirla.boxloader.BoxLoaderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    private Button btn_kirim,btn_update;
    private Spinner spinner;
    private EditText et_nama,et_nrp;
    private TextView tv_loading;
    private ProgressDialog progressDialog;
    private String nama,nrp,penghasilan;
    private BoxLoaderView boxLoaderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Intent data = getIntent();
        getSupportActionBar().setTitle("Insert Data Mahasiswa");
        boxLoaderView = (BoxLoaderView)findViewById(R.id.progress);
        boxLoaderView.setVisibility(View.GONE);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_update.setVisibility(View.GONE);
        tv_loading=(TextView)findViewById(R.id.tv_loading);
        tv_loading.setVisibility(View.GONE);
        et_nama = (EditText)findViewById(R.id.et_nama);
        et_nrp = (EditText)findViewById(R.id.et_nrp);
        btn_kirim = (Button) findViewById(R.id.btn_kirim);
        progressDialog = new ProgressDialog(this);
        spinner = (Spinner)findViewById(R.id.spinner);
        final ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
        if (data!= null)
        {
            btn_update.setVisibility(View.VISIBLE);
            btn_kirim.setVisibility(View.GONE);
            et_nama.setText(data.getStringExtra("nama"));
            et_nrp.setText(data.getStringExtra("nrp"));
            et_nrp.setKeyListener(null);

        }
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                penghasilan= spinner.getSelectedItem().toString();
                nama = et_nama.getText().toString();
                nrp = et_nrp.getText().toString();
                ApiRetrofit apiRetrofit = Retrofitserver.getclient().create(ApiRetrofit.class);
                Call<ResponModel> update = api.update(nrp,nama,penghasilan);
                update.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                        new CDialog(InsertActivity.this).createAlert("Berhasil",
                                CDConstants.SUCCESS, CDConstants.LARGE)
                                .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                .setDuration(2000)
                                .setPosition(CDConstants.CENTER)
                                .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                .show();
                    }

                    @Override
                    public void onFailure(Call<ResponModel> call, Throwable t) {
                        new CDialog(InsertActivity.this).createAlert("Gagal",
                                CDConstants.ERROR, CDConstants.LARGE)
                                .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                .setDuration(2000)
                                .setPosition(CDConstants.CENTER)
                                .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                .show();
                    }
                });
            }
        });
        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressDialog.setMessage("Kirim data");
//                progressDialog.setCancelable(false);
//                progressDialog.show();
                tv_loading.setVisibility(View.VISIBLE);
                boxLoaderView.setVisibility(View.VISIBLE);
                nama = et_nama.getText().toString();
                nrp = et_nrp.getText().toString();
                penghasilan= spinner.getSelectedItem().toString();
                Call<ResponModel> kirim = api.kirim(nrp,nama,penghasilan);
                kirim.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
//                        progressDialog.hide();
                        tv_loading.setVisibility(View.GONE);
                        boxLoaderView.setVisibility(View.GONE);
                        String kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                       if (kode.equals("1"))
                       {
                           new CDialog(InsertActivity.this).createAlert("Berhasil",
                                   CDConstants.SUCCESS, CDConstants.LARGE)
                                   .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                   .setDuration(2000)
                                   .setPosition(CDConstants.CENTER)
                                   .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                   .show();
                       }
                       else
                       {
                           new CDialog(InsertActivity.this).createAlert("Gagal",
                                   CDConstants.ERROR, CDConstants.LARGE)
                                   .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                   .setDuration(2000)
                                   .setPosition(CDConstants.CENTER)
                                   .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                   .show();
                       }
                    }

                    @Override
                    public void onFailure(Call<ResponModel> call, Throwable t) {
//                        progressDialog.hide();
                        tv_loading.setVisibility(View.GONE);
                        boxLoaderView.setVisibility(View.GONE);
                        new CDialog(InsertActivity.this).createAlert("Cek Koneksi",
                                CDConstants.ERROR, CDConstants.LARGE)
                                .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                .setDuration(2000)
                                .setPosition(CDConstants.CENTER)
                                .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                .show();
                    }
                });
            }
        });
    }
}
