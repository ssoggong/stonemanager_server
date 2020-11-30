package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Subject;
import com.ssoggong.stonemanager_server.entity.UserSchedule;
import com.ssoggong.stonemanager_server.entity.UserSubject;
import com.ssoggong.stonemanager_server.exception.MultipleNotFoundException;
import com.ssoggong.stonemanager_server.repository.UserScheduleRepository;
import com.ssoggong.stonemanager_server.repository.UserSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserSubjectService {

    private final UserSubjectRepository userSubjectRepository;

    @Transactional
    public void saveUserSubject(UserSubject userSubject) { userSubjectRepository.save(userSubject); }
}
