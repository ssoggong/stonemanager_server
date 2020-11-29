package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
}
