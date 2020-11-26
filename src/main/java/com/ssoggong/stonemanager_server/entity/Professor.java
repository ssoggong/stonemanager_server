package com.ssoggong.stonemanager_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "PROFESSOR")
public class Professor {
    @Id
    @GeneratedValue
    @Column(name = "professor_idx")
    private Long idx;

    @Column(name = "professor_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(table = "DEPARTMENT", name = "department_idx")
    private Department department;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Subject> subjects = new HashSet<>();
}
