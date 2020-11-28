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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<TaskTag> taskTagSet = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<ScheduleTag> scheduleTagSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_idx")
    private Subject subject;

    private String projectName;
    private String teamName;

    //== 빌더 ==//
    @Builder
    public Project(Set<ProjectUser> projectUserSet, Set<Task> taskSet, Set<Schedule> scheduleSet, Set<TaskTag> taskTagSet, Set<ScheduleTag> scheduleTagSet, Subject subject, String projectName, String teamName) {

        for(ProjectUser projectUser: projectUserSet) {
            this.projectUserSet.add(projectUser);
        }

        for(Task task: taskSet) {
            this.taskSet.add(task);
        }

        for(Schedule schedule: scheduleSet) {
            this.scheduleSet.add(schedule);
        }

        for(TaskTag taskTag: taskTagSet){
            this.taskTagSet.add(taskTag);
        }

        for(ScheduleTag scheduleTag: scheduleTagSet) {
            this.scheduleTagSet.add(scheduleTag);
        }

        this.subject = subject;
        subject.getProjectSet().add(this); //== 연관관계 설정 ==//

        this.projectName = projectName;
        this.teamName = teamName;
    }
}
