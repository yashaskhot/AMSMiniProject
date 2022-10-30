package com.example.sample2;

public class StudentData5 {
    private String stud_id;
    private String stud_name;
    private String attendance_status;

    public StudentData5(String stud_id, String stud_name, String attendance_status) {
        this.stud_id=stud_id;
        this.stud_name=stud_name;
        this.attendance_status=attendance_status;

    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        stud_id = stud_id;
    }

    public String getStud_name() {
        return stud_name;
    }

    public void setStud_name(String stud_name) {
        stud_name = stud_name;
    }

    public String getAttendance_status() {
        return attendance_status;
    }

    public void setAttendance_status(String attendance_status) {
        attendance_status = attendance_status;
    }

}
