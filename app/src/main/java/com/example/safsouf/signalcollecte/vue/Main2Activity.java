package com.example.safsouf.signalcollecte.vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safsouf.signalcollecte.R;
import com.example.safsouf.signalcollecte.modele.ConnectionDetector;
import com.example.safsouf.signalcollecte.modele.Debit;

public class Main2Activity extends AppCompatActivity {

    EditText txt_etat;
EditText txt_Name_Device;
int speedMbps;
    ConnectionDetector cd;
    Debit db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt_etat=(EditText)findViewById( R.id.txt_etat );

        cd= new ConnectionDetector( this );

        if(cd.isConnected()){
            txt_etat.setText("Conected");
        }else {txt_etat.setText("Not Conected");}







    }
}
