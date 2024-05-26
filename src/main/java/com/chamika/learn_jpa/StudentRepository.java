package com.chamika.learn_jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByEmail(String email);
    Optional<List<Student>>  findByAgeBetween(Integer min, Integer max);

    // * This is a native SQL query 🥹. However, only use native sql only if JPQL is not able to get the job done
    @Query(value = "SELECT * FROM t_student WHERE age BETWEEN ?1 AND ?2", nativeQuery = true)
    Optional<List<Student>>  findByAgeBetweenNativeSQL(Integer min, Integer max);

}
