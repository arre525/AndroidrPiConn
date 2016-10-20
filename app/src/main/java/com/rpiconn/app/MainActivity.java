package com.rpiconn.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.Format;


public class MainActivity extends ActionBarActivity{


    connectTask asyncTask;
    String ipKey = "com.rpiconn.app.ipkey";

    // Make sure if back is pressed, the app closes, so if we restart it, it tries to connect again
    public void onBackPressed()
    {
            finish();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Read the stored IP address and set it in our textfield
        EditText thefield = (EditText) findViewById(R.id.editText);
        SharedPreferences prefs = this.getSharedPreferences(
                "com.rpiconn.app", Context.MODE_PRIVATE);

        String ip = prefs.getString(ipKey, "192.168.0.226");
        thefield.setText(ip);

        // Start the async task, and make sure that if the ip field changes, we remember it
        asyncTask = new connectTask(ip,getApplicationContext(),(CheckBox) findViewById(R.id.checkBox));
        thefield.addTextChangedListener(new MyTextWatcher(prefs,thefield));
        asyncTask.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}
