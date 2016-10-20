package com.rpiconn.app;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.Toast;

import com.rpiconn.app.TCPClient;

public class connectTask extends AsyncTask<String,String,Boolean> {
    public Context myctx;
    CheckBox ckbox;
    Boolean ok;
    String ip;

    connectTask(String theip,Context thectx,CheckBox theckbox)
    {
        ip = theip;
        myctx = thectx;
        ckbox = theckbox;
    }

    @Override
    protected Boolean doInBackground(String... message) {

        //we create a TCPClient object and
        TCPClient tmp = new TCPClient(ip);
        ok = tmp.run();

        return ok;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        // This executes in the UI thread

        int duration = Toast.LENGTH_SHORT;
        final CharSequence text;

        if(ok)
        {
            text = "Succesfully connected";

            ckbox.setChecked(true);
        }
        else
        {
            text = "Failed to connect";
        }

        Toast.makeText(myctx, text, Toast.LENGTH_SHORT).show();
    }
}