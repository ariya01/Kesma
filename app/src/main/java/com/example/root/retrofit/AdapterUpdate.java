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
 * Created by root on 12/28/17.
 */

public class AdapterUpdate extends RecyclerView.Adapter<AdapterUpdate.Holder>{
    private List<ReadModel> list;
    private Context context;

    public AdapterUpdate (Context context, List<ReadModel> list)
    {
        this.list=list;
        this.context=context;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        Holder holder = new Holder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
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
                            .setTitle(R.string.update)
                            .setMessage(R.string.pesanupdate)
                            .setCancelable(false)
                            .setPositiveButton("Update", new DialogSheet.OnPositiveClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context,UpdateDataActivity.class);
                                    intent.putExtra("nrp",readModel.getNrp());
                                    intent.putExtra("nama",readModel.getNama());
                                    intent.putExtra("penghasilan",readModel.getPenghasilan());
                                    context.startActivity(intent);

                                }
                            })
                            .setNegativeButton("Cencel", new DialogSheet.OnNegativeClickListener() {
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
