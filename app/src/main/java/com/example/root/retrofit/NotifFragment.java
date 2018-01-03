package com.example.root.retrofit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotifFragment extends Fragment implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    Button btn_send;
    EditText et_judul,et_pesan,et_gambar;
    Spinner spinner;
    private RadioGroup radioGroup;
    private boolean kirimsemua;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notif, container, false);

        radioGroup = (RadioGroup)view.findViewById(R.id.groupRadio);
        btn_send = (Button)view.findViewById(R.id.btn_send);
        spinner = (Spinner)view.findViewById(R.id.spinnersend);
        et_judul = (EditText)view.findViewById(R.id.et_judul);
        et_pesan = (EditText)view.findViewById(R.id.et_pesan);
        et_gambar = (EditText)view.findViewById(R.id.et_gambar);

        radioGroup.setOnCheckedChangeListener(this);
        btn_send.setOnClickListener(this);
        // Inflate the layout for this fragment
        loaddevices();
        return view;

    }

    private void loaddevices()
    {
        Log.d(TAG, "loaddevices: disini");
        ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
        Call<DeviceModel> baru = api.devicenya();
        Log.d(TAG, "loaddevices: disana");
        baru.enqueue(new Callback<DeviceModel>() {
            @Override
            public void onResponse(Call<DeviceModel> call, Response<DeviceModel> response) {
                List<ReadDevice> email = response.body().getDevices();
                Log.d(TAG, "onResponse: "+email.size());
                List<String> list = new ArrayList<String>();
                for (int i=0; i<email.size();i++)
                {
                    list.add(email.get(i).getEmail());
                    Log.d(TAG, "onResponse: "+email.get(i).getEmail());
                }
                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
                stringArrayAdapter.setDropDownViewResource(R.layout.simplespinner);
                spinner.setAdapter(stringArrayAdapter);
            }

            @Override
            public void onFailure(Call<DeviceModel> call, Throwable t) {
                Log.d(TAG, "onFailure: gagal");
            }   
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.sendAll:
                kirimsemua = true;
                spinner.setEnabled(false);
                break;
            case R.id.sendOne:
                kirimsemua =false;
                spinner.setEnabled(true);
                break;
        }
    }
}
