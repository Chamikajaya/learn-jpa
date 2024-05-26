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
    CommandLineRunner runner(StudentRepository studentRepository) {
        return args -> {

            Faker faker = new Faker();
            Random random = new Random();

//            for (int i = 0; i < 50; i++) {
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
//            }

            /*
            studentRepository.
                    findAll(Sort.by("age").ascending().and(Sort.by("firstName").descending())).  // first sort by age in ascending order, then sort by first name in descending order
                    forEach(s -> System.out.println(s.getFirstName() + " " + s.getAge()));
            */

            // * PAGINATION

            /*
            PageRequest pageRequest =  PageRequest.of(0, 10, Sort.by("age").ascending());
            Page<Student> studentPage = studentRepository.findAll(pageRequest);

            System.out.println(studentPage);

             */

            int pageNumber = 0;
            int pageSize = 10;

            while (true) {

                PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,
                        Sort.by("age").ascending().and(Sort.by("firstName").ascending()));

                Page<Student> studentPage = studentRepository.findAll(pageRequest);

                if (studentPage.isEmpty()) {
                    break;
                }

                System.out.println("Page number: " + pageNumber);
                studentPage.forEach(s -> System.out.println(s.getFirstName() + " " + s.getAge()));

                pageNumber++;

                System.out.println();
            }





        };
    }


}
