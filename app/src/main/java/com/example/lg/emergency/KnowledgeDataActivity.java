package com.example.lg.emergency;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class KnowledgeDataActivity extends AppCompatActivity {

    TextView text[] = new TextView[5];
    ImageView image[] = new ImageView[5];

    TextView[][] subText = new TextView[5][10];

    CardView[][] cardViews = new CardView[5][11];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_detail);



        text[0] = (TextView)findViewById(R.id.content_text1);
        text[1] = (TextView)findViewById(R.id.content_text2);
        text[2] = (TextView)findViewById(R.id.content_text3);
        text[3] = (TextView)findViewById(R.id.content_text4);
        text[4] = (TextView)findViewById(R.id.content_text5);


        image[0] = (ImageView)findViewById(R.id.content_image1);
        image[1] = (ImageView)findViewById(R.id.content_image2);
        image[2] = (ImageView)findViewById(R.id.content_image3);
        image[3] = (ImageView)findViewById(R.id.content_image4);
        image[4] = (ImageView)findViewById(R.id.content_image5);

        subText[0][0]= (TextView)findViewById(R.id.tv1_1_KnowledgeDataActivity);
        subText[0][1]= (TextView)findViewById(R.id.tv1_2_KnowledgeDataActivity);
        subText[0][2]= (TextView)findViewById(R.id.tv1_3_KnowledgeDataActivity);
        subText[0][3]= (TextView)findViewById(R.id.tv1_4_KnowledgeDataActivity);
        subText[0][4]= (TextView)findViewById(R.id.tv1_5_KnowledgeDataActivity);
        subText[0][5]= (TextView)findViewById(R.id.tv1_6_KnowledgeDataActivity);
        subText[0][6]= (TextView)findViewById(R.id.tv1_7_KnowledgeDataActivity);
        subText[0][7]= (TextView)findViewById(R.id.tv1_8_KnowledgeDataActivity);
        subText[0][8]= (TextView)findViewById(R.id.tv1_9_KnowledgeDataActivity);
        subText[0][9]= (TextView)findViewById(R.id.tv1_10_KnowledgeDataActivity);

        subText[1][0]= (TextView)findViewById(R.id.tv2_1_KnowledgeDataActivity);
        subText[1][1]= (TextView)findViewById(R.id.tv2_2_KnowledgeDataActivity);
        subText[1][2]= (TextView)findViewById(R.id.tv2_3_KnowledgeDataActivity);
        subText[1][3]= (TextView)findViewById(R.id.tv2_4_KnowledgeDataActivity);
        subText[1][4]= (TextView)findViewById(R.id.tv2_5_KnowledgeDataActivity);
        subText[1][5]= (TextView)findViewById(R.id.tv2_6_KnowledgeDataActivity);
        subText[1][6]= (TextView)findViewById(R.id.tv2_7_KnowledgeDataActivity);
        subText[1][7]= (TextView)findViewById(R.id.tv2_8_KnowledgeDataActivity);
        subText[1][8]= (TextView)findViewById(R.id.tv2_9_KnowledgeDataActivity);
        subText[1][9]= (TextView)findViewById(R.id.tv2_10_KnowledgeDataActivity);

        subText[2][0]= (TextView)findViewById(R.id.tv3_1_KnowledgeDataActivity);
        subText[2][1]= (TextView)findViewById(R.id.tv3_2_KnowledgeDataActivity);
        subText[2][2]= (TextView)findViewById(R.id.tv3_3_KnowledgeDataActivity);
        subText[2][3]= (TextView)findViewById(R.id.tv3_4_KnowledgeDataActivity);
        subText[2][4]= (TextView)findViewById(R.id.tv3_5_KnowledgeDataActivity);
        subText[2][5]= (TextView)findViewById(R.id.tv3_6_KnowledgeDataActivity);
        subText[2][6]= (TextView)findViewById(R.id.tv3_7_KnowledgeDataActivity);
        subText[2][7]= (TextView)findViewById(R.id.tv3_8_KnowledgeDataActivity);
        subText[2][8]= (TextView)findViewById(R.id.tv3_9_KnowledgeDataActivity);
        subText[2][9]= (TextView)findViewById(R.id.tv3_10_KnowledgeDataActivity);

        subText[3][0]= (TextView)findViewById(R.id.tv4_1_KnowledgeDataActivity);
        subText[3][1]= (TextView)findViewById(R.id.tv4_2_KnowledgeDataActivity);
        subText[3][2]= (TextView)findViewById(R.id.tv4_3_KnowledgeDataActivity);
        subText[3][3]= (TextView)findViewById(R.id.tv4_4_KnowledgeDataActivity);
        subText[3][4]= (TextView)findViewById(R.id.tv4_5_KnowledgeDataActivity);
        subText[3][5]= (TextView)findViewById(R.id.tv4_6_KnowledgeDataActivity);
        subText[3][6]= (TextView)findViewById(R.id.tv4_7_KnowledgeDataActivity);
        subText[3][7]= (TextView)findViewById(R.id.tv4_8_KnowledgeDataActivity);
        subText[3][8]= (TextView)findViewById(R.id.tv4_9_KnowledgeDataActivity);
        subText[3][9]= (TextView)findViewById(R.id.tv4_10_KnowledgeDataActivity);


        subText[4][0]= (TextView)findViewById(R.id.tv5_1_KnowledgeDataActivity);
        subText[4][1]= (TextView)findViewById(R.id.tv5_2_KnowledgeDataActivity);
        subText[4][2]= (TextView)findViewById(R.id.tv5_3_KnowledgeDataActivity);
        subText[4][3]= (TextView)findViewById(R.id.tv5_4_KnowledgeDataActivity);
        subText[4][4]= (TextView)findViewById(R.id.tv5_5_KnowledgeDataActivity);
        subText[4][5]= (TextView)findViewById(R.id.tv5_6_KnowledgeDataActivity);
        subText[4][6]= (TextView)findViewById(R.id.tv5_7_KnowledgeDataActivity);
        subText[4][7]= (TextView)findViewById(R.id.tv5_8_KnowledgeDataActivity);
        subText[4][8]= (TextView)findViewById(R.id.tv5_9_KnowledgeDataActivity);
        subText[4][9]= (TextView)findViewById(R.id.tv5_10_KnowledgeDataActivity);

        cardViews[0][0] = (CardView)findViewById(R.id.content_cv_KnowledgeActivity);
        cardViews[0][1] = (CardView)findViewById(R.id.tv1_1_cv_KnowledgeActivity);
        cardViews[0][2] = (CardView)findViewById(R.id.tv1_2_cv_KnowledgeActivity);
        cardViews[0][3] = (CardView)findViewById(R.id.tv1_3_cv_KnowledgeActivity);
        cardViews[0][4] = (CardView)findViewById(R.id.tv1_4_cv_KnowledgeActivity);
        cardViews[0][5] = (CardView)findViewById(R.id.tv1_5_cv_KnowledgeActivity);
        cardViews[0][6] = (CardView)findViewById(R.id.tv1_6_cv_KnowledgeActivity);
        cardViews[0][7] = (CardView)findViewById(R.id.tv1_7_cv_KnowledgeActivity);
        cardViews[0][8] = (CardView)findViewById(R.id.tv1_8_cv_KnowledgeActivity);
        cardViews[0][9] = (CardView)findViewById(R.id.tv1_9_cv_KnowledgeActivity);
        cardViews[0][10] = (CardView)findViewById(R.id.tv1_10_cv_KnowledgeActivity);

        cardViews[1][0] = (CardView)findViewById(R.id.content2_cv_KnowledgeActivity);
        cardViews[1][1] = (CardView)findViewById(R.id.tv2_1_cv_KnowledgeActivity);
        cardViews[1][2] = (CardView)findViewById(R.id.tv2_2_cv_KnowledgeActivity);
        cardViews[1][3] = (CardView)findViewById(R.id.tv2_3_cv_KnowledgeActivity);
        cardViews[1][4] = (CardView)findViewById(R.id.tv2_4_cv_KnowledgeActivity);
        cardViews[1][5] = (CardView)findViewById(R.id.tv2_5_cv_KnowledgeActivity);
        cardViews[1][6] = (CardView)findViewById(R.id.tv2_6_cv_KnowledgeActivity);
        cardViews[1][7] = (CardView)findViewById(R.id.tv2_7_cv_KnowledgeActivity);
        cardViews[1][8] = (CardView)findViewById(R.id.tv2_8_cv_KnowledgeActivity);
        cardViews[1][9] = (CardView)findViewById(R.id.tv2_9_cv_KnowledgeActivity);
        cardViews[1][10] = (CardView)findViewById(R.id.tv2_10_cv_KnowledgeActivity);

        cardViews[2][0] = (CardView)findViewById(R.id.content3_cv_KnowledgeActivity);
        cardViews[2][1] = (CardView)findViewById(R.id.tv3_1_cv_KnowledgeActivity);
        cardViews[2][2] = (CardView)findViewById(R.id.tv3_2_cv_KnowledgeActivity);
        cardViews[2][3] = (CardView)findViewById(R.id.tv3_3_cv_KnowledgeActivity);
        cardViews[2][4] = (CardView)findViewById(R.id.tv3_4_cv_KnowledgeActivity);
        cardViews[2][5] = (CardView)findViewById(R.id.tv3_5_cv_KnowledgeActivity);
        cardViews[2][6] = (CardView)findViewById(R.id.tv3_6_cv_KnowledgeActivity);
        cardViews[2][7] = (CardView)findViewById(R.id.tv3_7_cv_KnowledgeActivity);
        cardViews[2][8] = (CardView)findViewById(R.id.tv3_8_cv_KnowledgeActivity);
        cardViews[2][9] = (CardView)findViewById(R.id.tv3_9_cv_KnowledgeActivity);
        cardViews[2][10] = (CardView)findViewById(R.id.tv3_10_cv_KnowledgeActivity);

        cardViews[3][0] = (CardView)findViewById(R.id.content4_cv_KnowledgeActivity);
        cardViews[3][1] = (CardView)findViewById(R.id.tv4_1_cv_KnowledgeActivity);
        cardViews[3][2] = (CardView)findViewById(R.id.tv4_2_cv_KnowledgeActivity);
        cardViews[3][3] = (CardView)findViewById(R.id.tv4_3_cv_KnowledgeActivity);
        cardViews[3][4] = (CardView)findViewById(R.id.tv4_4_cv_KnowledgeActivity);
        cardViews[3][5] = (CardView)findViewById(R.id.tv4_5_cv_KnowledgeActivity);
        cardViews[3][6] = (CardView)findViewById(R.id.tv4_6_cv_KnowledgeActivity);
        cardViews[3][7] = (CardView)findViewById(R.id.tv4_7_cv_KnowledgeActivity);
        cardViews[3][8] = (CardView)findViewById(R.id.tv4_8_cv_KnowledgeActivity);
        cardViews[3][9] = (CardView)findViewById(R.id.tv4_9_cv_KnowledgeActivity);
        cardViews[3][10] = (CardView)findViewById(R.id.tv4_10_cv_KnowledgeActivity);

        cardViews[4][0] = (CardView)findViewById(R.id.content5_cv_KnowledgeActivity);
        cardViews[4][1] = (CardView)findViewById(R.id.tv5_1_cv_KnowledgeActivity);
        cardViews[4][2] = (CardView)findViewById(R.id.tv5_2_cv_KnowledgeActivity);
        cardViews[4][3] = (CardView)findViewById(R.id.tv5_3_cv_KnowledgeActivity);
        cardViews[4][4] = (CardView)findViewById(R.id.tv5_4_cv_KnowledgeActivity);
        cardViews[4][5] = (CardView)findViewById(R.id.tv5_5_cv_KnowledgeActivity);
        cardViews[4][6] = (CardView)findViewById(R.id.tv5_6_cv_KnowledgeActivity);
        cardViews[4][7] = (CardView)findViewById(R.id.tv5_7_cv_KnowledgeActivity);
        cardViews[4][8] = (CardView)findViewById(R.id.tv5_8_cv_KnowledgeActivity);
        cardViews[4][9] = (CardView)findViewById(R.id.tv5_9_cv_KnowledgeActivity);
        cardViews[4][10] = (CardView)findViewById(R.id.tv5_10_cv_KnowledgeActivity);


        for(int i = 0; i <5 ; i++)
        {
            text[i].setVisibility(View.GONE);
            image[i].setVisibility(View.GONE);
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++) {
                subText[i][j].setVisibility(View.GONE);
                subText[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            }
        }

        for(int i =0 ; i < 5; i++){
            for(int j = 0; j < 11; j++){
                cardViews[i][j].setVisibility(View.GONE);
            }
        }

        Intent intent = getIntent();

