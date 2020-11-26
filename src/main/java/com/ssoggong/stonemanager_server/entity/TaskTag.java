package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TASKTAG")
public class TaskTag {
    @Id
    @GeneratedValue
    @Column(name = "tasktag_idx")
    private Long idx;

    @Column(name = "tasktag_name")
    private String name;

    @Column(name = "tasktag_color")
    private Integer color;

    @OneToMany(mappedBy = "tasktag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();
}
