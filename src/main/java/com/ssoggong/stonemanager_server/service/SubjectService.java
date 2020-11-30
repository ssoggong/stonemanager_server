package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.project.FindStudentDto;
import com.ssoggong.stonemanager_server.entity.Subject;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.entity.UserSubject;
import com.ssoggong.stonemanager_server.exception.SubjectNotFoundException;
import com.ssoggong.stonemanager_server.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Transactional
    public void saveSubject(Subject subject) { subjectRepository.save(subject);}

    public Subject findById(Long subjectId){
        return subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException(subjectId));
    }

    public List<FindStudentDto> findUser(Long subjectId, String keyword){
        Subject subject = findById(subjectId);
        List<UserSubject> userSubjects = subject.getUserSubjectSet().stream()
                .filter(userSubject -> userSubject.getSubject().getIdx().equals(subjectId)).collect(Collectors.toList());
        List<FindStudentDto> dtos = new ArrayList<>();
        for (UserSubject userSubject : userSubjects){
            if(userSubject.getUser().getName().equals(keyword)){
                dtos.add(FindStudentDto.of(userSubject.getUser()));
            }
        }
        return dtos;
    }
}
