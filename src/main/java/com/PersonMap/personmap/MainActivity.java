package com.PersonMap.personmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.PersonMap.personmap.bean.User;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {

    private Button show; // data show
    private Button hide; // data hide
    private Button control; // data control
    private Button exit; // exit label

    private static final int SHOW = 1; // 显示
    private static final int HIDE = 2; // 关闭
    private static final int LOAD = 3; // 加载数据

    private MapView mMapView = null;

    private BaiduMap mBaiduMap =null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();


        //get label
        show = findViewById(R.id.data_show);
        hide = findViewById(R.id.data_hide);
        control = findViewById(R.id.data_control);
        exit = findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.exit(0);
            }
        });

        control.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "Go to Control!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Navigator.class);
                startActivity(intent);
            }
        });

        show.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //定义Maker坐标点
                LatLng point1 = new LatLng(39.963175, 116.400244);
                LatLng point2 = new LatLng(39.96455, 116.400244);
                LatLng point3 = new LatLng(39.96475, 116.40144);
                LatLng point4 = new LatLng(39.96567, 116.40234);
                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.p1);
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option1 = new MarkerOptions()
                        .position(point1)
                        .icon(bitmap);
                OverlayOptions option2 = new MarkerOptions()
                        .position(point2)
                        .icon(bitmap);
                OverlayOptions option3 = new MarkerOptions()
                        .position(point3)
                        .icon(bitmap);
                OverlayOptions option4 = new MarkerOptions()
                        .position(point4)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                mBaiduMap.addOverlay(option1);
                mBaiduMap.addOverlay(option2);
                mBaiduMap.addOverlay(option3);
                mBaiduMap.addOverlay(option4);

                //设置位置
                MapStatusUpdate status1 = MapStatusUpdateFactory.newLatLng(point4);
                mBaiduMap.setMapStatus(status1);
            }
        });

        hide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mBaiduMap.clear();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }




}