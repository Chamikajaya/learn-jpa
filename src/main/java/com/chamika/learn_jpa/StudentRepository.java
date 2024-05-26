package com.chamika.learn_jpa;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByEmail(String email);

    Optional<List<Student>> findByAgeBetween(Integer min, Integer max);


    @Modifying
    // The @Modifying annotation is used to indicate that this query will modify data in the database. Spring Data JPA typically assumes that repository methods return data and do not modify it. This annotation informs Spring Data JPA that this assumption does not hold for this method.
    @Transactional
        // The @Transactional annotation is used to indicate that this method should be run within a transaction. A database transaction is a sequence of actions that are treated as a single unit of work. These actions should either complete entirely or not at all. The @Transactional annotation in Spring provides a way to define the scope of a single database transaction.
    void deleteStudentById(Integer id);



}
