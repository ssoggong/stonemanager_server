package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Project {

    @Id @GeneratedValue
    @Column(name = "project_idx")
    private Long idx;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectUser> projectUserList = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<File> fileList = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<CheckList> checkListList = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> taskList = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Schedule> scheduleList = new ArrayList<>();

    private String projectName;
    private String teamName;

    //== 연관관계 메서드 ==//
    public void addProjectUser(ProjectUser projectUser) {
        projectUserList.add(projectUser);
        projectUser.setProject(this);
    }

    public void addComment(Comment comment){
        commentList.add(comment);
        comment.setProject(this);
    }

    public void addFile(File file) {
        fileList.add(file);
        file.setProject(this);
    }

    public void addCheckList(CheckList checkList){
        checkListList.add(checkList);
        checkList.setProject(this);
    }

    public void addTask(Task task){
        taskList.add(task);
        task.setProject(this);
    }

    public void addSchedule(Schedule schedule){
        scheduleList.add(schedule);
        schedule.setProject(this);
    }
}
