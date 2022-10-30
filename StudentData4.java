package com.example.sample2;

import java.util.Date;

public class StudentData4 {
    public String stud_abs;
    public String absent_percentage;

    public String getStud_abs() {
        return stud_abs;
    }

    public void setStud_abs(String stud_abs) {
        stud_abs = stud_abs;
    }

    public String getAbsent_percentage() {
        return absent_percentage;
    }

    public void setAbsnet_percentage(String absnet_percentage) {
        absnet_percentage = absnet_percentage;
    }
    public StudentData4(String stud_abs, String absent_percentage) {
        this.stud_abs=stud_abs;
        this.absent_percentage=absent_percentage;
    }
}
