package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "SUBJECT")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "subject_idx")
    private Long idx;

    @Column(name = "subject_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(table = "PROFESSOR", name = "professor_idx")
    private Professor professor;

    @Column(name = "subject_year")
    private String year;

    @Column(name = "subject_semester")
    private String semester;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(table = "DEPARTMENT", name = "department_idx")
    private Department department;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();
}
