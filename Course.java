package com.example.wang.febox;

/**
 * Created by wang on 16-11-2.
 */
public class Course {
    private String name,room,teach;
    int id;//课程名称、上课教室，教师，课程编号
    int start,step; //开始上课节次， 一共几节课
    public Course(String name, String room, int start, int step,
                  String teach, int id) {
        super();
        this.name = name;
        this.room = room;
        this.start = start;
        this.step = step;
        this.teach = teach;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getTeach() {
        return teach;
    }

    public void setTeach(String teach) {
        this.teach = teach;
    }


}