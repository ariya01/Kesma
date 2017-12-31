package com.example.root.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.circulardialog.CDialog;
import com.example.circulardialog.extras.CDConstants;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DotProgressBar dotProgressBar;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ReadModel> modelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent =getIntent();
        recyclerView = (RecyclerView)findViewById(R.id.recycler6);
        dotProgressBar = (DotProgressBar)findViewById(R.id.dot_progress_bar6);
        dotProgressBar.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        getSupportActionBar().setTitle("Cari Mahasiswa");
        ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
        Log.d("nama", "onCreate: ");
        Call<ResponModel> cari = api.cari2(intent.getStringExtra("nrp"),intent.getStringExtra("nama"),intent.getStringExtra("ukt"));
        cari.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                dotProgressBar.setVisibility(View.GONE);
                new CDialog(ResultActivity.this).createAlert("Berhasil",
                        CDConstants.SUCCESS, CDConstants.LARGE)
                        .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                        .setDuration(2000)
                        .setPosition(CDConstants.CENTER)
                        .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                        .show();
                modelList = response.body().getResult();
                adapter = new AdapterRead(ResultActivity.this,modelList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                dotProgressBar.setVisibility(View.GONE);
                new CDialog(ResultActivity.this).createAlert("Gagal",
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
