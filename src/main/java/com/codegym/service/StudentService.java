package com.codegym.service;

import com.codegym.model.Student;
import com.codegym.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository iStudentRepository;

    @Override
    public List<Student> findAll() {
        return iStudentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        iStudentRepository.save(student);
    }

    @Override
    public Student findById(Long id) {
        return iStudentRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iStudentRepository.remove(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return iStudentRepository.findByName(name);
    }
}
