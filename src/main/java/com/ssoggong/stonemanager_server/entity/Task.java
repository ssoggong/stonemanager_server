package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TASK")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "task_idx")
    private Long idx;

    @Column(name = "task_name")
    private String name;

    @ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_idx")
    private Project project;

    @Column(name = "task_description")
    private String description;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<File> files = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CheckList> checkLists = new HashSet<>();

    @Column(name = "task_createTime")
    private LocalDateTime createTime;

    @Column(name = "task_deadline")
    private LocalDateTime deadline;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TaskTag> tags = new HashSet<>();

}
