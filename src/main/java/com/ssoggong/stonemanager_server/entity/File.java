package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id @GeneratedValue
    @Column(name = "file_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_idx")
    private Task task;

    private String name;

    //== 연관관계 메서드 ==//
    public void setTask(Task task){
        this.task = task;
        task.getFileSet().add(this);
    }

    //== 빌더 ==//
    @Builder
    public File(Task task, String name) {
        this.task = task;
        this.name = name;
    }
}
