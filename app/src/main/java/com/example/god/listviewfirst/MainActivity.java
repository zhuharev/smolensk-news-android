package com.example.god.listviewfirst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity {

    public static String EXTRA_MESSAGE = "msg";

    Boolean setted = false;
    ArrayList<String> items = new ArrayList<String>();
    public static JSONArray cachedArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Новости Смоленска");


        try {
            getPublicTimeline();
        }catch(JSONException e){}

    }


    public void sendMesg(Integer position) {
        Intent intent = new Intent(getApplicationContext(), News.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        startActivity(intent);
    }


        public void getPublicTimeline() throws JSONException {
            if (setted) {
                Log.d("deb","setted");
                return;
            }
            RestClient.get("http://smoljanin.ru/feed.json", null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray jArray) {

                    cachedArray = jArray;
                    setted = true;

                    if (setted) {
                        for (int i = 0; i < cachedArray.length(); i++) {

                            try {
                                JSONObject json_data = cachedArray.getJSONObject(i);
                                String name = json_data.getString("Title");
                                items.add(name);
                            } catch (JSONException e) {
                            }
                            //Log.d(name, "Output");
                        }
                    }

                    ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.activity_listview, items);

                    ListView listView = (ListView) findViewById(R.id.mobile_list);
                    listView.setAdapter(mArrayAdapter);


                    //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);


                    //listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                           // Toast.makeText(getApplicationContext(),
                            //        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                            //        .show();
                            sendMesg(position);
                        }
                    });

                }
            });
        }


}
