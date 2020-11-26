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
@Table(name = "DEPARTMENT")
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
}
