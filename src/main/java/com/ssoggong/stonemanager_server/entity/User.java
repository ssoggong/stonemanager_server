package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_idx")
    private Long idx;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSubject> userSubjectList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProjectUser> projectUserList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTask> userTaskList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSchedule> userScheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    private String id;

    private String pw;

    private String salt;

    private String image;

    private String email;

    // 테이블 따로 뺄지 정해야함
    private String department;

    //== 연관관계 메서드 ==//
    public void setUserSubject(UserSubject userSubject){
        userSubjectList.add(userSubject);
        userSubject.setUser(this);
    }

    public void setUserTask(UserTask userTask){
        userTaskList.add(userTask);
        userTask.setUser(this);
    }

    public void setProjectUser(ProjectUser projectUser){
        projectUserList.add(projectUser);
        projectUser.setUser(this);
    }

    public void setUserSchedule(UserSchedule userSchedule){
        userScheduleList.add(userSchedule);
        userSchedule.setUser(this);
    }

    public void setComment(Comment comment){
        commentList.add(comment);
        comment.setUser(this);
    }
}
