package com.example.wang.febox;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CourseTableActivity extends AppCompatActivity {

    LinearLayout weekPanels[]=new LinearLayout[7];
    List courseData[]=new ArrayList[7];//这是一个存放数组的list,相当于一个二维数组
    int itemHeight;
    int marTop,marLeft;
    SQLiteDatabase db;
    String DB_name="mDb.db";
    String Table_name="Courses";
    boolean changed=true;

    int week_col;
    int name_col;
    int room_col;
    int start_col;
    int step_col;
    int teacher_col;
    int id_col;



    private static final String Tag="course";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_class_table);
        //
        itemHeight=getResources().getDimensionPixelSize(R.dimen.weekItemHeight);
        marTop=getResources().getDimensionPixelSize(R.dimen.weekItemMarTop);
        marLeft=getResources().getDimensionPixelSize(R.dimen.weekItemMarLeft);

        //数据
        getData();



        for (int i = 0; i < weekPanels.length; i++) {
            weekPanels[i]=(LinearLayout) findViewById(R.id.weekPanel_1+i);
            initWeekPanel(weekPanels[i], courseData[i]);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //用于返回箭头的实现。
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.d(Tag,"about_activity finished");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(CourseTableActivity.this,CourseAddActivity.class);
                DataBaseHp Mdbhp=new DataBaseHp(DB_name,CourseTableActivity.this);
                db=Mdbhp.getDb();

                String Sql2="insert into Flag  values(1);";
                db.execSQL(Sql2);
                db.close();

                startActivity(intent);


            }
        });
    }
    public void getData(){
        /*List<Course>list1=new ArrayList<Course>();
        Course c1 =new Course("软件工程","A402", 1, 4, "典韦", 1002);
        list1.add(c1);
        list1.add(new Course("C语言", "A101", 6, 3, "甘宁", 1001));
        courseData[0]=list1;

        List<Course>list2=new ArrayList<Course>();
        list2.add(new Course("计算机组成原理", "A106", 6, 3, "马超", 1001));
        list2.add(new Course("hehe","基教501",9,2,"lilei",2015));
        courseData[1]=list2;

        List<Course>list3=new ArrayList<Course>();
        list3.add(new Course("数据库原理", "A105", 2, 3, "孙权", 1008));
        list3.add(new Course("计算机网络", "A405", 6, 2, "司马懿", 1009));
        list3.add(new Course("电影赏析", "A112", 9, 2, "诸葛亮", 1039));
        courseData[2]=list3;

        List<Course>list4=new ArrayList<Course>();
        list4.add(new Course("数据结构", "A223", 1, 3, "刘备", 1012));
        list4.add(new Course("操作系统", "A405", 6, 3, "曹操", 1014));
        courseData[3]=list4;

        List<Course>list5=new ArrayList<Course>();
        list5.add(new Course("Android开发","C120",1,4,"黄盖",1250));
        list5.add(new Course("游戏设计原理","C120",8,4,"陆逊",1251));
        courseData[4]=list5;

        backup_data();*/
        restore_data();

    }


    void backup_data()//备份数据
    {
        DataBaseHp Mdbhp=new DataBaseHp(DB_name,this);

        Mdbhp.createTable(Table_name);//不存在才新建,存在则刷新
        db=Mdbhp.getDb();

        ContentValues cValue=new ContentValues();
        for (int i = 0;courseData[i]!=null  && i < courseData.length; i++)
        {                                       //??????????????????????????????   直接写courseData[i].get(j)怎么不行？
            List<Course> data=courseData[i];

            for(int j=0;j<data.size();j++)
            {
                Course c =data.get(j);
                cValue.put("week",i+1);
                cValue.put("name",c.getName());
                cValue.put("room",c.getRoom());
                cValue.put("start",c.getStart());
                cValue.put("step",c.getStep());
                cValue.put("teacher",c.getTeach());
                cValue.put("id",c.getId());
                db.insert(Table_name,null,cValue);
                cValue.clear();
            }

        }
        db.close();

    }
    void restore_data()//恢复数据
    {
        DataBaseHp Mdbhp=new DataBaseHp(DB_name,this);
        db=Mdbhp.getDb();
        String col[]={"week","name","room","start","step","teacher","id"};
        String course_table="create table  if not exists  "+ Table_name+"(_id integer primary key autoincrement,week tinyint,name varchar,room varchar,start tinyint,step tinyint,teacher varchar ,id Smallint)";
        db.execSQL(course_table);
        Cursor cursor=db.query(Table_name,col,null,null,null,null,"week");

         week_col=cursor.getColumnIndex("week");
         name_col=cursor.getColumnIndex("name");
         room_col=cursor.getColumnIndex("room");
         start_col=cursor.getColumnIndex("start");
         step_col=cursor.getColumnIndex("step");
         teacher_col=cursor.getColumnIndex("teacher");
         id_col=cursor.getColumnIndex("id");


        List<Course>list1=new ArrayList<Course>();
        List<Course>list2=new ArrayList<Course>();
        List<Course>list3=new ArrayList<Course>();
        List<Course>list4=new ArrayList<Course>();
        List<Course>list5=new ArrayList<Course>();



        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            switch(cursor.getInt(week_col))
            {
                case 1:list1.add(new Course(cursor.getString(name_col),cursor.getString(room_col),cursor.getInt(start_col),
                        cursor.getInt(step_col),cursor.getString(teacher_col),cursor.getInt(id_col)));break;
                case 2:list2.add(new Course(cursor.getString(name_col),cursor.getString(room_col),cursor.getInt(start_col),
                        cursor.getInt(step_col),cursor.getString(teacher_col),cursor.getInt(id_col)));break;
                case 3:list3.add(new Course(cursor.getString(name_col),cursor.getString(room_col),cursor.getInt(start_col),
                        cursor.getInt(step_col),cursor.getString(teacher_col),cursor.getInt(id_col)));break;
                case 4:list4.add(new Course(cursor.getString(name_col),cursor.getString(room_col),cursor.getInt(start_col),
                        cursor.getInt(step_col),cursor.getString(teacher_col),cursor.getInt(id_col)));break;
                case 5:list5.add(new Course(cursor.getString(name_col),cursor.getString(room_col),cursor.getInt(start_col),
                        cursor.getInt(step_col),cursor.getString(teacher_col),cursor.getInt(id_col)));break;
            }
        }

        courseData[0]=list1;
        courseData[1]=list2;
        courseData[2]=list3;
        courseData[3]=list4;
        courseData[4]=list5;

        db.close();

    }


    public void initWeekPanel(LinearLayout ll,List<Course> data){
        if(ll==null || data==null || data.size()<1)return;
        Log.i("Msg", "初始化面板");
        Course pre=data.get(0);
        for (int i = 0; i < data.size(); i++) {

            Course c =data.get(i);
            TextView tv =new TextView(this);
            LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,//宽
                    itemHeight*c.getStep()+marTop*(c.getStep()-1));
            if(i>0){
                lp.setMargins(marLeft, (c.getStart()-(pre.getStart()+pre.getStep()))*(itemHeight+marTop)+marTop, 0, 0);
            }else{
                lp.setMargins(marLeft, (c.getStart()-1)*(itemHeight+marTop)+marTop, 0, 0);
            }
            tv.setLayoutParams(lp);
            tv.setGravity(Gravity.TOP);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setTextSize(12);
            tv.setTextColor(getResources().getColor(R.color.courseTextColor));
            tv.setText(c.getName()+" @"+c.getRoom()+" by"+c.getTeach());
            tv.setBackgroundColor(getResources().getColor(R.color.actionBarBg));
            // tv.setBackground(getResources().getDrawable(R.drawable.tvshape));
            ll.addView(tv);
            pre=c;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            DataBaseHp Mdbhp=new DataBaseHp(DB_name,this);
            Mdbhp.createTable(Table_name);
            db=Mdbhp.getDb();

            String Sql2="insert into Flag  values(1);";
            db.execSQL(Sql2);
            db.close();

            this.recreate();

        }
        if(id==R.id.action_update)
        {

        }

        if(id==R.id.action_export)
        {

        }

        if(id==R.id.action_import)
        {

        }


        return super.onOptionsItemSelected(item);
    }
    public void onResume()
    {


        super.onResume();


        String[] col={"flag"};

        DataBaseHp Mdbhp=new DataBaseHp(DB_name,this);
        db=Mdbhp.getDb();
        String Sql="create table if not exists Flag (flag tinyint);";
        db.execSQL(Sql);
        Cursor cursor=db.query("Flag",col,null,null,null,null,null);

        if(cursor.getCount()>=1) {
            db.execSQL(" delete from  Flag where flag='1' ");/**********唉，这里搞得我太心酸
             因为薄弱的基础知识，我只好想出这样一个效率并不高的方法，当float按钮
             被按下时，在数据库的Flag表中插入一个值，若在此处检测到这个值，重新启动activity以刷新屏幕*************************************/
            db.close();

            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();

            overridePendingTransition(0, 0);
            startActivity(intent);
        }

       // CourseTableActivity.this.recreate();
        /*Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);*/


    }

    @Override
    public void onStop()
    {
        super.onStop();
        DataBaseHp Mdbhp=new DataBaseHp(DB_name,CourseTableActivity.this);
        db=Mdbhp.getDb();

        String Sql2="insert into Flag  values(1);";
        db.execSQL(Sql2);
        db.close();
    }

}
