package com.rpiconn.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Arnout on 20/10/2016.
 */
public class MyTextWatcher implements TextWatcher
{
    SharedPreferences prefs;
    EditText textfield;
    MyTextWatcher(SharedPreferences inprefs,EditText intextfield)
    {
        prefs=inprefs;
        textfield=intextfield;
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start,
                                  int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start,
                              int before, int count) {

        String ipKey = "com.rpiconn.app.ipkey";
        prefs.edit().putString(ipKey, textfield.getText().toString()).apply();
    }
}
