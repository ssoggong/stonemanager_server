package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.comment.CommentDto;
import com.ssoggong.stonemanager_server.dto.comment.CommentResponse;
import com.ssoggong.stonemanager_server.entity.Comment;
import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.exception.CommentNotFoundException;
import com.ssoggong.stonemanager_server.exception.UnauthorizedUserException;
import com.ssoggong.stonemanager_server.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Comment comment) { commentRepository.save(comment); }

    public Comment findById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));
    }
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
    public void updateComment(Long commentId, String content, Long userId){
        Comment comment = findById(commentId);
        if(!comment.getUser().getIdx().equals(userId)) throw new UnauthorizedUserException(userId);
        comment.setContent(content);
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId){
        Comment comment = findById(commentId);
        if(!comment.getUser().getIdx().equals(userId)) throw new UnauthorizedUserException(userId);
        commentRepository.deleteById(commentId);
    }

   public CommentResponse readComments(Task task){
       List<CommentDto> dtos = new ArrayList<>();
       for(Comment comment: task.getCommentSet()){
            dtos.add(CommentDto.of(comment, comment.getUser()));
       }
       return new CommentResponse(dtos);
   }

}
