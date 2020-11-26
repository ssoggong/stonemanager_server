package com.ssoggong.stonemanager_server.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TASKTAG")
public class TaskTag {
    @Id
    @GeneratedValue
    @Column(name = "taskTag_idx")
    private Long idx;

    private String name;

    private Integer color;

    @OneToMany(mappedBy = "taskTag", cascade = CascadeType.ALL)
    private Set<TaskTaskTag> taskTaskTagSet = new HashSet<>();

    //== 연관관계 메서드==//
    public void addTaskTaskTag(TaskTaskTag taskTaskTag){
        taskTaskTagSet.add(taskTaskTag);
        taskTaskTag.setTaskTag(this);
    }

    //==빌더==//
    public TaskTag(String name, Integer color, Set<TaskTaskTag> taskTaskTagSet) {
        this.name = name;
        this.color = color;
        this.taskTaskTagSet = taskTaskTagSet;
    }
}
