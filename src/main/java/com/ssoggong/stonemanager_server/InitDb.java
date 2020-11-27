package com.ssoggong.stonemanager_server;

import com.ssoggong.stonemanager_server.entity.*;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private User createUser(String name, String studentId, String pw, String image, String salt, String email){
            User user = User.builder()
                    .name(name)
                    .studentId(studentId)
                    .pw(pw)
                    .image(image)
                    .salt(salt)
                    .email(email)
                    .commentSet(new HashSet<>())
                    .projectUserSet(new HashSet<>())
                    .userScheduleSet(new HashSet<>())
                    .userSubjectSet(new HashSet<>())
                    .userTaskSet(new HashSet<>())
                    .build();

            em.persist(user);
            return user;
        }

        private Department createDepartment(String name){

            Department department = Department.builder()
                    .name(name)
                    .subjectSet(new HashSet<>())
                    .studentSet(new HashSet<>())
                    .professorSet(new HashSet<>())
                    .build();

            Set<Student> studentSet = new HashSet<>();

            Student student1 = createStudent("고운", "2016112182");
            Student student2 = createStudent("종근", "2016112107");
            Student student3 = createStudent("윤호", "2016112100");
            Student student4 = createStudent("영훈", "2016112101");

            student1.setDepartment(department);
            student2.setDepartment(department);
            student3.setDepartment(department);
            student4.setDepartment(department);

            studentSet.add(student1);
            studentSet.add(student2);
            studentSet.add(student3);
            studentSet.add(student4);

            return department;
        }

        private Student createStudent(String name, String studentId){
            Student student = Student.builder()
                    .name(name)
                    .studentId(studentId)
                    .subjectStudentSet(new HashSet<>())
                    .build();

            return student;
        }

        private Subject createSubject(String name, Professor professor, String semester, String year, Department department){
            Subject subject = Subject.builder()
                    .name(name)
                    .professor(professor)
                    .semester(semester)
                    .year(year)
                    .department(department)
                    .projectSet(new HashSet<>())
                    .subjectStudentSet(new HashSet<>())
                    .userSubjectSet(new HashSet<>())
                    .build();

            return subject;
        }

        private Project createProject(String projectName, String teamName, Subject subject) {
            Project project = Project.builder()
                    .projectName(projectName)
                    .teamName(teamName)
                    .scheduleSet(new HashSet<>())
                    .subject(subject)
                    .taskSet(new HashSet<>())
                    .build();

            return project;
        }

        private Professor createProfessor(String name, Department department){
            Professor professor = Professor.builder()
                    .name(name)
                    .department(department)
                    .build();

            return professor;
        }

        private ProjectUser createProjectUser(Project project, User user){
            ProjectUser projectUser = ProjectUser.builder()
                    .project(project)
                    .user(user)
                    .build();

            return projectUser;
        }

        private ScheduleTag createScheduleTag(String name, Integer color) {
            ScheduleTag scheduleTag = ScheduleTag.builder()
                    .name(name)
                    .color(color)
                    .build();

            return scheduleTag;
        }

        private final EntityManager em;
        public void dbInit1(){

            User user1 = createUser("고운", "2016112182", "test","test","test","test");
            User user2 = createUser("종근", "2016112107", "test22","test22","test22","test22");
            User user3 = createUser("윤호", "2016112100", "test33","test33","test33","test33");

            em.persist(user1);
            em.persist(user2);
            em.persist(user3);


            Department department1 = createDepartment("컴퓨터공학과");
            em.persist(department1);

            Professor professor1 = createProfessor("최은만", department1);
            em.persist(professor1);

            Subject subject1 = createSubject("소프트웨어공학개론", professor1, "2", "2020", department1);
            em.persist(subject1);

            Project project1 = createProject("stone manager", "쏘꽁", subject1);
            em.persist(project1);

            ProjectUser projectUser1 = createProjectUser(project1, user1);
            ProjectUser projectUser2 = createProjectUser(project1, user2);
            ProjectUser projectUser3 = createProjectUser(project1, user3);
            em.persist(projectUser1);
            em.persist(projectUser2);
            em.persist(projectUser3);

            ScheduleTag scheduleTag1 = createScheduleTag("scheduleTag1", 1);
            ScheduleTag scheduleTag2 = createScheduleTag("scheduleTag2", 2);
            em.persist(scheduleTag1);
            em.persist(scheduleTag2);
        }

    }
}










