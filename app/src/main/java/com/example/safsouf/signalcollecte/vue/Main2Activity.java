package com.example.safsouf.signalcollecte.vue;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.Selection;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.safsouf.signalcollecte.R;
import com.example.safsouf.signalcollecte.modele.ConnectionDetector;
import com.example.safsouf.signalcollecte.modele.Debit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    TelephonyManager mTelephonyManager;
    MyPhoneStateListener mPhoneStatelistener;
    String mSignalStrength = "";
    TextView txt_etat;
    EditText txt_Name_Device;
    ConnectionDetector cd;
    EditText rsrp;
    EditText rsrq;
    EditText rssi;
    EditText cqi;
    EditText SS;
    EditText dn;
    EditText dm;
    EditText dv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt_etat = (TextView) findViewById(R.id.etat);
        rsrp = (EditText) findViewById(R.id.rsrp);
        rsrq = (EditText) findViewById(R.id.rsrq);
        rssi = (EditText) findViewById(R.id.rssi);
        cqi = (EditText) findViewById(R.id.cqi);
        SS = (EditText) findViewById(R.id.ss);
        dn = (EditText) findViewById(R.id.dn);
        dm = (EditText) findViewById(R.id.dm);
        dv = (EditText) findViewById(R.id.dv);

        PhoneStateListener myPhoneStateListener;
        TelephonyManager tm;

        /*cd= new ConnectionDetector( this );
           if(cd.isConnected()){
            txt_etat.setText("Conected");
        }else {txt_etat.setText("Not Conected");}*/

        mPhoneStatelistener = new MyPhoneStateListener();
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyManager.listen(mPhoneStatelistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        //Device name
        String manufacturer = Build.MANUFACTURER;
        dn.setText(String.valueOf(manufacturer));
        Log.d("name----->", String.valueOf(manufacturer));
        //Device model
        String model = Build.MODEL;
        dm.setText(String.valueOf(model));
        Log.d("model----->", String.valueOf(model));

        // Device version
        String version = Build.VERSION.RELEASE;
        dv.setText("Android");
        dv.setText(String.valueOf(version));
        Log.d("version----->", String.valueOf( version));
        }

    class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);

            mSignalStrength = signalStrength.toString();
            String[] parts = mSignalStrength.split(" ");

            int signalStrengthDbm = 0;
            int rsrpLTE = 0;
            int rsrqLTE = 0;
            int rssnrLTE = 0;
            int cqiLTE = 0;

            if (mTelephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE) {
                txt_etat.setText("Conected to LTE");
            } else {
                txt_etat.setText("Not conected to LTE");
            }

            // For Lte SignalStrength: dbm = ASU - 140.
            //Log.d("signalStrength----->", String.valueOf(mSignalStrength));
            //SS.setText(String.valueOf(mSignalStrength));

            //LteSignalStrength PART 8
            signalStrengthDbm = Integer.parseInt(parts[8]) - 140;
            SS.setText(String.valueOf(signalStrengthDbm));

            //RSRP PART 9
            rsrpLTE = Integer.parseInt(parts[9]) - 140;
            rsrp.setText(String.valueOf(rsrpLTE));

            //RSRQPART 10
            rsrqLTE = Integer.parseInt(parts[10]) - 140;
            rsrq.setText(String.valueOf(rsrqLTE));

            //RSSNR PART 11
            rssnrLTE = Integer.parseInt(parts[11]) - 140;
            rssi.setText(String.valueOf(rssnrLTE));

            //CQI  PART 12
            cqiLTE = Integer.parseInt(parts[12]) - 140;
            cqi.setText(String.valueOf(cqiLTE));
        }
    }}





