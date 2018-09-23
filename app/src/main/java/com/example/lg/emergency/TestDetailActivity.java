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

        HttpConnetion httpConn = new HttpConnetion(MainActivity.URL_Server + "DetailData");

        String jsonData;
        try {
            jsonData = httpConn.execute().get();

            JSONArray jsonArr = new JSONArray(jsonData);
            JSONObject jsonObj;
            String[] attribute = {"TEXT1", "TEXT2","TEXT3", "TEXT4","TEXT5", "IMAGE1","IMAGE2", "IMAGE3","IMAGE4", "IMAGE5",};
            for(int j = 0; j < jsonArr.length(); j++ ) {
                jsonObj = jsonArr.getJSONObject(j);
                ArrayList<String> arrList = httpConn.JsonPasing(jsonObj, attribute);

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
