package com.PersonMap.personmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.PersonMap.personmap.sql.MyDatabaseHelper;

public class Register extends AppCompatActivity {

    Button register_lj,back;
    TextView touchScreen;
    EditText Account,Pwd1,Pwd2;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDatabaseHelper = new MyDatabaseHelper(this);
        //按钮组件
        register_lj =findViewById(R.id.register_but);
        back =findViewById(R.id.back);
        //编辑框
        Account = findViewById(R.id.register_name);
        Pwd1 = findViewById(R.id.register_pwd);
        Pwd2 = findViewById(R.id.register_pwd2);

        register_lj.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //获取输入的用户名和密码
                String name = Account.getText().toString().trim();
                String password = Pwd1.getText().toString().trim();
                String password1 = Pwd2.getText().toString().trim();
                if (name.equals("")){
                    Toast.makeText(Register.this, "User name cannot be blank!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(password1)){
                    Toast.makeText(Register.this, "Entered passwords differ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Register.this, "Password setting cannot be less than 6 digits!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(myDatabaseHelper.findUserByUserName(name)!=null){
                    Toast.makeText(Register.this, "User Exist!", Toast.LENGTH_SHORT).show();
                    return;
                }

                myDatabaseHelper.insertUser(name,password);
                Toast.makeText(Register.this, "Register Success,Return to Login!", Toast.LENGTH_SHORT).show();
                //显示注册界面
                Intent intent = new Intent(Register.this, Login.class);
                //启动显示修改界面
                startActivity(intent);
                finish();
            }
        });

        //返回
        back.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}