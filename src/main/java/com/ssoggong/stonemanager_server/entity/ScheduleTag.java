package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SCHEDULETAG")
public class ScheduleTag {
    @Id
    @GeneratedValue
    @Column(name = "scheduletag_idx")
    private Long idx;

    private String name;

    private Integer color;

    @OneToMany(mappedBy = "scheduletag", cascade = CascadeType.ALL)
    private Set<ScheduleScheduleTag> scheduleScheduleTagSet = new HashSet<>();

    public void addScheduleScheduleTag(ScheduleScheduleTag scheduleScheduleTag){
        scheduleScheduleTagSet.add(scheduleScheduleTag);
        scheduleScheduleTag.setScheduleTag(this);
    }

    //== 빌더 ==//
    @Builder
    public ScheduleTag(String name, Integer color, Set<ScheduleScheduleTag> scheduleScheduleTagSet) {
        this.name = name;
        this.color = color;
        this.scheduleScheduleTagSet = scheduleScheduleTagSet;
    }
}
