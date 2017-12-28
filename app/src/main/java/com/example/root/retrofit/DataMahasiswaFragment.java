package com.example.root.retrofit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DataMahasiswaFragment extends Fragment {

    Button btn_tambah, btn_update,btn_delete,btn_lihat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data_mahasiswa, container, false);
        btn_lihat = (Button)view.findViewById(R.id.btn_lihat);
        btn_tambah = (Button)view.findViewById(R.id.btn_tambah);
        btn_update = (Button)view.findViewById(R.id.btn_update);
        btn_delete = (Button)view.findViewById(R.id.btn_delete);
        btn_lihat.setOnClickListener(op);
        btn_delete.setOnClickListener(op);
        btn_tambah.setOnClickListener(op);
        btn_update.setOnClickListener(op);
        return view;
    }
    private View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_tambah:
                    Intent intent = new Intent(getActivity(),InsertActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_lihat:
                    Intent intent1 = new Intent(getActivity(),ReadActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btn_delete:
                    Intent intent2 = new Intent(getActivity(),DeleteActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.btn_update:
                    Intent intent3 = new Intent(getActivity(),UpdateActivity.class);
                    startActivity(intent3);
                    break;

            }
        }
    };
}
