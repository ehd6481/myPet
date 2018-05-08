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

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_PETNAME = "petname";
    private static final String TAG_DATE = "date";
    private static final String TAG_CorD = "CorD";
    private static final String TAG_COUNT = "count";

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
            Ddisease = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < Ddisease.length(); i++) {
                JSONObject c = Ddisease.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String petname = c.getString(TAG_PETNAME);
                String date = c.getString(TAG_DATE);
                String CorD =c.getString(TAG_CorD);
                String count=c.getString(TAG_COUNT);

                HashMap<String, String> Ddisease = new HashMap<String, String>();

                Ddisease.put(TAG_ID, id);
                Ddisease.put(TAG_PETNAME, petname);
                Ddisease.put(TAG_DATE, date);
                Ddisease.put(TAG_CorD, CorD);
                Ddisease.put(TAG_COUNT, count);

                DdiseaseList.add(Ddisease);
            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, DdiseaseList, R.layout.listview_item,
                    new String[]{TAG_ID, TAG_PETNAME, TAG_DATE,TAG_CorD,TAG_COUNT},
                    new int[]{R.id.id, R.id.petname, R.id.date,R.id.CorD,R.id.count}
            );

            list.setAdapter(adapter);

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
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}

