package com.example.james.connect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by james on 30/03/2018.
 */

public class InterceptCall extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            Toast.makeText(context,"INCOMING CALL", Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {

        }
    }
}
