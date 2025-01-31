package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_idx")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    private String content;
    private LocalDateTime createdDate; // 타입 확인 필요

    //== 빌더 ==//
    @Builder
    public Comment(Task task, User user, String content, LocalDateTime createdDate){
        this.task = task;
        task.getCommentSet().add(this);
        this.user = user;
        user.getCommentSet().add(this);
        this.content = content;
        this.createdDate = createdDate;
    }
}
