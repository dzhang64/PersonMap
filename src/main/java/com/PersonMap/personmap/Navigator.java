package com.PersonMap.personmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Navigator extends AppCompatActivity {

    private Button showSet;
    private Button pointSet;
    private Button passwordSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nagigator);
        showSet = findViewById(R.id.button1);
        pointSet = findViewById(R.id.button2);
        passwordSet = findViewById(R.id.button3);
        showSet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(Navigator.this, "Go To ShowSet!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Navigator.this, ShowSet.class);
                startActivity(intent);
            }
        });

        pointSet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(Navigator.this, "Go To Point Set!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Navigator.this, PointSet.class);
                startActivity(intent);
            }
        });


    }
}