package com.ssoggong.stonemanager_server.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SUBJECT_STUDENT")
public class SubjectStudent {
    @Id
    @GeneratedValue
    @Column(name = "subject_student_idx")
    private Long idx;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_idx")
    private Subject subject;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_idx")
    private Student student;

    //== 빌더 ==//
    public SubjectStudent(Subject subject, Student student) {
        this.subject = subject;
        this.student = student;
    }
}
