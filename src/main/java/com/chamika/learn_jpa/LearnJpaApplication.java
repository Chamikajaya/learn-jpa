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

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class LearnJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnJpaApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {
        return args -> {

            Faker faker = new Faker();
            Random random = new Random();

            for (int i = 0; i < 50; i++) {
                var name = faker.name();
                String firstName = name.firstName();
                String lastName = name.lastName();
                String fullName = firstName + " " + lastName;
                String email = fullName.split(" ")[0].toLowerCase() + "@gmail.com";
                Integer age = random.nextInt(18, 100);

                Student student = new Student(firstName, lastName, email, age);

                String idCardNumber = faker.number().digits(6); // Generates a random 6-digit number
                studentIdCardRepository.save(new StudentIdCard(idCardNumber, student));  // ! This will save the student and the student id card as well. 👈 First Saving the Student and then the Student Id Card will not work due to transactional issues.
            }

            // *  Revising Pagination and Sorting

            PageRequest pageRequest = PageRequest.of(
                    0,
                    10,
                    Sort.by("age").descending().and(Sort.by("firstName").ascending())
            );

            Page<Student> studentPage = studentRepository.findAll(pageRequest);


            studentPage.forEach((student) -> System.out.println(student.getFirstName() + " " + student.getLastName() + " --> " + student.getAge()));





        };
    }


}