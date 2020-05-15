/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5asss;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    
    @NamedQuery(name = "Course.findById",
            query = "select s from Course s where s.id= :id")
           
}
)
public class Course {
        @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private String room;
        @ManyToMany(mappedBy = "courses",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE})
        List<Students> students;
    public Course() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        
        
    
}
