package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class UserTask {

    @Id @GeneratedValue
    @Column(name = "user_task_idx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_idx")
    private Task task;

}
