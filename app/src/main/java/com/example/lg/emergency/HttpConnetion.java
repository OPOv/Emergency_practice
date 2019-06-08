package com.example.lg.emergency;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;

/*
 *   Class HttpConnection
 *
 *   1. public HttpConnetion(InformationDB infoDB, Dao dao) : 생성자
 *   2. public String doInBackground(String... strings) : http 통신 실행
 *   3. public ArrayList<String> JsonPasing(JSONObject jsonObj, String[] attribute) throws JSONException  : JSON 파싱
 *   4. public ArrayList<String> Prepare(JSONArray jsonArr, String[] attribute, int i) throws JSONException : JSON 파싱
 */

public class HttpConnetion extends AsyncTask<String, Void, String> {

    private InformationDB infoDB;
    private Dao dao;
    private Context context;
    private Exception e;
    private ProgressDialog progressDialog;

    public HttpConnetion(InformationDB infoDB, Dao dao,Context context) {
        this.infoDB = infoDB;
        this.dao = dao;
        this.context = context;
        progressDialog = new ProgressDialog((Activity)context);
    }

    public HttpConnetion(URLClass _url, Context context) {
        this.infoDB = new InformationDB("","","",_url);
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        if(infoDB.getName().equals("HospitalDB")) {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("초기 설정중입니다. 잠시만 기다려주세요...");
            progressDialog.show();
        }
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String str, receiveMsg = "";
        URL url = null;
        try {
            url = new URL(infoDB.getURL());
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
        } catch (IOException e) {
            //인터넷 연결 실패에 대한 exception 추가
            this.e = e;
        }

        return receiveMsg;
    }

    @Override
    protected void onPostExecute(String jsonData) {
        if(e != null){
            ExceptionHandling exceptHandling = new ExceptionHandling(e,context,"인터넷 연결이 불안정 합니다. \n인터넷 연결 상태를 확인 후 어플리케이션을 재실행 하십시오.");
            exceptHandling.StartingExceptionDialog();
        }
        else {
            if (infoDB.getName().equals("HospitalDB") || infoDB.getName().equals("ShelterDB") || infoDB.getName().equals("AEDDB")) {
                JSONArray jsonArr = null;
                try {
                    jsonArr = new JSONArray(jsonData);

                    if (infoDB.getName().equals("HospitalDB"))
                        this.GetJsonAndExecuteSQL(jsonArr, new String[]{"MC_NM", "ROAD_ADDRESS", "LAT", "LNG", "PHONE_NO"}, infoDB.getName());
                    else if (infoDB.getName().equals("ShelterDB"))
                        this.GetJsonAndExecuteSQL(jsonArr, new String[]{"vt_acmdfclty_nm", "dtl_adres", "ycord", "xcord", "mngps_telno"}, infoDB.getName());
                    else if (infoDB.getName().equals("AEDDB"))
                        this.GetJsonAndExecuteSQL(jsonArr, new String[]{"buildPlace", "buildAddress", "wgs84Lat", "wgs84Lon", "managerTel"}, infoDB.getName());
                } catch (JSONException e) {
                    ExceptionHandling exceptHandling = new ExceptionHandling(e,context,"초기 설정 중 문제가 발생했습니다. \n지속적으로 문제발생시 어플리케이션 개발자에게 문의하십시오");
                    exceptHandling.StartingExceptionDialog();
                }
                if(infoDB.getName().equals("HospitalDB"))
                    progressDialog.dismiss();
            }
        }

        super.onPostExecute(jsonData);
    }

    public ArrayList<String> JsonParsing(JSONObject jsonObj, String[] attribute, String dbName) throws JSONException {
        ArrayList<String> arrList = new ArrayList<>(attribute.length);
        for (int i = 0; i < attribute.length; i++) {
            if(dbName.equals("AEDDB")){
                JSONObject jsonObj1 = jsonObj.getJSONObject(attribute[i]);
                arrList.add(jsonObj1.getString("_text"));
            }
            else
                arrList.add(jsonObj.getString(attribute[i]));
        }
        return arrList;
    }

    public String MakeSql(String[] attribute,ArrayList<String> arrList,InformationDB infoDB,int j) {
        String val = "";

        for(int c = 0; c < attribute.length; c++)
            val +=  ", '" + arrList.get(c) + "'";

        String sql = "INSERT INTO " + infoDB.getName() + infoDB.getAttribute1()
                + " VALUES(" + j + val + ");";

        return sql;
    }

    public ArrayList<String> GetJsonReturnArrayList(JSONArray jsonArr, String[] attribute, int i, String dbName) throws JSONException {
        JSONObject jsonObj = jsonArr.getJSONObject(i);
        return this.JsonParsing(jsonObj,attribute, dbName);
    }

    public void GetJsonAndExecuteSQL(JSONArray jsonArr, String[] attribute, String dbName) throws JSONException {
        for (int i = 0; i < jsonArr.length(); i++) {
            ArrayList<String> arrList = GetJsonReturnArrayList(jsonArr,attribute,i,dbName);

            dao.ExecuteSQL(MakeSql(attribute, arrList, infoDB, i));
        }
    }
}
