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
@Table(name = "PROFESSOR")
public class Professor {
    @Id
    @GeneratedValue
    @Column(name = "professor_idx")
    private Long idx;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_idx")
    private Department department;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private Set<Subject> subjectSet = new HashSet<>();

    public void setDepartment(Department department){
        this.department = department;
        department.getProfessorSet().add(this);
    }
}
