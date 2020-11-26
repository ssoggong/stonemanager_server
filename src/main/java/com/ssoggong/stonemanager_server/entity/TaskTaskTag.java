package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TASK_TASKTAG")
public class TaskTaskTag {
    @Id
    @GeneratedValue
    @Column(name = "task_taskTag_idx")
    private Long idx;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_idx")
    private Task task;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "taskTag_idx")
    private TaskTag taskTag;

    //== 빌더 ==//
    @Builder
    public TaskTaskTag(Task task, TaskTag taskTag) {
        this.task = task;
        this.taskTag = taskTag;
    }
}
