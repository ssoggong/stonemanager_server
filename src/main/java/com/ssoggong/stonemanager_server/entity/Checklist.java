package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Checklist {

    @Id @GeneratedValue
    @Column(name = "checklist_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_idx")
    private Task task;

    private String name;
    private Boolean state; // 타입 확인 필요(Enum)

    public void setTask(Task task){
        this.task = task;
        task.getChecklistSet().add(this);
    }

    //== 빌더 ==//
    @Builder
    public Checklist(Task task, String name, Boolean state) {
        this.task = task;
        this.name = name;
        this.state = state;
    }
}
