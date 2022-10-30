package com.example.sample2;

public class SearchTableData {
    public String stud_roll;
    private String stud_batch;
    private String stud_year;

    public String getStud_roll() {
        return stud_roll;
    }

    public void setStud_roll(String stud_roll) {
        this.stud_roll = stud_roll;
    }

    public String getStud_batch() {
        return stud_batch;
    }

    public void setStud_batch(String stud_batch) {
        this.stud_batch = stud_batch;
    }

    public String getStud_year() {
        return stud_year;
    }

    public void setStud_year(String stud_year) {
        this.stud_year = stud_year;
    }

    public String getStud_sem() {
        return stud_sem;
    }

    public void setStud_sem(String stud_sem) {
        this.stud_sem = stud_sem;
    }

    public String getStud_dept() {
        return stud_dept;
    }

    public void setStud_dept(String stud_dept) {
        this.stud_dept = stud_dept;
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }

    private String stud_sem;

    public SearchTableData(String stud_roll, String stud_batch, String stud_year, String stud_sem, String stud_dept, String stud_id) {
        this.stud_roll = stud_roll;
        this.stud_batch = stud_batch;
        this.stud_year = stud_year;
        this.stud_sem = stud_sem;
        this.stud_dept = stud_dept;
        this.stud_id = stud_id;
    }

    private String stud_dept;
    private String stud_id;
}
