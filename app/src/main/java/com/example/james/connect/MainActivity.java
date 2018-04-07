package com.example.james.connect;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
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

    private SQLiteDatabase contactsDatabase;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 3;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 4;
    String telPhoneNo;
    EditText dialNumberField;
    private TelephonyManager myTelephonyManager;
    EditText permittedCountryField;
    ContactManagementDBHelper dbHelper;
    String registeredNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new ContactManagementDBHelper(this);
        contactsDatabase = dbHelper.getWritableDatabase();
        myTelephonyManager = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }
        /*registeredNumber = myTelephonyManager.getLine1Number();
        if(registeredNumber == null)
        {
            Toast.makeText(this, "Please register a phone number", Toast.LENGTH_LONG);
        }else{
            addYourNumberToDB();
        }*/
        Button callNumber = (Button) findViewById(R.id.button);
        dialNumberField = (EditText) findViewById(R.id.editText);
        Button addPermittedCountryButton = (Button) findViewById(R.id.button3);
        permittedCountryField = (EditText) findViewById(R.id.PermittedCountry);

        callNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall();
            }
        });
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }

        addPermittedCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPermittedCountryToDB();
            }
        });
    }

    public boolean checkIsDataAlreadyInDB(String TableName, String dbfield, String fieldValue) {
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = contactsDatabase.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    private void addPermittedCountryToDB()
    {
        //if(!yourNumberField.getText().toString().trim().equals("") || yourNumberField != null)
        //{
        //if the user enters a new phone number for their personal phone number we drop all tables that
        //are relevant to the older number: user, blocked numbers, permitted countries.
        //if(DatabaseUtils.queryNumEntries(contactsDatabase, ContactManagementContract.User.TABLE_NAME) > 0) {
        //  dbHelper.onUpgrade(contactsDatabase, contactsDatabase.getVersion(), contactsDatabase.getVersion() + 1);
        //}
        //String number = yourNumberField.getText().toString();
        String country = permittedCountryField.getText().toString().trim();
        if(!checkIsDataAlreadyInDB(ContactManagementContract.PermittedCountries.TABLE_NAME, ContactManagementContract.PermittedCountries.COLUMN_COUNTRY_NAME, country)) {
            ContentValues cv = new ContentValues();
            cv.put(ContactManagementContract.PermittedCountries.COLUMN_COUNTRY_NAME, country);
            contactsDatabase.insert(ContactManagementContract.PermittedCountries.TABLE_NAME, null, cv);
            /*cv.clear();
            Cursor cursor = contactsDatabase.rawQuery("SELECT ID FROM " + ContactManagementContract.PermittedCountries.TABLE_NAME +
                    " WHERE " + ContactManagementContract.PermittedCountries.COLUMN_COUNTRY_NAME +
                    " = '" + country + "';", null);
            cursor.moveToFirst();
            //There should only ever be one row as no country can be entered twice
            int countryID = cursor.getInt(0);
            cv.put(ContactManagementContract.User_PermittedCountries.COLUMN_COUNTRY_ID, countryID);
            cursor = contactsDatabase.rawQuery("SELECT ID FROM " + ContactManagementContract.User.TABLE_NAME +
                    " WHERE " + ContactManagementContract.User.COLUMN_USER_NUMBER +
                    " = '" + registeredNumber + "';", null);
            cursor.moveToFirst();
            int userID = cursor.getInt(0);
            cv.put(ContactManagementContract.User_PermittedCountries.COLUMN_USER_ID, userID);
            contactsDatabase.insert(ContactManagementContract.User_PermittedCountries.TABLE_NAME, null, cv);*/
        }
        //}
    }

    private void addYourNumberToDB()
    {
        //if(!yourNumberField.getText().toString().trim().equals("") || yourNumberField != null)
        //{
            //if the user enters a new phone number for their personal phone number we drop all tables that
            //are relevant to the older number: user, blocked numbers, permitted countries.
            //if(DatabaseUtils.queryNumEntries(contactsDatabase, ContactManagementContract.User.TABLE_NAME) > 0) {
              //  dbHelper.onUpgrade(contactsDatabase, contactsDatabase.getVersion(), contactsDatabase.getVersion() + 1);
            //}
            //String number = yourNumberField.getText().toString();
        if(!checkIsDataAlreadyInDB(ContactManagementContract.User.TABLE_NAME, ContactManagementContract.User.COLUMN_USER_NUMBER, registeredNumber)) {
            ContentValues cv = new ContentValues();
            cv.put(ContactManagementContract.User.COLUMN_USER_NUMBER, registeredNumber);
            contactsDatabase.insert(ContactManagementContract.User.TABLE_NAME, null, cv);
        }
        //}
    }

    private void makeCall(){
        String phoneNo = dialNumberField.getText().toString();
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
                if (grantResults.length > 1
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this,"PERMISSION GRANTED", Toast.LENGTH_LONG).show();
                    //makeCall();// Call the number in the text field
                } else {
                    Toast.makeText(MainActivity.this, "You have denied us the ability to make calls from our app.", Toast.LENGTH_LONG).show();
                }
            }
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"PERMISSION GRANTED", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"NOOOO PERMISSION GRANTED", Toast.LENGTH_LONG).show();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
