package com.example.james.connect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by james on 26/02/2018.
 */

public class PhoneCallStateReceiver extends BroadcastReceiver{
    private TelephonyManager myTelMan;
    public static boolean isListening = false;

    @Override
    public void onReceive(final Context context, Intent intent) {
        myTelMan = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        PhoneStateListener myPhoneStateListner = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                switch(state){
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Toast.makeText(context, "CALL_STATE_OFFHOOK", Toast.LENGTH_LONG).show();
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        Toast.makeText(context, "CALL_STATE_IDLE", Toast.LENGTH_LONG).show();
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Toast.makeText(context, "CALL_STATE_RINGING", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        if(!isListening) {
            myTelMan.listen(myPhoneStateListner, PhoneStateListener.LISTEN_CALL_STATE);
            isListening = true;
        }
    }
}
