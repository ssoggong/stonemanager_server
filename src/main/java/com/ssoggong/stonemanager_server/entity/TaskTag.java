package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TASKTAG")
public class TaskTag {
    @Id
    @GeneratedValue
    @Column(name = "tasktag_idx")
    private Long idx;

    private String name;

    private Integer color;

    @OneToMany(mappedBy = "tasktag", cascade = CascadeType.ALL)
    private Set<TaskTaskTag> taskTaskTagSet = new HashSet<>();

    public void addTaskTaskTag(TaskTaskTag taskTaskTag){
        taskTaskTagSet.add(taskTaskTag);
        taskTaskTag.setTaskTag(this);
    }
}
