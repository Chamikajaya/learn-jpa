package com.chamika.learn_jpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_student", uniqueConstraints = {@UniqueConstraint(name = "email_unique", columnNames = "email")})
public class Student {

    @Id
    @SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 1)
    // This annotation is used to define a sequence generator that will be used to generate unique values for the primary key.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    @Column(updatable = false)
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

    // form the bi-directional relationship - so that when we load the student, we can also load the student id card.
    @OneToOne(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL)
    // 👉 what orphanRemoval does is that if we delete the student, the student id card will also be deleted.
    private StudentIdCard studentIdCard;


    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // Since cascade = CascadeType.ALL --> This means that any operation performed on a Student entity (such as saving, updating, or deleting) will also be applied to its associated Book entities.
    private List<Book> bookList = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    //*  For many to many we have to use a bridge table This will create the bridge table with 2 columns where student_id references id in t_student and course_id references id in t_course ==>

    @JoinTable(
            name = "t_student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")

    )
    private List<Course> courses = new ArrayList<>();


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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addBook(Book book) {
        if (!bookList.contains(book)) {
            bookList.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (bookList.contains(book)) {
            bookList.remove(book);
            book.setStudent(null);
        }
    }


    public void studentEnrolsToCourse(Course course) {
        courses.add(course);  // add the course to this particular student's courses list
        course.getStudentList().add(this);  // add the student to the course's student list
    }

    public void studentUnrollsFromCourse(Course course) {
        courses.remove(course);  // remove the course from this particular student's courses list
        course.getStudentList().remove(this);  // remove this particular student from course's student list
    }


    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", age=" + age + '}';
    }
}
