package com.ssoggong.stonemanager_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Professor {
    @Id
    @GeneratedValue
    @Column(name = "professor_idx")
    private Long idx;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_idx")
    private Department department;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private Set<Subject> subjectSet = new HashSet<>();

    //== 연관관계 메서드 ==//
    public void setDepartment(Department department){
        this.department = department;
        department.getProfessorSet().add(this);
    }

    //== 빌더 ==//
    @Builder
    public Professor(String name, Department department, Set<Subject> subjectSet) {
        this.name = name;
        this.department = department;
        this.subjectSet = subjectSet;
    }
}
