package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSubject {
    @Id
    @GeneratedValue
    @Column(name = "user_subject_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_idx")
    private Subject subject;

    //== 빌더 ==//
    @Builder
    public UserSubject(User user, Subject subject) {
        this.user = user;
        user.getUserSubjectSet().add(this);
        this.subject = subject;
        subject.getUserSubjectSet().add(this); //== 연관관계 설정 ==//
    }
}
