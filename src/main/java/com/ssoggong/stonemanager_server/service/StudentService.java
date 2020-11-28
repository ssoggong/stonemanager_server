package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Student;
import com.ssoggong.stonemanager_server.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public void saveStudent(Student student) { studentRepository.save(student); }
}
