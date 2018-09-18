package com.example.lg.emergency;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/*
 *   Class HttpCommunication
 *
 *   1. public HttpCommunication(Context context) : context 및 AsyncHttpClient 설정
 *   2. public void URLConnection(String subURL, final String dbName) : AsyncHttpClient를 사용 API에서 데이터 받아오기
*/

public class HttpCommunication {
    private static AsyncHttpClient ahClient;
    private static String apiURL = "http://data.gwd.go.kr/apiservice/KEY/json/";
    private Context context;

    public HttpCommunication(Context context)
    {
        this.context = context;
        ahClient = new AsyncHttpClient();
    }

    public void URLConnection(String subURL, final String dbName)
    {
        ahClient.get(apiURL + subURL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("TEST1 AsyncHttpClient","연결성공 status code : " + statusCode);
                if(statusCode == 200) {

                    String jsonData = new String(responseBody);

                    Dao dao = new Dao(context,dbName);
                    dao.InsertDB(jsonData);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TEST1 AsyncHttpClient","연결실패 status code : " + statusCode);
            }
        });
    }
}
