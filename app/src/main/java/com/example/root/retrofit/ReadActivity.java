package com.example.root.retrofit;

import android.app.ProgressDialog;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.circulardialog.CDialog;
import com.example.circulardialog.extras.CDConstants;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadActivity extends AppCompatActivity implements OnQueryTextListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DotProgressBar dotProgressBar;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ReadModel> modelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
//        progressDialog = new ProgressDialog(this);
        recyclerView = (RecyclerView)findViewById(R.id.recycler2);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        getSupportActionBar().setTitle("Melihat Detail Mahasiswa");
//        progressDialog.setMessage("Loading");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
        dotProgressBar = (DotProgressBar)findViewById(R.id.dot_progress_bar3);
        dotProgressBar.setVisibility(View.VISIBLE);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refesh2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
                Call<ResponModel> getdata =api.data();
                getdata.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                        dotProgressBar.setVisibility(View.GONE);
                        new CDialog(ReadActivity.this).createAlert("Telah Di Refresh",
                                CDConstants.SUCCESS, CDConstants.LARGE)
                                .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                .setDuration(2000)
                                .setPosition(CDConstants.CENTER)
                                .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                .show();
                        modelList = response.body().getResult();
                        adapter = new AdapterRead(ReadActivity.this,modelList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<ResponModel> call, Throwable t) {
                        dotProgressBar.setVisibility(View.GONE);
                        new CDialog(ReadActivity.this).createAlert("Gagal di refesh",
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

        ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
        Call<ResponModel> getdata =api.data();
        getdata.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
//                progressDialog.hide();
                dotProgressBar.setVisibility(View.GONE);
                new CDialog(ReadActivity.this).createAlert("Berhasil",
                        CDConstants.SUCCESS, CDConstants.LARGE)
                        .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                        .setDuration(2000)
                        .setPosition(CDConstants.CENTER)
                        .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                        .show();
                modelList = response.body().getResult();
                adapter = new AdapterRead(ReadActivity.this,modelList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t)
            {
//                progressDialog.hide();
                dotProgressBar.setVisibility(View.GONE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cari, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Cari Nama Mahasiswa");
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        recyclerView.setVisibility(View.GONE);
        ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
        Call<ResponModel>cari = api.cari(newText);
        cari.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                modelList = response.body().getResult();
                adapter = new AdapterRead(ReadActivity.this,modelList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {

            }
        });
        return true;
    }
}
