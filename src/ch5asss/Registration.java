/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5asss;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Abu Yasser
 */
@Entity
public class Registration {
    @Id 
    private Integer studentid;
    
    @Id 
    private Integer courseid;
    
    private String semester;

    public Registration() {
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    
    
    
}
