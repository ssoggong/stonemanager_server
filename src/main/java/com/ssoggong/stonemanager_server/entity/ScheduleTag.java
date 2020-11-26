package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "SCHEDULETAG")
public class ScheduleTag {
    @Id
    @GeneratedValue
    @Column(name = "scheduletag_idx")
    private Long idx;

    @Column(name = "scheduletag_name")
    private String name;

    @Column(name = "scheduletag_color")
    private Integer color;

    @OneToMany(mappedBy = "scheduletag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Schedule> schedules = new HashSet<>();
}
