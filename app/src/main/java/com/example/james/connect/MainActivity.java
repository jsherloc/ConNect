package com.example.james.connect;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 3;
    String telPhoneNo;
    EditText phoneField;
    private TelephonyManager myTelephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTelephonyManager = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        Button myButton = (Button) findViewById(R.id.button);
        phoneField = (EditText) findViewById(R.id.editText);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall();
            }
        });
    }

    private void makeCall(){
        String phoneNo = phoneField.getText().toString();
        if (!phoneNo.isEmpty()) {
            telPhoneNo = "tel:" + phoneNo;
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
                Toast.makeText(MainActivity.this, "makeCall.", Toast.LENGTH_LONG).show();
            }
            else {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(telPhoneNo)));
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "Enter a valid phone number", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall();// Call the number in the text field
                } else {
                    Toast.makeText(MainActivity.this, "You have denied us the ability to make calls from our app.", Toast.LENGTH_LONG).show();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    PhoneStateListener myPhoneStateListner = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch(state){
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(MainActivity.this, "CALL_STATE_OFFHOOK", Toast.LENGTH_LONG).show();
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    Toast.makeText(MainActivity.this, "CALL_STATE_IDLE", Toast.LENGTH_LONG).show();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Toast.makeText(MainActivity.this, "CALL_STATE_RINGING", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        myTelephonyManager.listen(myPhoneStateListner, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myTelephonyManager.listen(myPhoneStateListner, PhoneStateListener.LISTEN_NONE);
    }
}
