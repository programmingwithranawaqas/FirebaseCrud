package com.example.r9fire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowData extends AppCompatActivity {

    RecyclerView rvlist;
    StudentAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        rvlist = findViewById(R.id.rvList);
        rvlist.setHasFixedSize(true);
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Student> options
                = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Students"), Student.class)
                .build();
        sAdapter = new StudentAdapter(options);
        rvlist.setAdapter(sAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sAdapter.stopListening();
    }
}