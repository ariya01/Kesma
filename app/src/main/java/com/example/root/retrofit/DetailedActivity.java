package com.example.root.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.circulardialog.CDialog;
import com.example.circulardialog.extras.CDConstants;
import com.marcoscg.dialogsheet.DialogSheet;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity {
    private TextView tv_nama,tv_nrp,tv_ukt;
    private FabSpeedDial fabSpeedDial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent data = getIntent();
        fabSpeedDial = (FabSpeedDial)findViewById(R.id.floatnya);
        tv_nama = (TextView)findViewById(R.id.tv_isinama1);
        tv_nrp = (TextView)findViewById(R.id.tv_isinrp1);
        tv_ukt = (TextView)findViewById(R.id.tv_isiukt1);
        tv_nama.setText(data.getStringExtra("nama"));
        tv_nrp.setText(data.getStringExtra("nrp"));
        tv_ukt.setText(data.getStringExtra("penghasilan"));

        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if (menuItem.getTitle().equals("Edit"))
                {
                    Intent intent = new Intent(DetailedActivity.this,UpdateDataActivity.class);
                    intent.putExtra("nrp",tv_nrp.getText().toString());
                    intent.putExtra("nama",tv_nama.getText().toString());
                    intent.putExtra("penghasilan",tv_ukt.getText().toString());
                    startActivity(intent);
                }
                else if (menuItem.getTitle().equals("Hapus"))
                {
                    new DialogSheet(DetailedActivity.this)
                            .setTitle(R.string.delete)
                            .setMessage(R.string.pesandelete)
                            .setCancelable(false)
                            .setPositiveButton(R.string.delete, new DialogSheet.OnPositiveClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    dotProgressBar = (DotProgressBar)v.findViewById(R.id.dot_progress_bar4);
//                                    dotProgressBar.setVisibility(View.VISIBLE);
                                    ApiRetrofit apiRetrofit = Retrofitserver.getclient().create(ApiRetrofit.class);
                                    Call<ResponModel> del = apiRetrofit.delete(tv_nrp.getText().toString());
                                    del.enqueue(new Callback<ResponModel>() {
                                        @Override
                                        public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
//                                            dotProgressBar.setVisibility(View.GONE);
                                            new CDialog(DetailedActivity.this).createAlert("Berhasil",
                                                    CDConstants.SUCCESS, CDConstants.LARGE)
                                                    .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                                    .setDuration(2000)
                                                    .setPosition(CDConstants.CENTER)
                                                    .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                                    .show();
                                        }

                                        @Override
                                        public void onFailure(Call<ResponModel> call, Throwable t) {
//                                            dotProgressBar.setVisibility(View.GONE);
                                            new CDialog(DetailedActivity.this).createAlert("Gagal",
                                                    CDConstants.ERROR, CDConstants.LARGE)
                                                    .setAnimation(CDConstants.SCALE_FROM_BOTTOM_TO_TOP)
                                                    .setDuration(2000)
                                                    .setPosition(CDConstants.CENTER)
                                                    .setTextSize(CDConstants.LARGE_TEXT_SIZE)
                                                    .show();
                                        }
                                    });

                                }
                            })
                            .setNegativeButton(R.string.cencel, new DialogSheet.OnNegativeClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Your action
                                }
                            })
                            .setButtonsColorRes(R.color.colorPrimary)  // Default color is accent
                            .show();
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });

    }

}
