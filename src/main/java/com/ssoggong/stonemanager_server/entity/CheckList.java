package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CheckList {

    @Id @GeneratedValue
    @Column(name = "checkList_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_idx")
    private Project project;

    private String name;
    private Boolean state; // 타입 확인 필요(Enum)
}
