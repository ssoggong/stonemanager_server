package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id @GeneratedValue
    @Column(name = "schedule_idx")
    private Long idx;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private Set<UserSchedule> userScheduleSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_idx")
    private Project project;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private Set<ScheduleScheduleTag> scheduleScheduleTagSet = new HashSet<>();

    private String name;

    private LocalDateTime date;

    private String description;

    @Builder
    public Schedule(Project project, Set<UserSchedule> userScheduleSet, Set<ScheduleScheduleTag> scheduleScheduleTagSet, String name, LocalDateTime date, String description) {
        this.project = project;
        project.getScheduleSet().add(this);

        for(UserSchedule userSchedule: userScheduleSet) {
            this.userScheduleSet.add(userSchedule);
        }

        for(ScheduleScheduleTag scheduleScheduleTag: scheduleScheduleTagSet) {
            this.scheduleScheduleTagSet.add(scheduleScheduleTag);
        }

        this.name = name;
        this.date = date;
        this.description = description;
    }
}
