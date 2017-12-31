package com.example.root.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcoscg.dialogsheet.DialogSheet;

import java.util.List;

/**
 * Created by ariya on 12/30/17.
 */

public class AdapterRead extends RecyclerView.Adapter<AdapterRead.Holder>
{
    private List<ReadModel> list;
    private Context context;

    public AdapterRead (Context context, List<ReadModel> list)
    {
        this.list=list;
        this.context=context;
    }
    @Override
    public AdapterRead.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        AdapterRead.Holder holder = new AdapterRead.Holder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterRead.Holder holder, int position) {
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
        public Holder(View itemView) {
            super(itemView);
            tv_nama=(TextView)itemView.findViewById(R.id.tv_nama);
            tv_nrp=(TextView)itemView.findViewById(R.id.tv_nrp);
            tv_penghasilan=(TextView)itemView.findViewById(R.id.tv_ukt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    new DialogSheet(context)
                            .setTitle(R.string.detail)
                            .setMessage(R.string.pesandetail)
                            .setCancelable(false)
                            .setPositiveButton(R.string.Lihat, new DialogSheet.OnPositiveClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context,DetailedActivity.class);
                                    intent.putExtra("nrp",readModel.getNrp());
                                    intent.putExtra("nama",readModel.getNama());
                                    intent.putExtra("penghasilan",readModel.getPenghasilan());
                                    context.startActivity(intent);

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

