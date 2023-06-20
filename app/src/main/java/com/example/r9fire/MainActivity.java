package com.example.r9fire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnDel;
    TextView tvResult;

    TextInputEditText etStudentId, etStudentName, etStudentSem, etStudentDelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Students")
                .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String text = "";
                                for(DataSnapshot student:snapshot.getChildren())
                                {
                                    text = text + student.child("sId").getValue().toString()
                                            +" : "+student.child("sName").getValue().toString()
                                            +" : "+student.child("sSem").getValue().toString()+"\n";
                                }
                                tvResult.setText(text);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etStudentId.getText().toString().trim();
                String name = etStudentName.getText().toString().trim();
                String sem = etStudentSem.getText().toString().trim();

                HashMap<String, Object> data = new HashMap<>();
                data.put("sId", id);
                data.put("sName",name);
                data.put("sSem", sem);

                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("Students")
                        .child(id)
                        .updateChildren(data)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity.this, "Student Added Successfully.", Toast.LENGTH_SHORT).show();
                                clearFields();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(MainActivity.this, ShowData.class));

//                String id = etStudentDelId.getText().toString().trim();
//                if(id.isEmpty())
//                {
//
//                }
//                else
//                {
//                    FirebaseDatabase
//                            .getInstance()
//                            .getReference()
//                            .child("Students")
//                            .child(id)
//                            .removeValue()
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }

            }
        });


    }

    private void clearFields() {
        etStudentSem.setText("");
        etStudentId.setText("");
        etStudentName.setText("");
    }

    private void init()
    {
        etStudentId = findViewById(R.id.etStudentId);
        etStudentName = findViewById(R.id.etStudentName);
        etStudentSem = findViewById(R.id.etStudentSem);
        etStudentDelId = findViewById(R.id.etStudentDelId);
        btnSave = findViewById(R.id.btnSave);
        btnDel = findViewById(R.id.btnDel);
        tvResult = findViewById(R.id.tvResult);
    }
}