package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Comment;
import com.ssoggong.stonemanager_server.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Comment comment) { commentRepository.save(comment); }

}
