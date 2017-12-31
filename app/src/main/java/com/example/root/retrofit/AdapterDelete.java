package com.example.root.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.circulardialog.CDialog;
import com.example.circulardialog.extras.CDConstants;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.marcoscg.dialogsheet.DialogSheet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 12/31/17.
 */

public class AdapterDelete extends RecyclerView.Adapter<AdapterDelete.Holder> {
    private List<ReadModel> list;
    private Context context;

    public AdapterDelete (Context context, List<ReadModel> list)
    {
        this.list=list;
        this.context=context;
    }

    @Override
    public AdapterDelete.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        AdapterDelete.Holder holder = new AdapterDelete.Holder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterDelete.Holder holder, int position) {
        ReadModel readModel = list.get(position);
        holder.tv_nama.setText(readModel.getNama());
        holder.tv_nrp.setText(readModel.getNrp());
        holder.tv_penghasilan.setText(readModel.getPenghasilan());
        holder.readModel = readModel;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_nama,tv_nrp,tv_penghasilan;
        ReadModel readModel;
        private DotProgressBar dotProgressBar;
        public Holder(View itemView) {
            super(itemView);
            tv_nama=(TextView)itemView.findViewById(R.id.tv_nama);
            tv_nrp=(TextView)itemView.findViewById(R.id.tv_nrp);
            tv_penghasilan=(TextView)itemView.findViewById(R.id.tv_ukt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    new DialogSheet(context)
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
                                            new CDialog(context).createAlert("Berhasil",
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
                                            new CDialog(context).createAlert("Gagal",
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
            });
        }
    }
}
