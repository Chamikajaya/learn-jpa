package com.chamika.learn_jpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

//         studentRepository.save(new Student("Chamika", "Jayasinghe", "chamika@gmail.com", 22));

            studentRepository.save(student);

//            String message = studentRepository.existsByEmail("chamika@gmail.com") ? "Student exists" : "Student does not exist";

            List<Student> students = studentRepository.findByAgeBetweenNativeSQL(40, 60).orElse(null);

            System.out.println(students);




        };
    }


}
