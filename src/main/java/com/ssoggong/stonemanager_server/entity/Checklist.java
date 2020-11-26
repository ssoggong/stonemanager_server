package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Checklist {

    @Id @GeneratedValue
    @Column(name = "checklist_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_idx")
    private Task task;

    private String name;
    private Boolean state; // 타입 확인 필요(Enum)
}
