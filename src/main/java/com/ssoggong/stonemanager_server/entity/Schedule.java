package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Schedule {

    @Id @GeneratedValue
    @Column(name = "schedule_idx")
    private Long idx;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<UserSchedule> userScheduleList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_idx")
    private Project project;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<ScheduleScheduleTag> scheduleScheduleTagList = new ArrayList<>();

    private String name;

    private LocalDateTime date;

    private String description;

    // 필요 여부 확인
    private LocalDateTime createdTime;

    //== 연관관계 메서드 ==//
    public void setUserSchedule(UserSchedule userSchedule){
        userScheduleList.add(userSchedule);
        userSchedule.setSchedule(this);
    }

    public void setScheduleScheduleTag(ScheduleScheduleTag scheduleScheduleTag) {
        scheduleScheduleTagList.add(scheduleScheduleTag);
        scheduleScheduleTag.setSchedule(this);
    }
}
