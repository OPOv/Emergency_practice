package com.example.lg.emergency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class knowledge_listview_adapter extends ArrayAdapter<String> {

    String[] Item1;
    String[] Item2;
    String[] Item3;



    knowledge_listview_adapter(KnowledgeActivity context, String[] item1, String[] item2, String[] item3)
    {
        super(context, R.layout.activity_knowledge_listview, item1);


        this.Item1 = item1;
        this.Item2 = item2;
        this.Item3 = item3;



    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater imageInflater = LayoutInflater.from(getContext());//여기서 오류날수있음
        View view = imageInflater.inflate(R.layout.activity_knowledge_listview,parent,false);
        TextView name =(TextView) view.findViewById(R.id.adapter_name);
        TextView day =(TextView) view.findViewById(R.id.adapter_day);
        TextView subject =(TextView) view.findViewById(R.id.adapter_subject);


        String i1 =Item1[position];
        String i2 =Item2[position];
        String i3 =Item3[position];



        name.setText(i1);
        day.setText(i2);
        subject.setText(i3);


        return view;
    }

}
