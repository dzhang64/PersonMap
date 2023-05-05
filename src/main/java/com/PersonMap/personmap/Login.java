package com.PersonMap.personmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.PersonMap.personmap.bean.User;
import com.PersonMap.personmap.sql.MyDatabaseHelper;

public class Login extends AppCompatActivity {

    Button login_but,register_but;
    TextView touchScreen;
    EditText Account_2,pwd;
    MyDatabaseHelper myDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_but = findViewById(R.id.login);
        register_but = findViewById(R.id.register);
        Account_2 = findViewById(R.id.UserName);
        pwd =findViewById(R.id.Pwd);
        myDatabaseHelper = new MyDatabaseHelper(this);

        //login
        login_but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String acc = Account_2.getText().toString().trim();
                String pwd1 =pwd.getText().toString().trim();
                User user = myDatabaseHelper.findUserByUserName(acc);
                if(user!=null) {
                    if (user.getPassword().equals(pwd1)) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Login.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Login.this, "The Account Isn't Exist!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //register
        register_but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //显示注册界面
                Intent intent = new Intent(Login.this, Register.class);
                //启动显示修改界面
                startActivity(intent);
                finish();
            }
        });

    }
}