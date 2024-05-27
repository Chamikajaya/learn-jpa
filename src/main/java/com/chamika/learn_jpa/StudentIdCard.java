package com.chamika.learn_jpa;

import jakarta.persistence.*;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
@Table(name = "t_student_id_card", uniqueConstraints = {@UniqueConstraint(name = "card_number_unique", columnNames = "cardNumber")})
public class StudentIdCard {

    @Id
    @SequenceGenerator(name = "student_id_card_id_seq", sequenceName = "student_id_card_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_card_id_seq")
    @Column(updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String cardNumber;

    // 👉 For One-to-One relationships FetchType is EAGER and for One to Many / Many to One FetchType would be LAZY
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // here if we set orphanRemoval = true, then if we delete the student id card, the student will also be deleted. Since we do not want that, we do not set orphanRemoval = true. (By default it is false.)

    // This is the foreign key column in the student_id_card table, and it references the id column in the student table. ->
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_id_card_student_id_fk"))
    private Student student;


    public StudentIdCard() {
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", student=" + student +
                '}';
    }
}
