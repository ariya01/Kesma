package com.example.root.retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    private Button btn_kirim;
    private Spinner spinner;
    private EditText et_nama,et_nrp;
    private ProgressDialog progressDialog;
    private String nama,nrp,penghasilan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        et_nama = (EditText)findViewById(R.id.et_nama);
        et_nrp = (EditText)findViewById(R.id.et_nrp);
        btn_kirim = (Button) findViewById(R.id.btn_kirim);
        progressDialog = new ProgressDialog(this);
        spinner = (Spinner)findViewById(R.id.spinner);
        final ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Kirim data");
                progressDialog.setCancelable(false);
                progressDialog.show();
                nama = et_nama.getText().toString();
                nrp = et_nrp.getText().toString();
                penghasilan= spinner.getSelectedItem().toString();
                Call<ResponModel> kirim = api.kirim(nrp,nama,penghasilan);
                kirim.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                        progressDialog.hide();
                        String kode = response.body().getKode();
                       String pesan = response.body().getPesan();

                       if (kode.equals("1"))
                       {
                           Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT).show();
                       }
                    }

                    @Override
                    public void onFailure(Call<ResponModel> call, Throwable t) {
                        progressDialog.hide();
                    }
                });
            }
        });
    }
}
