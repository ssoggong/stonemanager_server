package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "subject_idx")
    private Long idx;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_idx")
    private Professor professor;

    private String year;

    private String semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_idx")
    private Department department;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<SubjectStudent> subjectStudentSet = new HashSet<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<UserSubject> userSubjectSet = new HashSet<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Project> projectSet = new HashSet<>();

    //== 빌더 ==//
    @Builder
    public Subject(String name, String year, String semester, Professor professor, Department department,Set<SubjectStudent> subjectStudentSet, Set<UserSubject> userSubjectSet, Set<Project> projectSet) {
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.professor = professor;
        professor.getSubjectSet().add(this); //== 연관관계 설정 ==//
        this.department = department;
        department.getSubjectSet().add(this); //== 연관관계 설정 ==//
        this.subjectStudentSet = subjectStudentSet;
        this.userSubjectSet = userSubjectSet;
        this.projectSet = projectSet;
    }
}
