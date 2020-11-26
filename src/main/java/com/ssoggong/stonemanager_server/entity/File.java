package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class File {

    @Id @GeneratedValue
    @Column(name = "file_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_idx")
    private Task task;

    private String name;

    public void setTask(Task task){
        this.task = task;
        task.getFiles().add(this);
    }
}
