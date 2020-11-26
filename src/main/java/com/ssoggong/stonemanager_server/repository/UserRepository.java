package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
