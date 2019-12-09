package com.example.kalkulatorbidang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private static final String TAG = "MainActivity";

    RecyclerviewAdapter adapter;

    EditText Input1, Input2;
    Button BtnHtg;
    RadioGroup Group;
    android.widget.RadioButton RadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> HasilHitungan = new ArrayList<>();
        final RecyclerView recyclerView = findViewById(R.id.MyRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        Input1 = findViewById(R.id.Input1);
        Input2 = findViewById(R.id.Input2);
        BtnHtg = findViewById(R.id.BtnHtg);
        Group = findViewById(R.id.radioGroup);

        BtnHtg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double Result;
                String Operand;
                int RadioId = Group.getCheckedRadioButtonId();
                RadioButton = findViewById(RadioId);
                Toast.makeText(MainActivity.this,RadioButton.getText(),Toast.LENGTH_SHORT).show();

                if(RadioButton.getText().equals("Bagi")){
                    Result = Double.parseDouble(Input1.getText().toString()) / Double.parseDouble(Input2.getText().toString());
                    Operand = "+";
                } else if (RadioButton.getText().equals("Kurang")) {
                    Result = Double.parseDouble(Input1.getText().toString()) - Double.parseDouble(Input2.getText().toString());
                    Operand = "-";
                } else if (RadioButton.getText().equals("Kali")) {
                    Result = Double.parseDouble(Input1.getText().toString()) * Double.parseDouble(Input2.getText().toString());
                    Operand = "*";
                } else {
                    Result = Double.parseDouble(Input1.getText().toString()) + Double.parseDouble(Input2.getText().toString());
                    Operand = "/";
                }


                //Log.d(TAG,"IKI DEBUG JAMAN KAWAK");
                // Create a new user with a first and last name
                Map<String, Object> hitung = new HashMap<>();
                hitung.put("Var1", Input1.getText().toString());
                hitung.put("Var2", Input2.getText().toString());
                hitung.put("Opr", Operand);
                hitung.put("Result", Result.toString());
                // Add a new document with a generated ID
                db.collection("hitungan")
                        .add(hitung)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                // Lihat Data (setelah sukses push data)
                                db.collection("hitungan")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                                        HasilHitungan.add(document.getData().get("Var1").toString()
                                                                + document.getData().get("Opr").toString()
                                                                + document.getData().get("Var2").toString()
                                                                + "="
                                                                + document.getData().get("Result").toString());
                                                    }
                                                    adapter = new RecyclerviewAdapter(MainActivity.this, HasilHitungan);
                                                    recyclerView.setAdapter(adapter);
                                                } else {
                                                    Log.w(TAG, "Error getting documents.", task.getException());
                                                }
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

            }
        });

    }

    public void TambahData(View v){

    }
}
