package com.rpiconn.app;

import android.os.AsyncTask;

import com.rpiconn.app.TCPClient;


/**
 * Created by Arnout on 20/10/2016.
 */
public class connectTask extends AsyncTask<String,String,Integer> {
    public AsyncResponse delegate = null;
    boolean ok;
    String ip;

    connectTask(String theip)
    {
        ip = theip;
    }

    @Override
    protected Integer doInBackground(String... message) {

        //we create a TCPClient object and
        TCPClient tmp = new TCPClient(ip);
        ok = tmp.run();
        delegate.processFinish(ok);

        return 1;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(Integer... result) {

    }
}