package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TASK")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "task_idx")
    private Long idx;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_idx")
    private Project project;

    private String description;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<File> files = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Checklist> checkLists = new HashSet<>();

    private LocalDateTime createTime;

    private LocalDateTime deadline;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<UserTask> userTaskSet = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<TaskTaskTag> taskTaskTagSet = new HashSet<>();

    public void addUserTask(UserTask userTask){
        userTaskSet.add(userTask);
        userTask.setTask(this);
    }

    public void addTaskTaskTag(TaskTaskTag taskTaskTag){
        taskTaskTagSet.add(taskTaskTag);
        taskTaskTag.setTask(this);
    }
}
