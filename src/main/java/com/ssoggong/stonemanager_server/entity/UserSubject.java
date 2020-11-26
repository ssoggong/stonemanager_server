package com.ssoggong.stonemanager_server.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_SUBJECT")
public class UserSubject {
    @Id
    @GeneratedValue
    @Column(name = "user_subject_idx")
    private Long idx;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(targetEntity = Subject.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_idx")
    private Subject subject;

    //== 빌더 ==//
    public UserSubject(User user, Subject subject) {
        this.user = user;
        this.subject = subject;
    }
}
