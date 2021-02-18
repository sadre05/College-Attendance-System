/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import javafx.scene.control.CheckBox;

/**
 *
 * @author mdsad
 */
public class Getter_Setter {

    private int roll_number;
    private String student_name, gender, department,keys_dept_year_sem_sec;
    private CheckBox Present_Absent;

    public int getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(int roll_number) {
        this.roll_number = roll_number;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public CheckBox getPresent_Absent() {
        return Present_Absent;
    }

    public void setPresent_Absent(CheckBox Present_Absent) {
        this.Present_Absent = Present_Absent;
    }
    public  String getkeys_dept_year_sem_sec()
    {
        
        return keys_dept_year_sem_sec;
    }
    public  void setkeys_dept_year_sem_sec(String keys_dept_year_sem_sec)
    {
        this.keys_dept_year_sem_sec=keys_dept_year_sem_sec;
    }
    
//    public Getter_Setter() {
//
//    }

    public Getter_Setter(int roll_number, String student_name, String gender, String department,String keys_dept_year_sem_sec) {
        this.roll_number = roll_number;
        this.student_name = student_name;
        this.gender = gender;
        this.department = department;
        this.keys_dept_year_sem_sec=keys_dept_year_sem_sec;
        this.Present_Absent = new CheckBox();
    }

}
