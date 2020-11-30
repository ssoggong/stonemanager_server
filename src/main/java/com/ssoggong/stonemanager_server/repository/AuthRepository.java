package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByEmail(String email);
}
