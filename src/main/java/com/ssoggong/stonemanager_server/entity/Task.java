package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "task_idx")
    private Long idx;

    private String name;
    private String description;
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_idx")
    private Project project;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Comment> commentSet = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<File> fileSet = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Checklist> checklistSet = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<UserTask> userTaskSet = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<TaskTaskTag> taskTaskTagSet = new HashSet<>();

    //== 연관관계 메서드 ==//
    public void addUserTask(UserTask userTask){
        userTaskSet.add(userTask);
        userTask.setTask(this);
    }

    public void addTaskTaskTag(TaskTaskTag taskTaskTag){
        taskTaskTagSet.add(taskTaskTag);
        taskTaskTag.setTask(this);
    }

    //==빌더==//
    @Builder
    public Task(String name, String description, LocalDateTime deadline, Project project, Set<Comment> commentSet, Set<File> fileSet, Set<Checklist> checklistSet, Set<UserTask> userTaskSet, Set<TaskTaskTag> taskTaskTagSet) {
        this.name = name;
        this.project = project;
        this.description = description;
        this.deadline = deadline;
        this.commentSet = commentSet;
        this.fileSet = fileSet;
        this.checklistSet = checklistSet;
        this.userTaskSet = userTaskSet;
        this.taskTaskTagSet = taskTaskTagSet;
    }

}
