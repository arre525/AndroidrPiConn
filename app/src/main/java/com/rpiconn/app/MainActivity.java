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


    public void onBackPressed()
    {

            finish();
    }




    //this override the implemented method from asyncTask
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText thefield = (EditText) findViewById(R.id.editText);
        SharedPreferences prefs = this.getSharedPreferences(
                "com.rpiconn.app", Context.MODE_PRIVATE);


        String ip = prefs.getString(ipKey, "192.168.0.226");

        thefield.setText(ip);


        asyncTask = new connectTask(ip,getApplicationContext(),(CheckBox) findViewById(R.id.checkBox));

        thefield.addTextChangedListener(new MyTextWatcher(prefs,thefield));

        asyncTask.execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}
