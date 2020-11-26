package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Professor;
import com.ssoggong.stonemanager_server.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Transactional
    public void saveProfessor(Professor professor) { professorRepository.save(professor); }
}
