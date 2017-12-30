package com.example.root.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.circulardialog.CDialog;
import com.example.circulardialog.extras.CDConstants;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.marcoscg.dialogsheet.DialogSheet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDataActivity extends AppCompatActivity {
    private Button btn_update;
    private Spinner spinner;
    private EditText et_nama,et_nrp;
    private String nama,nrp,penghasilan;
    private DotProgressBar dotProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        Intent data = getIntent();
        dotProgressBar = (DotProgressBar)findViewById(R.id.dot_progress_bar1);
        dotProgressBar.setVisibility(View.GONE);
        getSupportActionBar().setTitle("Update Data Mahasiswa");
        btn_update = (Button)findViewById(R.id.btn_update1);
        et_nama = (EditText)findViewById(R.id.et_nama1);
        et_nrp = (EditText)findViewById(R.id.et_nrp1);
        spinner = (Spinner)findViewById(R.id.spinner1);
        et_nama.setText(data.getStringExtra("nama"));
        et_nrp.setText(data.getStringExtra("nrp"));
        et_nrp.setKeyListener(null);
        final ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                penghasilan= spinner.getSelectedItem().toString();
                nama = et_nama.getText().toString();
                nrp = et_nrp.getText().toString();
                dotProgressBar.setVisibility(View.VISIBLE);
                ApiRetrofit apiRetrofit = Retrofitserver.getclient().create(ApiRetrofit.class);
                Call<ResponModel> update = api.update(nrp,nama,penghasilan);
                update.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                        new CDialog(UpdateDataActivity.this).createAlert("Berhasil",
                                CDConstants.SUCCESS, CDConstants.LARGE)
                                .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                .setDuration(2000)
                                .setPosition(CDConstants.CENTER)
                                .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                .show();
                        dotProgressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(UpdateDataActivity.this,UpdateActivity.class);
                    }
                Intent intent = new Intent(UpdateDataActivity.this,UpdateActivity.class);
                    @Override
                    public void onFailure(Call<ResponModel> call, Throwable t) {
                        new CDialog(UpdateDataActivity.this).createAlert("Gagal",
                                CDConstants.ERROR, CDConstants.LARGE)
                                .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                .setDuration(2000)
                                .setPosition(CDConstants.CENTER)
                                .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                .show();
                        dotProgressBar.setVisibility(View.GONE);
                        new DialogSheet(UpdateDataActivity.this)
                                .setTitle(R.string.gagalupdate)
                                .setMessage(R.string.pesanGagal)
                                .setCancelable(false)
                                .setPositiveButton("Coba Lagi", new DialogSheet.OnPositiveClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                                .setNegativeButton(R.string.cencel, new DialogSheet.OnNegativeClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(UpdateDataActivity.this,UpdateActivity.class);
                                    }
                                })
                                .setButtonsColorRes(R.color.colorPrimary)  // Default color is accent
                                .show();
                    }
                });
            }
        });
    }
}
