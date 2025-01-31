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
    private int state;

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

    //==빌더==//
    @Builder
    public Task(String name, String description, LocalDateTime deadline, int state, Project project, Set<Comment> commentSet, Set<File> fileSet, Set<Checklist> checklistSet, Set<UserTask> userTaskSet, Set<TaskTaskTag> taskTaskTagSet) {
        this.name = name;
        this.project = project;
        project.getTaskSet().add(this); //== 연관관계 설정 ==//
        this.description = description;
        this.deadline = deadline;
        this.state = state;
        this.commentSet = commentSet;
        this.fileSet = fileSet;
        this.checklistSet = checklistSet;
        this.userTaskSet = userTaskSet;
        this.taskTaskTagSet = taskTaskTagSet;
    }

}
