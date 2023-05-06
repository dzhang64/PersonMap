package com.PersonMap.personmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.PersonMap.personmap.bean.Point;
import com.PersonMap.personmap.bean.ShowType;
import com.PersonMap.personmap.sql.MyDatabaseHelper;
import com.PersonMap.personmap.tools.ExcelTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class PointSet extends AppCompatActivity {

    private List<ShowType> allShowType;

    private String[] nameArray;

    private HashMap<String,Integer> nameMap;

    private int selectShowTypeId;

    private Button select;

    private String filePathStr;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_set);
        select = findViewById(R.id.selectButton);
        myDatabaseHelper = new MyDatabaseHelper(this);
        allShowType = myDatabaseHelper.findAllShowType();
        if(allShowType.size()>0){
            nameMap = new HashMap<String,Integer>();
            nameArray = new String[allShowType.size()];
            for (int i = 0; i < allShowType.size(); i++) {
                ShowType showType = allShowType.get(i);
                String name = showType.getName();
                Integer id = showType.getId();
                nameMap.put(name,id);
                nameArray[i] = name;
            }
            initSpinnerForDropdown(nameArray);
        }

        select.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.filePath);
                filePathStr = "/sdcard/Download/1.xls";
                tv.setText(filePathStr);
            }
        });

        Button save = findViewById(R.id.save);
        Button back = findViewById(R.id.back);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                EditText categoryW = findViewById(R.id.category);
                TextView tv = findViewById(R.id.filePath);
                String category = categoryW.getText().toString().trim();
                myDatabaseHelper.insertPoint(category,"A","A",120.664445,32.599723);
                myDatabaseHelper.insertPoint(category,"B","B",120.6654	,32.5102);
                myDatabaseHelper.insertPoint(category,"C","C",120.6667,32.599723);
                myDatabaseHelper.insertPoint(category,"D","D",120.6681,32.6234);
                myDatabaseHelper.insertCategoryShowTypeRelationship(category,selectShowTypeId);
            }
        });

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(PointSet.this, "Back to Control Screen!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PointSet.this, Navigator.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void initSpinnerForDropdown(String[] showTypeArray) {
        // 声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item, showTypeArray);
        // 从布局文件中获取名叫sp_dropdown的下拉框
        Spinner sp_dropdown = findViewById(R.id.show_type);
        // 设置下拉框的标题。对话框模式才显示标题，下拉模式不显示标题
        sp_dropdown.setAdapter(starAdapter); // 设置下拉框的数组适配器
        sp_dropdown.setSelection(0); // 设置下拉框默认显示第一项
        // 给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        sp_dropdown.setOnItemSelectedListener(new MySelectedListener());
    }

    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            String name = nameArray[arg2];
            selectShowTypeId = nameMap.get(name);
        }
        // 未选择时的处理方法，通常无需关注
        public void onNothingSelected(AdapterView<?> arg0) {}
    }







}