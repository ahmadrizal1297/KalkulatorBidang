package com.example.kalkulatorbidang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtn1, txtn2;
    Button btnpersegi, btnlingkaran, btnsegitiga;
    TextView txthasilkeliling, txthasilluas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtn1 = (EditText) findViewById(R.id.txtn1);
        txtn2 = (EditText) findViewById(R.id.txtn2);
        btnpersegi = findViewById(R.id.btnpersegi);
        btnlingkaran = findViewById(R.id.btnlingkaran);
        btnsegitiga = findViewById(R.id.btnsegitiga);
        txthasilkeliling = findViewById(R.id.txthasilkeliling);
        txthasilluas = findViewById(R.id.txthasilluas);

    }

    public void hitungPersegi(View v){
        String n1 = txtn1.getText().toString();
        double nilai1 = Double.parseDouble(n1);
        String n2 = txtn1.getText().toString();
        double nilai2 = Double.parseDouble(n2);

        double keliling = nilai1+nilai2;
        txthasilkeliling.setText("Keliling persegi pjg = "+keliling);


        double luas = nilai1*nilai2;
        txthasilkeliling.setText("Luas persegi pjg = "+luas);

    }

    public void hitungLingkaran(View v){
        String n1 = txtn1.getText().toString();
        double nilai1 = Double.parseDouble(n1);
        String n2 = txtn1.getText().toString();
        double nilai2 = Double.parseDouble(n2);

        double keliling = 3.14*(nilai1*2);
        txthasilkeliling.setText("Keliling lingkaran = "+keliling);


        double luas = 3.14*(nilai1*nilai1);
        txthasilkeliling.setText("Luas lingkaran = "+luas);

    }

    public void hitungSegitiga(View v){
        String n1 = txtn1.getText().toString();
        double nilai1 = Double.parseDouble(n1);
        String n2 = txtn1.getText().toString();
        double nilai2 = Double.parseDouble(n2);

        double keliling = nilai1+nilai2;
        txthasilkeliling.setText("Keliling segitiga = "+keliling);


        double luas = (nilai1*nilai2)/2;
        txthasilkeliling.setText("Luas segitiga = "+luas);

    }
}
