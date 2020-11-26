package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}
