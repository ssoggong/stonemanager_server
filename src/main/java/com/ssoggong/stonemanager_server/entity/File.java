package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id @GeneratedValue
    @Column(name = "file_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_idx")
    private Task task;

    private String name;
    private String fileUri;

    //== 빌더 ==//
    @Builder
    public File(Task task, String name, String uri) {
        this.task = task;
        task.getFileSet().add(this);
        this.name = name;
        this.fileUri = uri;
    }
}
