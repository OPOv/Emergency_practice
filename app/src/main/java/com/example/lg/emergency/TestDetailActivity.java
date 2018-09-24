package com.example.lg.emergency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TestDetailActivity extends AppCompatActivity {

    TextView text[] = new TextView[5];
    ImageView img[] = new ImageView[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail);


        // TextView 및 ImageView 연결
        text[0] = (TextView)findViewById(R.id.textView);
        text[1] = (TextView)findViewById(R.id.textView1);
        text[2] = (TextView)findViewById(R.id.textView2);
        text[3] = (TextView)findViewById(R.id.textView3);
        text[4] = (TextView)findViewById(R.id.textView4);

        img[0] = (ImageView)findViewById(R.id.imageView);
        img[1] = (ImageView)findViewById(R.id.imageView1);
        img[2] = (ImageView)findViewById(R.id.imageView2);
        img[3] = (ImageView)findViewById(R.id.imageView3);
        img[4] = (ImageView)findViewById(R.id.imageView4);

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
            for(int j = 0; j < jsonArr.length(); j++ ) {
                /*
                 * JSONArray 의 j번째 값을 파싱해서 ArrayList에 담음
                 * {"TEXT1", "TEXT2","TEXT3", "TEXT4","TEXT5", "IMAGE1","IMAGE2", "IMAGE3","IMAGE4", "IMAGE5"}는 찾을 키값
                 */
                ArrayList<String> arrList = httpConn.GetJsonReturnArrayList(jsonArr,
                        new String[]{"TEXT1", "TEXT2","TEXT3", "TEXT4","TEXT5", "IMAGE1","IMAGE2", "IMAGE3","IMAGE4", "IMAGE5"},j);

                /*
                 * ArrayList에 담긴 Value들을 각각의 TextView와 ImagaView에 출력
                 * arrList.get(0~4) : Text
                 * arrList.get(4~9) : Image File 명
                 * Glide.with(출력할 Activity).load(이미지 저장 장소(server면 url, 어플내부면 R.drawable.xxx).apply(new RequestOptions()
                 *                            .override(width값, height값(이미지 크기 조정 생략 가능)).into(출력할 ImageView 선택);
                 */
                for(int i = 0; i < 5; i++)
                    if(!arrList.get(i).equals(""))
                        text[i].setText(arrList.get(i));
                for(int i = 5; i< 10; i++)
                    if(!arrList.get(i).equals(""))
                        Glide.with(this).load(MainActivity.URL_Server+"DetailData/img/1_"+ (i-4) + ".jpg").apply(new RequestOptions().override(900,500)).into(img[i-5]);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
