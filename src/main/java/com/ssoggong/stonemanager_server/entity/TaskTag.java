package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskTag {
    @Id
    @GeneratedValue
    @Column(name = "taskTag_idx")
    private Long idx;

    private String name;

    private Integer color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "taskTag", cascade = CascadeType.ALL)
    private Set<TaskTaskTag> taskTaskTagSet = new HashSet<>();

    //==빌더==//
    @Builder
    public TaskTag(String name, Integer color, Project project, Set<TaskTaskTag> taskTaskTagSet) {
        this.name = name;
        this.color = color;
        this.project = project;
        project.getTaskTagSet().add(this); //== 연관관계 설정 ==//
        this.taskTaskTagSet = taskTaskTagSet;

    }
}
