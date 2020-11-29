package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_idx")
    private Long idx;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserSubject> userSubjectSet = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ProjectUser> projectUserSet = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserTask> userTaskSet = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserSchedule> userScheduleSet = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Comment> commentSet = new HashSet<>();

    private String name;

    private String studentId;

    private String pw;

    private String salt;

    private String image;

    private String email;

    //== 빌더 ==//
    @Builder
    public User(Set<UserSubject> userSubjectSet, Set<ProjectUser> projectUserSet, Set<UserTask> userTaskSet, Set<UserSchedule> userScheduleSet, Set<Comment> commentSet,String name, String studentId, String pw, String salt, String image, String email) {
        this.userSubjectSet = userSubjectSet;
        this.projectUserSet = projectUserSet;
        this.userTaskSet = userTaskSet;
        this.userScheduleSet = userScheduleSet;
        this.commentSet = commentSet;
        this.name = name;
        this.studentId = studentId;
        this.pw = pw;
        this.salt = salt;
        this.image = image;
        this.email = email;
    }
}
