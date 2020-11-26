package com.ssoggong.stonemanager_server.repository;

import com.ssoggong.stonemanager_server.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
