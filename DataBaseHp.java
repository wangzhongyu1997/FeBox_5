package com.example.wang.febox;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashSet;

/**
 * Created by wang on 16-11-5.
 */

public class DataBaseHp {
    String DBname;
    SQLiteDatabase db;

  //  private HashSet list_of_Tables=new HashSet();
    DataBaseHp(String DbName,Context context)
    {
        this.DBname=DbName;
        String path=context.getFilesDir().getPath();
        db=SQLiteDatabase.openOrCreateDatabase(path+"/"+DBname,null);

    }
    public void createTable(String table_name){

        //创建表SQL语句

        String sql ="drop table  if exists  "+table_name+"; "+ "DELETE FROM  if exists sqlite_sequence ;";
        String course_table="create table  if not exists  "+ table_name+"(_id integer primary key autoincrement,week tinyint,name varchar,room varchar,start tinyint,step tinyint,teacher varchar ,id Smallint)";
        db.execSQL(sql);
        db.execSQL(course_table);
    }

    public  void  delete(String Table)
    {
        String SqlDel=" Drop table "+Table;
        db.execSQL(Table);
    }
    public SQLiteDatabase getDb()
    {
        return db;
    }
}

