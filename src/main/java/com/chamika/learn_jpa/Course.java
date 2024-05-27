package com.chamika.learn_jpa;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_course")
public class Course {

    @Id
    @SequenceGenerator(name = "course_id_seq", sequenceName = "course_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_seq")
    @Column(updatable = false)
    private Integer id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String department;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<Student> studentList = new ArrayList<>();



    public Course() {
    }

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
