package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.Comment;
import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Comment comment) { commentRepository.save(comment); }

    @Transactional
    public void createComment(String content, User user, Task task){
        Comment comment = Comment.builder()
                .content(content)
                .createdDate(LocalDateTime.now())
                .task(task)
                .user(user)
                .build();
        saveComment(comment);
    }

    @Transactional
    public void updateComment(Long commentId, String content, User user, Task task){
        // TODO 작성자와 동일한지
    }

}
