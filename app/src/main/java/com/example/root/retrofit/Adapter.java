package com.example.root.retrofit;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcoscg.dialogsheet.DialogSheet;

import java.util.List;

/**
 * Created by root on 12/26/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holderdata>
{
    private List<ReadModel> list;
    private Context context;

    public Adapter (Context context, List<ReadModel> list)
    {
        this.list=list;
        this.context=context;
    }

    @Override
    public Holderdata onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        Holderdata holderdata = new Holderdata(layout);
        return holderdata;
    }

    @Override
    public void onBindViewHolder(Holderdata holder, int position) {
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

    class Holderdata extends RecyclerView.ViewHolder{
        TextView tv_nama,tv_nrp,tv_penghasilan;
        ReadModel readModel;
        public Holderdata(View itemView) {
            super(itemView);
            tv_nama=(TextView)itemView.findViewById(R.id.tv_nama);
            tv_nrp=(TextView)itemView.findViewById(R.id.tv_nrp);
            tv_penghasilan=(TextView)itemView.findViewById(R.id.tv_ukt);
        }
    }
}
