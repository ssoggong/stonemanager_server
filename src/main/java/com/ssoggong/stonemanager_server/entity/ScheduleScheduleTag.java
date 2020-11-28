package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleScheduleTag {
    @Id
    @GeneratedValue
    @Column(name = "schedule_scheduleTag_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_idx")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleTag_idx")
    private ScheduleTag scheduleTag;

    //== 빌더 ==//
    @Builder
    public ScheduleScheduleTag (Schedule schedule, ScheduleTag scheduleTag) {
        this.schedule = schedule;
        this.scheduleTag = scheduleTag;
        //== 연관관계 설정 ==//
        schedule.getScheduleScheduleTagSet().add(this);
        scheduleTag.getScheduleScheduleTagSet().add(this);
    }
}
