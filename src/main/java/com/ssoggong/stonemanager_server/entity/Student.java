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
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "student_idx")
    private Long idx;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_idx")
    private Department department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<SubjectStudent> subjectStudentSet = new HashSet<>();

    public void setDepartment(Department department){
        this.department = department;
        department.getStudentSet().add(this);
    }

    public void addSubjectStudent(SubjectStudent subjectStudent){
        subjectStudentSet.add(subjectStudent);
        subjectStudent.setStudent(this);
    }
}
