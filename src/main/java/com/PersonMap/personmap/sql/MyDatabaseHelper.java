package com.PersonMap.personmap.sql;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.PersonMap.personmap.bean.Point;
import com.PersonMap.personmap.bean.ShowType;
import com.PersonMap.personmap.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASENAME = "PersonalMap.db" ;	// db name
    private static final int DATABASEVERSION = 1 ;
    private static final String TABLE_USERNAME = "User" ;	// User Table

    private static final String Table_Point = "Point"; // Point Table

    private static final String Table_Show = "ShowType" ; // ShowType Table

    private static final String Table_Category_Show = "Category_Show" ; // ShowType Table

    public MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create user table
        String sqlString="create table "+ TABLE_USERNAME+"( id integer primary key autoincrement,Account varchar(255),Password varchar(255))";
        db.execSQL(sqlString);
        sqlString="create table "+ Table_Point+"( id integer primary key autoincrement,Category varchar(255),Name varchar(255),Tips varchar(255),Longitude real,Latitude real)";
        db.execSQL(sqlString);
        sqlString="create table "+ Table_Show+"( id integer primary key autoincrement,Name varchar(255),Size integer,ColorHex VARCHAR(6),MarkID integer)";
        db.execSQL(sqlString);
        sqlString="create table "+ Table_Category_Show+"( id integer primary key autoincrement,Category varchar(255),ShowID integer)";
        db.execSQL(sqlString);

    }

    //insert user
    public  void insertUser(String account,String password)
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="insert into "+TABLE_USERNAME+"(Account,Password) values (?,?)";
        Object args[]=new Object[]{account,password};
        db.execSQL(sqlString,args);
        db.close();
    }

    //insert Point
    public  void insertPoint(String category,String name,String tips,Double longitude, Double latitude)
    {
        SQLiteDatabase db= super.getWritableDatabase();
        String sqlString="insert into "+Table_Point+"(Category,Name,Tips,Longitude,Latitude) values (?,?,?,?,?)";
        Object args[]=new Object[]{category,name,tips,longitude,latitude};
        db.execSQL(sqlString,args);
        db.close();
    }

    //insert Show type
    public  void insertShowType(String name,Integer size,String colorHex,Integer markID)
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="insert into "+TABLE_USERNAME+"(Name,Size,ColorHex,MarkID) values (?,?,?,?)";
        Object args[]=new Object[]{name,size,colorHex,markID};
        db.execSQL(sqlString,args);
        db.close();
    }

    //insert Category and ShowType Relationship
    public  void insertCategoryShowTypeRelationship(String category,Integer showID)
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="insert into "+Table_Category_Show+"(Category,ShowID) values (?,?)";
        Object args[]=new Object[]{category,showID};
        db.execSQL(sqlString,args);
        db.close();
    }

    //find all user
    public List<User> findAllUser()
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from "+TABLE_USERNAME;
        Cursor result =db.rawQuery(sqlString,null);
        List<User> userList = new ArrayList<>();
        for (result.moveToFirst();!result.isAfterLast();result.moveToNext())
        {
            //判断数据库是否存在此对象
            int id = result.getInt(0);
            String account = result.getString(1);
            String password = result.getString(2);
            User user = new User(id,account,password);
            userList.add(user);
        }
        //关闭游标
        result.close();
        return userList;
    }

    //find User by name
    public User findUserPasswordByUserName(String name)
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from "+TABLE_USERNAME+" where Account = " + name;
        Cursor result =db.rawQuery(sqlString,null);
        User user = null;
        if(result.getCount() != 0){
            int id = result.getInt(0);
            String account = result.getString(1);
            String password = result.getString(2);
            user = new User(id,account,password);
        }
        //关闭游标
        result.close();
        return user;
    }

    //find all point
    public List<Point> findAllPoint()
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from "+Table_Point;
        Cursor result =db.rawQuery(sqlString,null);
        List<Point> pointList = new ArrayList<>();
        for (result.moveToFirst();!result.isAfterLast();result.moveToNext())
        {
            //判断数据库是否存在此对象
            int id = result.getInt(0);
            String category = result.getString(1);
            String name = result.getString(2);
            String tips = result.getString(3);
            double longitude = result.getDouble(4);
            double latitude = result.getDouble(5);
            Point point = new Point(id,category,name,tips,longitude,latitude);
            pointList.add(point);
        }
        //关闭游标
        result.close();
        return pointList;
    }

    //find  points by category
    public List<Point> findPointsByCategory(String ct)
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from "+Table_Point + "where Category = " + ct;
        Cursor result =db.rawQuery(sqlString,null);
        List<Point> pointList = new ArrayList<>();
        for (result.moveToFirst();!result.isAfterLast();result.moveToNext())
        {
            //判断数据库是否存在此对象
            int id = result.getInt(0);
            String category = result.getString(1);
            String name = result.getString(2);
            String tips = result.getString(3);
            double longitude = result.getDouble(4);
            double latitude = result.getDouble(5);
            Point point = new Point(id,category,name,tips,longitude,latitude);
            pointList.add(point);
        }
        //关闭游标
        result.close();
        return pointList;
    }

    //find  point by id
    public Point findPointsByCategory(int tid)
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from "+Table_Point + "where id = " + tid;
        Cursor result =db.rawQuery(sqlString,null);
        Point point = null;
        if(result.getCount() != 0){
            //判断数据库是否存在此对象
            int id = result.getInt(0);
            String category = result.getString(1);
            String name = result.getString(2);
            String tips = result.getString(3);
            double longitude = result.getDouble(4);
            double latitude = result.getDouble(5);
            point = new Point(id,category,name,tips,longitude,latitude);
        }
        //关闭游标
        result.close();
        return point;
    }

    //find all show type
    public List<ShowType> findAllShowType()
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from "+Table_Show;
        Cursor result =db.rawQuery(sqlString,null);
        List<ShowType> showList = new ArrayList<>();
        for (result.moveToFirst();!result.isAfterLast();result.moveToNext())
        {
            //判断数据库是否存在此对象
            int id = result.getInt(0);
            String name = result.getString(1);
            int size = result.getInt(2);
            String colorHex = result.getString(3);
            int markID = result.getInt(4);
            ShowType showType = new ShowType(id,name,size,colorHex,markID);
            showList.add(showType);
        }
        //关闭游标
        result.close();
        return showList;
    }

    //find ShowType by id
    public ShowType findShowType(int sid)
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from "+Table_Show+" where id = " + sid;
        Cursor result =db.rawQuery(sqlString,null);
        ShowType showType = null;
        if(result.getCount() != 0){
            int id = result.getInt(0);
            String name = result.getString(1);
            int size = result.getInt(2);
            String colorHex = result.getString(3);
            int markID = result.getInt(4);
            showType = new ShowType(id,name,size,colorHex,markID);
        }
        //关闭游标
        result.close();
        return showType;
    }

    //find ShowType ID by category,return -1 if no data
    public int findShowTypeByCategory(String category)
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select ShowID from "+Table_Category_Show+" where Category = " + category;
        Cursor result =db.rawQuery(sqlString,null);
        ShowType showType = null;
        int id = -1;
        if(result.getCount() != 0){
            id = result.getInt(0);
        }
        //关闭游标
        result.close();
        return id;
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}