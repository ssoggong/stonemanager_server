package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USER_SUBJECT")
public class UserSubject {
    @Id
    @GeneratedValue
    @Column(name = "user_subject_idx")
    private Long idx;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(targetEntity = Subject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_idx")
    private Subject subject;
}
