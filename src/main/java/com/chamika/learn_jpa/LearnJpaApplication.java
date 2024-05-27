package com.chamika.learn_jpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class LearnJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnJpaApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentIdCardRepository studentIdCardRepository) {
        return args -> {

//            Faker faker = new Faker();
//            Random random = new Random();
//
//            for (int i = 0; i < 12; i++) {
//                var name = faker.name();
//                String firstName = name.firstName();
//                String lastName = name.lastName();
//                String fullName = firstName + " " + lastName;
//                String email = fullName.split(" ")[0].toLowerCase() + "@gmail.com";
//                Integer age = random.nextInt(18, 100);
//
//                Student student = new Student(firstName, lastName, email, age);
//
//                String idCardNumber = faker.number().digits(6); // Generates a random 6-digit number
//                studentIdCardRepository.save(new StudentIdCard(idCardNumber, student));  // ! This will save the student and the student id card as well. 👈 First Saving the Student and then the Student Id Card will not work due to transactional issues.
//            }

            Student s1 = new Student("Chamika", "Jayasinghe", "chamika@gmail.com", 22);
            StudentIdCard i1 = new StudentIdCard("210247B", s1);

            Student s2 = new Student("Mike", "Dane", "mike@gmail.com", 22);
            StudentIdCard i2 = new StudentIdCard("210690B", s2);

            Book b1 = new Book("Atomic Habits", LocalDateTime.now());
            Book b2 = new Book("Deep Work", LocalDateTime.now().minusYears(2));
            Book b3 = new Book("Steal like an Artist", LocalDateTime.now().minusDays(10));
            Book b4 = new Book("Harry Potter", LocalDateTime.now().minusDays(10));

//        s1.addBook(b1);
//        s1.addBook(b2);
//        s1.addBook(b3);
//        s2.addBook(b4);

            /*

            * When you save the StudentIdCard instance, Spring Data JPA cascades the persist operation to the associated Student instance (s1). Due to the cascade = CascadeType.ALL annotation on the @OneToMany relationship, the Book instances associated with the Student instance are also persisted (saved) to the database.

            * In other words, even though you are not explicitly saving the Student and Book instances, they are saved automatically because of the cascading effect when you save the StudentIdCard instance. (So no need for StudentRepo or BookRepo 🥹)


             */

//        studentIdCardRepository.save(i1);
//        studentIdCardRepository.save(i2);


        };
    }


}