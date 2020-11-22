package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@IdClass(UserScheduleId.class)
public class UserSchedule {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_idx")
    private Schedule schedule;
}
