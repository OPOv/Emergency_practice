package com.example.lg.emergency;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder> {

    private Context mContext;
    private List<KnowledgeItem> items;
    int item_layout;

    public KnowledgeAdapter(Context mContext, List<KnowledgeItem> items, int item_layout){
        this.mContext = mContext;
        this.items = items;
        this.item_layout = item_layout;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_knowledge, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {


        final KnowledgeItem item = items.get(position);
        viewHolder.txtTitle.setText(item.getTitle());
        viewHolder.txtDate.setText(item.getDate());
        viewHolder.txtCont.setText(item.getSubtitle());

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDate, txtCont;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.adapter_name);
            txtDate = itemView.findViewById(R.id.adapter_day);
            txtCont = itemView.findViewById(R.id.adapter_subject);

        }
    }

}
