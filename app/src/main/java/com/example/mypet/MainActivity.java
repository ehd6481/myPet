package com.example.mypet;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {

    String myJSON;

    private static final String TAG_RESULTS1 = "result1";
    private static final String TAG_ID1 = "id1";
    private static final String TAG_PETNAME1 = "petname1";
    private static final String TAG_DATE1 = "date1";
    private static final String TAG_CorD1 = "CorD1";
    private static final String TAG_COUNT1 = "count1";
    //private static final String TAG_RESULTS2 = "result2";
    //private static final String TAG_ID2 = "id2";
    //private static final String TAG_PETNAME2 = "petname2";
    //private static final String TAG_DATE2 = "date2";
    //private static final String TAG_CorD2 = "CorD2";
    //private static final String TAG_COUNT2 = "count2";
    //private static final String TAG_RESULTS3 = "result3";
    //private static final String TAG_ID3 = "id3";
    //private static final String TAG_PETNAME3 = "petname3";
    //private static final String TAG_DATE3 = "date3";
    //private static final String TAG_CorD3 = "CorD3";
    //private static final String TAG_COUNT3 = "count3";
    //private static final String TAG_RESULTS4 = "result4";
    //private static final String TAG_ID4 = "id4";
    //private static final String TAG_PETNAME4 = "petname4";
    //private static final String TAG_DATE4 = "date4";
    //private static final String TAG_CorD4 = "CorD4";
    //private static final String TAG_COUNT4 = "count4";

    JSONArray Ddisease = null;

    ArrayList<HashMap<String, String>> DdiseaseList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listView);
        DdiseaseList = new ArrayList<HashMap<String, String>>();
        getData("http://35.196.162.160/mypet.php"); //수정 필요
    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            Ddisease = jsonObj.getJSONArray(TAG_RESULTS1);

            for (int i = 0; i < Ddisease.length(); i++) {
                JSONObject c = Ddisease.getJSONObject(i);
                String id1 = c.getString(TAG_ID1);
                String petname1 = c.getString(TAG_PETNAME1);
                String date1 = c.getString(TAG_DATE1);
                String CorD1 =c.getString(TAG_CorD1);
                String count1=c.getString(TAG_COUNT1);
                //String id2 = c.getString(TAG_ID2);
                //String petname2 = c.getString(TAG_PETNAME2);
               // String date2 = c.getString(TAG_DATE2);
                //String CorD2 =c.getString(TAG_CorD2);
                //String count2=c.getString(TAG_COUNT2);
                //String id3 = c.getString(TAG_ID3);
                //String petname3 = c.getString(TAG_PETNAME3);
                //String date3 = c.getString(TAG_DATE3);
                //String CorD3 =c.getString(TAG_CorD3);
                //String count3=c.getString(TAG_COUNT3);
                //String id4 = c.getString(TAG_ID4);
                //String petname4 = c.getString(TAG_PETNAME4);
                //String date4 = c.getString(TAG_DATE4);
                //String CorD4 =c.getString(TAG_CorD4);
               //String count4=c.getString(TAG_COUNT4);

                HashMap<String, String> Ddisease = new HashMap<String, String>();

                Ddisease.put(TAG_ID1, id1);
                Ddisease.put(TAG_PETNAME1, petname1);
                Ddisease.put(TAG_DATE1, date1);
                Ddisease.put(TAG_CorD1, CorD1);
                Ddisease.put(TAG_COUNT1, count1);
                //Ddisease.put(TAG_ID2, id2);
                //Ddisease.put(TAG_PETNAME2, petname2);
                //Ddisease.put(TAG_DATE2, date2);
               // Ddisease.put(TAG_CorD2, CorD2);
                //Ddisease.put(TAG_COUNT2, count2);
                //Ddisease.put(TAG_ID3, id3);
                //Ddisease.put(TAG_PETNAME3, petname3);
                //Ddisease.put(TAG_DATE3, date3);
                //Ddisease.put(TAG_CorD3, CorD3);
                //Ddisease.put(TAG_COUNT3, count3);
               // Ddisease.put(TAG_ID4, id4);
                //Ddisease.put(TAG_PETNAME4, petname4);
                //Ddisease.put(TAG_DATE4, date4);
                //Ddisease.put(TAG_CorD4, CorD4);
                //Ddisease.put(TAG_COUNT4, count4);

                DdiseaseList.add(Ddisease);
            }

            ListAdapter adapter1 = new SimpleAdapter(
                    MainActivity.this, DdiseaseList, R.layout.listview_item,
                    new String[]{TAG_ID1, TAG_PETNAME1, TAG_DATE1,TAG_CorD1,TAG_COUNT1},
                    new int[]{R.id.id, R.id.petname, R.id.date,R.id.CorD,R.id.count}

            );


            list.setAdapter(adapter1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }


            }

            @Override
            protected void onPostExecute(String result1) {
                myJSON = result1;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}

