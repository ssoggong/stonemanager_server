package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_idx")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    private String content;
    private LocalDateTime createdDate; // 타입 확인 필요
}
