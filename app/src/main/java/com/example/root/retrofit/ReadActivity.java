package com.example.root.retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.circulardialog.CDialog;
import com.example.circulardialog.extras.CDConstants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private List<ReadModel> modelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        progressDialog = new ProgressDialog(this);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
        Call<ResponModel> getdata =api.data();
        getdata.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                progressDialog.hide();
                new CDialog(ReadActivity.this).createAlert("Berhasil",
                        CDConstants.SUCCESS, CDConstants.LARGE)
                        .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                        .setDuration(2000)
                        .setPosition(CDConstants.CENTER)
                        .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                        .show();
                modelList = response.body().getResult();
                adapter = new Adapter(ReadActivity.this,modelList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t)
            {
                progressDialog.hide();
                new CDialog(ReadActivity.this).createAlert("Gagal",
                        CDConstants.ERROR, CDConstants.LARGE)
                        .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                        .setDuration(2000)
                        .setPosition(CDConstants.CENTER)
                        .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                        .show();
            }
        });

    }
}
