package com.example.sample2;

public class StudentData2 {
    public String stud_roll;
    private String stud_batch;
    private String stud_year;
    private String stud_sem;
    private String stud_dept;
    private String stud_id;
    public StudentData2(String stud_id, String stud_roll, String stud_batch, String stud_year, String stud_sem, String stud_dept) {
        this.stud_roll = stud_roll;
        this.stud_batch= stud_batch;
        this.stud_dept= stud_dept;
        this.stud_sem= stud_sem;
        this.stud_id= stud_id;
        this.stud_year= stud_year;



    }

    public StudentData2(String course_id, String lesson_id, String date, String roll_no) {
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        stud_id = stud_id;
    }


    public String getStud_roll() {
        return stud_roll;
    }

    public void setStud_roll(String stud_roll) {
        stud_roll = stud_roll;
    }

    public String getStud_batch() {
        return stud_batch;
    }

    public void setStud_batch(String stud_batch) {stud_batch = stud_batch;
    }

    public String getStud_year() {
        return stud_year;
    }

    public void setStud_year(String stud_year) {
        stud_year = stud_year;
    }

    public String getStud_sem() {
        return stud_sem;
    }

    public void setStud_sem(String stud_sem) {
        stud_sem = stud_sem;
    }

    public String getStud_dept() {
        return stud_dept;
    }

    public void setStud_dept(String stud_dept) {
        stud_dept = stud_dept;
    }


}
