package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
