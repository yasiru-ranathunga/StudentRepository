package com.example.StudentManagementSystem1.repository;

import com.example.StudentManagementSystem1.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students,Long> {
}
