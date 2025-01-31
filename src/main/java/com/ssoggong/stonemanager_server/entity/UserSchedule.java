package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    //== 빌더 ==//
    @Builder
    public UserSchedule(User user, Schedule schedule) {
        this.user = user;
        user.getUserScheduleSet().add(this); //== 연관관계 설정 ==//
        this.schedule = schedule;
        schedule.getUserScheduleSet().add(this); //== 연관관계 설정 ==//

    }
}
