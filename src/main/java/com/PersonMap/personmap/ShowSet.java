package com.PersonMap.personmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.PersonMap.personmap.sql.MyDatabaseHelper;

public class ShowSet extends AppCompatActivity {
    private Button save;
    private Button back;

    MyDatabaseHelper myDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_set);
        save =  findViewById(R.id.save);
        back = findViewById(R.id.back);
        myDatabaseHelper = new MyDatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                EditText nameW =  findViewById(R.id.name);
                EditText sizeW =  findViewById(R.id.size);
                EditText makeW =  findViewById(R.id.makerId);
                RadioButton colorW = findViewById(R.id.radioButton);
                String name =  nameW.getText().toString().trim();
                String color = "00FF00";
                int size = Integer.parseInt(sizeW.getText().toString().trim());
                int markId = Integer.parseInt(makeW.getText().toString().trim());
                if(colorW.isChecked()){
                    color = "FF0000";
                }
                if(myDatabaseHelper.findShowTypeByName(name)!=null){
                    Toast.makeText(ShowSet.this, "This Name has be used!", Toast.LENGTH_SHORT).show();
                    return;
                }
                myDatabaseHelper.insertShowType(name,size,color,markId);
                Toast.makeText(ShowSet.this, "Save ShowType Success!", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowSet.this, "Back to Control Screen!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowSet.this, Navigator.class);
                startActivity(intent);
                finish();
            }
        });


    }
}