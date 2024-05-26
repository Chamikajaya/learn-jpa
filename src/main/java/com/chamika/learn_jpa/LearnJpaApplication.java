package com.chamika.learn_jpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class LearnJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnJpaApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository studentRepository) {
        return args -> {

            Faker faker = new Faker();
            Random random = new Random();

            var name = faker.name();
            String firstName = name.firstName();
            String lastName = name.lastName();
            String fullName = firstName + " " + lastName;
            String email = fullName.split(" ")[0].toLowerCase() + "@gmail.com";
            Integer age = random.nextInt(18, 100);

            Student student = new Student(firstName, lastName, email, age);

            studentRepository.save(student);


            studentRepository.deleteStudentById(6);

            studentRepository.
                    findAll(Sort.by("age").ascending().and(Sort.by("firstName").descending())).  // first sort by age in ascending order, then sort by first name in descending order
                    forEach(s -> System.out.println(s.getFirstName() + " " + s.getAge()));


        };
    }


}
