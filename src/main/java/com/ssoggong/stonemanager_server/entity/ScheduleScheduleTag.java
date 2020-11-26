package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule_scheduletag")
public class ScheduleScheduleTag {
    @Id
    @GeneratedValue
    @Column(name = "schedule_scheduletag_idx")
    private Long idx;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_idx")
    private Schedule schedule;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduletag_idx")
    private ScheduleTag scheduleTag;
}
