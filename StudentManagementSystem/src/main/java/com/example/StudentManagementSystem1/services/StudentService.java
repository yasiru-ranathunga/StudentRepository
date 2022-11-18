package com.example.StudentManagementSystem1.services;

import com.example.StudentManagementSystem1.models.Students;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    List<Students>getAllStudents();

    Students saveStudent(Students students);

    Students getStudentById(Long id);

    Students updateStudent(Students students);

    void deleteStudentById(Long id);

    Page<Students> findpaginated(int pageNo,int pageSize);

}
