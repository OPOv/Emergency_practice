package com.example.lg.emergency;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

/*
 *   Class HttpCommunication
 *
 *   1. public HttpCommunication(Context context) : context 및 AsyncHttpClient 설정
 *   2. public void URLConnection(String subURL, final String dbName) : AsyncHttpClient를 사용 API에서 데이터 받아오기
*/

public class HttpCommunication  {
    private static AsyncHttpClient ahClient;
    private Context context;

    public HttpCommunication(Context context)
    {
        this.context = context;
        ahClient = new AsyncHttpClient();
    }

    public void URLConnectionForAPI(final Dao dao, final InformationDB infoDB) {
        ahClient.get(infoDB.getURL(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String jsonData = new String(responseBody);

                if(infoDB.getName().equals("HospitalDB")) {
                    String[] attribute = {"MC_NM", "ROAD_ADDRESS", "LAT", "LNG", "PHONE_NO"};
                    dao.JsonPasing(jsonData, attribute);
                }
                else if(infoDB.getName().equals("ShelterDB")) {
                    String[] attribute = {"TSUNAMI_SHELTER_NM", "ROAD_ADDRESS", "LAT", "LNG", "PHONE_NO"};
                    dao.JsonPasing(jsonData, attribute);
                }
                /*
                else if(infoDB.getName().equals("KnowledgeDB")){
                    String[] attribute = {"TSUNAMI_SHELTER_NM", "ROAD_ADDRESS", "LAT", "LNG", "PHONE_NO"};
                    dao.JsonPasing(jsonData, attribute);
                }
                */
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                /*
                Toast.makeText(context,"네트워크 연결이 불안정 합니다. 네트워크 연결을 확인해 주세요.",Toast.LENGTH_LONG);

                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                */
            }
        });
    }
}
