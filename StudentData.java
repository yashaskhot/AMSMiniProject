package com.example.sample2;
import java.sql.DriverManager;


public class StudentData {
     String stud_id;
     String stud_name;
    public StudentData(String stud_id,String stud_name){
        this.stud_id = stud_id;
        this.stud_name = stud_name;
    }

    public String getStud_id() {
        return stud_id;
    }
    public void setStud_id(String stud_id) {
        stud_id = stud_id;
    }

    public String getStud_name() {return stud_name; }
            public void setStud_name(String stud_name) {
                stud_name = stud_name;
            }

}




