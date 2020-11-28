package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Subject;
import com.ssoggong.stonemanager_server.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Transactional
    public void saveSubject(Subject subject) { subjectRepository.save(subject);}
}
