package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.SubjectStudent;
import com.ssoggong.stonemanager_server.repository.SubjectStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectStudentService {

    private final SubjectStudentRepository subjectStudentRepository;

    @Transactional
    public void saveSubjectStudent(SubjectStudent subjectStudent) { subjectStudentRepository.save(subjectStudent);}
}
