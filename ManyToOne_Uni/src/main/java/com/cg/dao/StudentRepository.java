package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
