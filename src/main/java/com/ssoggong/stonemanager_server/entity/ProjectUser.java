package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectUser {

    @Id @GeneratedValue
    @Column(name = "project_user_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_idx")
    private Project project;

    //== 빌더 ==//
    @Builder
    public ProjectUser(User user, Project project) {
        this.user = user;
        this.project = project;
    }
}

