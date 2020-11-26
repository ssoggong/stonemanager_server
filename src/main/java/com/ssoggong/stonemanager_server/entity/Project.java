package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_idx")
    private Subject subject;

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

    public void setSubject(Subject subject){
        this.subject = subject;
        subject.getProjectSet().add(this);
    }

    //== 빌더 ==//
    @Builder
    public Project(Set<ProjectUser> projectUserSet, Set<Task> taskSet, Set<Schedule> scheduleSet, String projectName, String teamName) {
        this.projectUserSet = projectUserSet;
        this.taskSet = taskSet;
        this.scheduleSet = scheduleSet;
        this.projectName = projectName;
        this.teamName = teamName;
    }
}
