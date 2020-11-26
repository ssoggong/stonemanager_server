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
public class Project {

    @Id @GeneratedValue
    @Column(name = "project_idx")
    private Long idx;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<ProjectUser> projectUserSet = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Task> taskSet = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Schedule> scheduleSet = new HashSet<>();

    private String projectName;
    private String teamName;

    //== 연관관계 메서드 ==//
    public void addProjectUser(ProjectUser projectUser) {
        projectUserSet.add(projectUser);
        projectUser.setProject(this);
    }

    public void addTask(Task task){
        taskSet.add(task);
        task.setProject(this);
    }

    public void addSchedule(Schedule schedule){
        scheduleSet.add(schedule);
        schedule.setProject(this);
    }
}
