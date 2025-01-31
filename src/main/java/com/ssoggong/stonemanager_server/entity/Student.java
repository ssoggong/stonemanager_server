package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "student_idx")
    private Long idx;

    private String studentId;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_idx")
    private Department department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<SubjectStudent> subjectStudentSet = new HashSet<>();

    //== 빌더 ==//
    @Builder
    public Student(String studentId, String name, Department department, Set<SubjectStudent> subjectStudentSet) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        department.getStudentSet().add(this);
        this.subjectStudentSet = subjectStudentSet;
    }
}
