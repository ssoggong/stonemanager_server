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
@Table(name = "SUBJECT")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "subject_idx")
    private Long idx;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(table = "PROFESSOR", name = "professor_idx")
    private Professor professor;

    private String year;

    private String semester;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(table = "DEPARTMENT", name = "department_idx")
    private Department department;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<SubjectStudent> subjectStudentSet = new HashSet<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<UserSubject> userSubjectSet = new HashSet<>();

    public void addUserSubject(UserSubject userSubject){
        userSubjectSet.add(userSubject);
        userSubject.setSubject(this);
    }

    public void addSubjectStudent(SubjectStudent subjectStudent){
        subjectStudentSet.add(subjectStudent);
        subjectStudent.setSubject(this);
    }

    public void setDepartment(Department department){
        this.department = department;
        department.getSubjectSet().add(this);
    }

    public void setProfessor(Professor professor){
        this.professor = professor;
        professor.getSubjectSet().add(this);
    }
}
