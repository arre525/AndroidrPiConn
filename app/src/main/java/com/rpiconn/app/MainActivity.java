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


public class MainActivity extends ActionBarActivity  implements AsyncResponse{


    connectTask asyncTask;
    String ipKey = "com.rpiconn.app.ipkey";


    public void onBackPressed()
    {

            finish();
    }



    public void processFinish(boolean ok){ // A delegate in the mainactivity, but this is -still- not the right (UI) thread
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;
        CharSequence text;

        if(ok)
        {
            runOnUiThread(new Runnable() {
                public void run()
                {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Succesfully connected", Toast.LENGTH_SHORT).show();
                }
            });
            CheckBox chkWindows = (CheckBox) findViewById(R.id.checkBox);
            chkWindows.setChecked(true);
        }
        else
        {
            runOnUiThread(new Runnable() {
                public void run()
                {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Failed to connect", Toast.LENGTH_SHORT).show();
                }
            });
        }


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


        asyncTask = new connectTask(ip);

        thefield.addTextChangedListener(new MyTextWatcher(prefs,thefield));


        asyncTask.delegate = this;
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
