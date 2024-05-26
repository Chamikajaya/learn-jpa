package com.chamika.learn_jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "t_student", uniqueConstraints = {@UniqueConstraint(name = "email_unique", columnNames = "email")})
public class Student {

    @Id
    @SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 1)
    // This annotation is used to define a sequence generator that will be used to generate unique values for the primary key.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    private Integer id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    // there is no need to specify unique constraint here because it is already specified in the table annotation above 😊
    private String email;
    @Column(nullable = false)
    private Integer age;





    public Student() {
    }

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
