package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class UserSchedule {

    @Id @GeneratedValue
    @Column(name = "user_schedule_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_idx")
    private Schedule schedule;
}
