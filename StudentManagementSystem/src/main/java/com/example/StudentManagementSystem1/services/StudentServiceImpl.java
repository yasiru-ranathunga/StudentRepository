package com.example.StudentManagementSystem1.services;


import com.example.StudentManagementSystem1.models.Students;
import com.example.StudentManagementSystem1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Students>getAllStudents(){
       return studentRepository.findAll();
    }

    @Override
    public Students saveStudent(Students students) {
        return studentRepository.save(students);
    }

    @Override
    public Students getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Students updateStudent(Students students) {
        return studentRepository.save(students);
    }

    @Override
    public void deleteStudentById(Long id) {
         studentRepository.deleteById(id);
    }

    @Override
    public Page<Students> findpaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1,pageSize);
        return this.studentRepository.findAll(pageable);
    }

}
