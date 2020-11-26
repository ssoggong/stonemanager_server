package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TASK_TASKTAG")
public class TaskTaskTag {
    @Id
    @GeneratedValue
    @Column(name = "task_tasktag_idx")
    private Long idx;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_idx")
    private Task task;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tasktag_idx")
    private TaskTag taskTag;
}
