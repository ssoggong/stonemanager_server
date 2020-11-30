package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Student;
import com.ssoggong.stonemanager_server.entity.Subject;
import com.ssoggong.stonemanager_server.entity.SubjectStudent;
import com.ssoggong.stonemanager_server.exception.StudentNotFoundException;
import com.ssoggong.stonemanager_server.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public void saveStudent(Student student) { studentRepository.save(student); }

    public List<Subject> getSubjects(String studentId){
        Student student = studentRepository.findByStudentId(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        Set<SubjectStudent> subjectStudents = student.getSubjectStudentSet();
        List<Subject> subjects = new ArrayList<>();
        for(SubjectStudent subjectStudent : subjectStudents){
            subjects.add(subjectStudent.getSubject());
        }
        return subjects;
    }
}
