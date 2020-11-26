package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(User user) { userRepository.save(user); }

    public Optional<User> findById(Long userId) { return userRepository.findById(userId); }
}
