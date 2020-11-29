package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "department_idx")
    private Long idx;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Student> studentSet = new HashSet<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Professor> professorSet = new HashSet<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Subject> subjectSet = new HashSet<>();

    //== 빌더 ==//
    @Builder
    public Department(String name, Set<Student> studentSet, Set<Professor> professorSet, Set<Subject> subjectSet) {
        this.name = name;
        this.studentSet = studentSet;
        this.professorSet = professorSet;
        this.subjectSet = subjectSet;
    }
}
