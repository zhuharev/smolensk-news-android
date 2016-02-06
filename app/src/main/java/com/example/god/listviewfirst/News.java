package com.example.god.listviewfirst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by god on 05.02.16.
 */
public class News extends Activity {


    String DEBUG_TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_message);

        Intent intent = getIntent();
        Integer pos = intent.getIntExtra(MainActivity.EXTRA_MESSAGE,0);

        TextView textView = (TextView) findViewById(R.id.textView3);


        try {
            JSONObject obj = MainActivity.cachedArray.getJSONObject(pos);
            String msg = obj.getString("Body");
            textView.setText(msg);
            setTitle(obj.getString("Title"));
        }catch (JSONException e){}


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle app bar item clicks here. The app bar
        // automatically handles clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }



}


