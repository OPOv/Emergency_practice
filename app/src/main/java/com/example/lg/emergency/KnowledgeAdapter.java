package com.example.lg.emergency;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.List;


public class KnowledgeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<KnowledgeItem> items;
    private URLClass _url;


    private final int HEADER = -2;
    private final int MIDDLE = -1;
    private final int BODY = 0;

    public KnowledgeAdapter(Context mContext, List<KnowledgeItem> items, URLClass _url){
        this.mContext = mContext;
        this.items = items;
        this._url = _url;
    }

    @Override
    public int getItemViewType(int position){

        if(position == 0)
            return HEADER;
        else if (position ==1 )
            return MIDDLE;
        else
            return BODY;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(viewType == HEADER){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main_top, null);
            return new KnowledgeHeader(v);
        }
        else if (viewType == MIDDLE)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main_middle, null);
            return new KnowledgeMiddle(v);
        }
        else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_knowledge, null);
            return new KnowledgeViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if(viewHolder instanceof KnowledgeViewHolder) {
            final KnowledgeItem item = items.get(position - 2);

//            Drawable drawable=mContext.getResources().getDrawable(item.getImage());
            //((KnowledgeViewHolder)viewHolder).imgImage.setImageResource(item.getImage());
            Glide.with(mContext).load(_url.getURL() + item.getImage()).into(((KnowledgeViewHolder)viewHolder).imgImage);
            ((KnowledgeViewHolder)viewHolder).txtTitle.setText(item.getTitle());
            ((KnowledgeViewHolder)viewHolder).txtDate.setText(item.getDate());
            ((KnowledgeViewHolder)viewHolder).txtCont.setText(item.getSubtitle());
        }

        else if(viewHolder instanceof KnowledgeHeader){
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
                    Intent mIntent = new Intent(mContext, DMapView.class);
                    mContext.startActivity(mIntent);
                    
                }
            });
        }

        else{

        }

    }

    @Override
    public int getItemCount() {
        return this.items.size() + 1;
    }

    class KnowledgeHeader extends RecyclerView.ViewHolder{
        ImageButton btnShelter, btnHospital;

        public KnowledgeHeader(View itemView) {
            super(itemView);
            btnHospital = itemView.findViewById(R.id.btn_hospital);
            btnShelter = itemView.findViewById(R.id.btn_shelter);
        }
    }


    class KnowledgeMiddle extends RecyclerView.ViewHolder{
        ImageButton btnShelter, btnHospital;
        TextView textView;
        public KnowledgeMiddle(View itemView) {
            super(itemView);


        }
    }

    class KnowledgeViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDate, txtCont;
        ImageView imgImage;
        public KnowledgeViewHolder(View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.adapter_image);
            txtTitle = itemView.findViewById(R.id.adapter_name);
            txtDate = itemView.findViewById(R.id.adapter_day);
            txtCont = itemView.findViewById(R.id.adapter_subject);



        }
    }

}
