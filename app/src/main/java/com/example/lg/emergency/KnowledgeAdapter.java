package com.example.lg.emergency;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lg.emergency.nmap.NMapViewer;

import java.util.List;


public class KnowledgeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<KnowledgeItem> items;

    private final int HEADER = -1;
    private final int BODY = 0;

    public KnowledgeAdapter(Context mContext, List<KnowledgeItem> items){
        this.mContext = mContext;
        this.items = items;

    }

    @Override
    public int getItemViewType(int position){

        if(position == 0)
            return HEADER;
        else
            return BODY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(viewType == HEADER){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main_top, null);
            return new KnowledgeHeader(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_knowledge, null);
            return new KnowledgeViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if(viewHolder instanceof KnowledgeViewHolder) {
            final KnowledgeItem item = items.get(position - 1);
            ((KnowledgeViewHolder)viewHolder).txtTitle.setText(item.getTitle());
            ((KnowledgeViewHolder)viewHolder).txtDate.setText(item.getDate());
            ((KnowledgeViewHolder)viewHolder).txtCont.setText(item.getSubtitle());
        } else{
            ((KnowledgeHeader)viewHolder).btnShelter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, ShelterActivity.class);
                    mContext.startActivity(mIntent);
                }
            });

            ((KnowledgeHeader)viewHolder).btnHospital.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, NMapViewer.class);
                    mContext.startActivity(mIntent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return this.items.size() + 1;
    }

    class KnowledgeHeader extends RecyclerView.ViewHolder{
        Button btnShelter, btnHospital;

        public KnowledgeHeader(View itemView) {
            super(itemView);
            btnHospital = itemView.findViewById(R.id.btn_hospital);
            btnShelter = itemView.findViewById(R.id.btn_shelter);
        }
    }

    class KnowledgeViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDate, txtCont;

        public KnowledgeViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.adapter_name);
            txtDate = itemView.findViewById(R.id.adapter_day);
            txtCont = itemView.findViewById(R.id.adapter_subject);

        }
    }

}