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

//            Faker faker = new Faker();
//            Random random = new Random();
//
//            for (int i = 0; i < 60; i++) {
//                var name = faker.name();
//                String firstName = name.firstName();
//                String lastName = name.lastName();
//                String fullName = firstName + " " + lastName;
//                String email = fullName.split(" ")[0].toLowerCase() + "@gmail.com";
//                Integer age = random.nextInt(18, 100);
//
//                Student student = new Student(firstName, lastName, email, age);
//
//                studentRepository.save(student);
//            Student student = new Student("Chamika", "Jayasinghe", "chamika@gmail.com", 22);
//            StudentIdCard studentIdCard = new StudentIdCard("123456", student);
//
//            studentIdCardRepository.save(studentIdCard);  // ! This will save the student and the student id card as well.



            // Since we have a bi-directional relationship between the student and the student id card, if we load the student, we can also load the student id card and vice versa. 👈
            studentIdCardRepository.findById(1).ifPresent(System.out::println);
            System.out.println("---------------------------------------------------");
            studentRepository.findById(61).ifPresent(System.out::println);


        };
    }


}