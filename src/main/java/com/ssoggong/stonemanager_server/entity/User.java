package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
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

    private String id;

    private String pw;

    private String salt;

    private String image;

    private String email;

    // 테이블 따로 뺄지 정해야함
    private String department;

    //== 연관관계 메서드 ==//
    public void setUserSubject(UserSubject userSubject){
        userSubjectSet.add(userSubject);
        userSubject.setUser(this);
    }

    public void setUserTask(UserTask userTask){
        userTaskSet.add(userTask);
        userTask.setUser(this);
    }

    public void setProjectUser(ProjectUser projectUser){
        projectUserSet.add(projectUser);
        projectUser.setUser(this);
    }

    public void setUserSchedule(UserSchedule userSchedule){
        userScheduleSet.add(userSchedule);
        userSchedule.setUser(this);
    }

    public void setComment(Comment comment){
        commentSet.add(comment);
        comment.setUser(this);
    }
}
