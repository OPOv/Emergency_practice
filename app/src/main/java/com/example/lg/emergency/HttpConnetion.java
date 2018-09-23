package com.example.lg.emergency;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/*
 *   Class HttpConnection
 *
 *   1. public HttpConnetion(String _url) : 생성자
 *   2. public String doInBackground(String... strings) : http 통신 실행
 *   3. public ArrayList<String> JsonPasing(JSONObject jsonObj, String[] attribute) throws JSONException  : JSON 파싱
 *   4. public ArrayList<String> Prepare(JSONArray jsonArr, String[] attribute, int i) throws JSONException : JSON 파싱
 */

public class HttpConnetion extends AsyncTask<String, Void, String>{

    private String _url;

    public HttpConnetion(String _url) {
        this._url = _url;
    }

    @Override
    protected String doInBackground(String... strings) {
        String str,receiveMsg = "";
        URL url = null;
        try {
            url = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                //연결 제한 시간을 1/1000 초 단위로 지정합니다.
                //0이면 무한 대기입니다.
                conn.setConnectTimeout(10000);

                //읽기 제한 시간을 지정합니다. 0이면 무한 대기합니다.
                //conn.setReadTimeout(0);

                //캐쉬 사용여부를 지정합니다.
                conn.setUseCaches(false);

                //http 연결의 경우 요청방식을 지정할수 있습니다.
                //지정하지 않으면 디폴트인 GET 방식이 적용됩니다.
                conn.setRequestMethod("GET");

                //서버에 요청을 보내가 응답 결과를 받아옵니다.
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                int resCode = conn.getResponseCode();


                if (resCode == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    reader.close();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }

    public ArrayList<String> JsonPasing(JSONObject jsonObj, String[] attribute) throws JSONException {
        ArrayList<String> arrList = new ArrayList(attribute.length);

        for (int i = 0; i < attribute.length; i++) {
            arrList.add(jsonObj.getString(attribute[i]));
        }
        return arrList;
    }

    public ArrayList<String> Prepare(JSONArray jsonArr, String[] attribute, int i) throws JSONException {
        JSONObject jsonObj = jsonArr.getJSONObject(i);
        return this.JsonPasing(jsonObj, attribute);
    }
}
