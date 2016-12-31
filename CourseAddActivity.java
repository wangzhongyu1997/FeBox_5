package com.example.wang.febox;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CourseAddActivity extends AppCompatActivity {

    String DB_name="mDb.db";
    String Table_name="Courses";
    SQLiteDatabase db;
    DataBaseHp Mdbhp;
    int course_start;
    int course_end;
    int position_globle=0;
    boolean finish=false;
    private ContentValues cValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("添加课程");

        ArrayAdapter<String> adapter;
        String m[]={"周一","周二","周三","周四","周五","周六","周日"};


        cValue=new ContentValues();


        /**************************************设置星期********************************************/
        final Spinner spinnerView1 = (Spinner) findViewById(R.id.spinner1);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, m);
        adapter.setDropDownViewResource(android.
                R.layout.simple_spinner_dropdown_item);
        spinnerView1.setAdapter(adapter);
        spinnerView1.setSelection(0, true);//默认选中第一个

        spinnerView1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        String selected=(String) spinnerView1.getItemAtPosition(position);

                        switch (selected)
                        {
                            case "周一":cValue.put("week",1);break;
                            case "周二":cValue.put("week",2);break;
                            case "周三":cValue.put("week",3);break;
                            case "周四":cValue.put("week",4);break;
                            case "周五":cValue.put("week",5);break;
                            case "周六":cValue.put("week",6);break;
                            case "周日":cValue.put("week",7);break;
                        }

                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                        cValue.put("week",1);

                    }
                });

/************************************设置课 start ********************************************/
        final Spinner spinnerView2 = (Spinner) findViewById(R.id.spinner2);
        String courses[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, courses);
        adapter2.setDropDownViewResource(android.
                R.layout.simple_spinner_dropdown_item);
        spinnerView2.setAdapter(adapter2);
           spinnerView2.setSelection(0, true);//默认选中第一个

        spinnerView2.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        String selected=(String) spinnerView2.getItemAtPosition(position);
                        position_globle=position;//缓存选中位置
                        switch (selected)
                        {
                            case "1":course_start=1;break;
                            case "2":course_start=2;break;
                            case "3":course_start=3;break;
                            case "4":course_start=4;break;
                            case "5":course_start=5;break;
                            case "6":course_start=6;break;
                            case "7":course_start=7;break;
                            case "8":course_start=8;break;
                            case "9":course_start=9;break;
                            case "10":course_start=10;break;
                            case "11":course_start=11;break;
                            case "12":course_start=12;break;
                        }
                        cValue.put("start",course_start);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                    //cValue.put();不能写在这儿？为啥？
                });

        /************************************设置课 end ******************************************/
        final Spinner spinnerView3 = (Spinner) findViewById(R.id.spinner3);
      //  String courses[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, courses);
        adapter3.setDropDownViewResource(android.
                R.layout.simple_spinner_dropdown_item);
        spinnerView3.setAdapter(adapter3);
        spinnerView3.setSelection(position_globle+1, true);//默认选中上节课的下一节

        spinnerView3.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {

                        String selected=(String) spinnerView3.getItemAtPosition(position);

                        switch (selected)
                        {
                            case "1":course_end=1;break;
                            case "2":course_end=2;break;
                            case "3":course_end=3;break;
                            case "4":course_end=4;break;
                            case "5":course_end=5;break;
                            case "6":course_end=6;break;
                            case "7":course_end=7;break;
                            case "8":course_end=8;break;
                            case "9":course_end=9;break;
                            case "10":course_end=10;break;
                            case "11":course_end=11;break;
                            case "12":course_end=12;break;
                        }
                        if(course_end>=course_start)
                            cValue.put("step",course_end-course_start+1);
                        finish=true;
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                      //  cValue.put("start",1);
                    }
                    //cValue.put();不能写在这儿？为啥？
                });







        Button buttonOk=(Button)findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText edit1=(EditText)findViewById(R.id.course_name);

                        Log.i("获取的值course_name", edit1.getText().toString());
                        cValue.put("name",edit1.getText().toString());

                        EditText edit2=(EditText)findViewById(R.id.address);
                        cValue.put("room",edit2.getText().toString());

                        EditText edit3=(EditText)findViewById(R.id.teacher_name);
                        cValue.put("teacher",edit3.getText().toString());

                        EditText edit4=(EditText)findViewById(R.id.editText);
                        cValue.put("id",edit4.getText().toString());


                        Mdbhp=new DataBaseHp(DB_name,CourseAddActivity.this);
                        db=Mdbhp.getDb();
                        db.insert(Table_name,null,cValue);
                        db.close();
                        finish();

                    }
                }

        );

        Button buttonCancel= (Button) findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

    }

}
