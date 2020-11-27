package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleTag {
    @Id
    @GeneratedValue
    @Column(name = "scheduleTag_idx")
    private Long idx;

    private String name;

    private Integer color;

    @OneToMany(mappedBy = "scheduleTag", cascade = CascadeType.ALL)
    private Set<ScheduleScheduleTag> scheduleScheduleTagSet = new HashSet<>();

    //== 빌더 ==//
    @Builder
    public ScheduleTag(String name, Integer color, Set<ScheduleScheduleTag> scheduleScheduleTagSet) {
        this.name = name;
        this.color = color;

        for(ScheduleScheduleTag scheduleScheduleTag: scheduleScheduleTagSet) {
            this.scheduleScheduleTagSet.add(scheduleScheduleTag);
        }
    }
}