//        ImageView imgage = findViewById(R.id.detail_image);
        TextView title = (TextView) findViewById(R.id.detail_name);
//        TextView day = (TextView) findViewById(R.id.detal_day);
//        TextView subject = (TextView) findViewById(R.id.detail_subject);


        int position  = intent.getExtras().getInt("position");


//        Glide.with(this).load(MainActivity.URL_Server+"DetailData/img/1.jpg").into(imgage);
        title.setText(intent.getStringExtra("title"));
//        day.setText(intent.getStringExtra("day"));
//        subject.setText(intent.getStringExtra("subject"));



        //HttpConnection Class 생성자 호출(_url : 연결 할 서버 주소)
        HttpConnetion httpConn = new HttpConnetion(new URLClass(MainActivity.URL_Server + "DetailData"));

        String jsonData;
        try {
            //해당 주소로 연결 해서 Jsondata를 받아옴(String)
            jsonData = httpConn.execute().get();

            /*
             * 받아온 jsonData를 JSONArray 타입으로 변경
             * JSONObject : {"KEY":"VALUE", "KEY1:"VALUE1",....}
             * JSONArray : [JSONObject]
             * JSON은 map을 생각하면 편함(key값을 입력하면 value가 나옴)
             */
            JSONArray jsonArr = new JSONArray(jsonData);
            /*
             * JSONArray 의 j번째 값을 파싱해서 ArrayList에 담음
             * {"TEXT1", "TEXT2","TEXT3", "TEXT4","TEXT5", "IMAGE1","IMAGE2", "IMAGE3","IMAGE4", "IMAGE5"}는 찾을 키값
             */
            ArrayList<String> arrList = httpConn.GetJsonReturnArrayList(jsonArr,
                    new String[]{"TEXT1", "TEXT2", "TEXT3", "TEXT4", "TEXT5", "IMAGE1", "IMAGE2", "IMAGE3", "IMAGE4", "IMAGE5"}, position);

            /*
             * ArrayList에 담긴 Value들을 각각의 TextView와 ImagaView에 출력
             * arrList.get(0~4) : Text
             * arrList.get(4~9) : Image File 명
             * Glide.with(출력할 Activity).load(이미지 저장 장소(server면 url, 어플내부면 R.drawable.xxx).apply(new RequestOptions()
             *                            .override(width값, height값(이미지 크기 조정 생략 가능)).into(출력할 ImageView 선택);
             */
            for(int i = 0; i < 5; i++) {
                if (!arrList.get(i).equals("")) {
                    String[] temp = new String[2];
                    temp[0] = arrList.get(i).split("/textSize/")[0];
                    temp[1] = arrList.get(i).split("/textSize/")[1];
                    text[i].setTextSize(Float.parseFloat(temp[0]));

                    SplitFunc(temp,"/textSytle/");
                    text[i].setTypeface(Typeface.DEFAULT_BOLD);
                    text[i].setText(temp[1].split("/split/")[0]);
                    text[i].setVisibility(View.VISIBLE);

                    for (int c = 0; c < temp[1].split("/split/").length - 1; c++) {
                        subText[i][c].setText(temp[1].split("/split/")[c+1]);
                        subText[i][c].setVisibility(View.VISIBLE);
                        cardViews[i][c].setVisibility(View.VISIBLE);
                    }
                    cardViews[i][temp[1].split("/split/").length-1].setVisibility(View.VISIBLE);
                }
            }

            for (int i = 5; i < 10; i++)
                if (!arrList.get(i).equals("")) {
                    GlideApp.with(this).load(MainActivity.URL_Server + "img/detail/" + (position + 1) + "_" + (i - 4) + ".jpg").into(image[i - 5]);
                    image[i - 5].setVisibility(View.VISIBLE);
                }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }










    }

    public void SplitFunc(String[] temp,String regex){
        temp[0] = temp[1].split(regex)[0];
        temp[1] = temp[1].split(regex)[1];
    }


    public void back_btn(View view) {
        finish();
    }
}
