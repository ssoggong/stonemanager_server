package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    //== 빌더 ==//
    @Builder
    public ScheduleScheduleTag (Schedule schedule, ScheduleTag scheduleTag) {
        this.schedule = schedule;
        this.scheduleTag = scheduleTag;
    }
}
