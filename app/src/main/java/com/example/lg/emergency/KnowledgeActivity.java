package com.example.lg.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;


public class KnowledgeActivity extends AppCompatActivity {


    private Spinner spinner;
    static final String[] mitem1 = {"심폐소생술 실습","ADV 사용 방법" , "지진발생시 대피요령","전쟁시 피난하기","심폐소생술 실습","ADV 사용 방법" , "지진발생시 대피요령","전쟁시 피난하기"} ;
    static final String[] mitem2 = {"2017/10/5","2018/05/17","2016/05/16","2019/12/25","2017/10/5","2018/05/17","2016/05/16","2019/12/25"} ;
    static final String[] mitem3 = {"천재지변","질병","천재지변","전쟁","천재지변","질병","천재지변","전쟁"} ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);

/////////////////////////////////////////////////////////////////////////////////// listview
        Adapter adapter =  new KnowledgeAdapter(this,mitem1,mitem2,mitem3);


        final ListView listview = (ListView) findViewById(R.id.knowledge_listview) ;

        listview.setAdapter((ListAdapter) adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),KnowledgeDataActivity.class);
                intent.putExtra("title", mitem1[position]);
                intent.putExtra("day", mitem2[position]);
                intent.putExtra("subject", mitem3[position]);


                startActivity(intent);


            }
        });
        ////////////////////////////////////////////////////////////////////////////////end listview

        String[] spinner_list = new String[4];
        spinner_list[0] = "전체보기";
        spinner_list[1] = "위급상황";
        spinner_list[2] = "전쟁상황";
        spinner_list[3] = "천재지변";
        spinner = (Spinner)findViewById(R.id.knowledge_spinner);
        ArrayAdapter spinnerAdapter;
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, spinner_list);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0)
                {
//                  //  Adapter adapter =  new KnowledgeAdapter(this,mitem1,mitem2,mitem3);
//
//
//                    final ListView listview = (ListView) findViewById(R.id.knowledge_listview) ;
//
//                    listview.setAdapter((ListAdapter) adapter);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



}
