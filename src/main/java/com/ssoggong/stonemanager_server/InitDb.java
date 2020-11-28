package com.ssoggong.stonemanager_server;

import com.ssoggong.stonemanager_server.entity.*;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        //initService.dbInit1();
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

            Student student1 = createStudent("고운", "2016112182", department);
            Student student2 = createStudent("종근", "2016112107", department);
            Student student3 = createStudent("윤호", "2016112100", department);
            Student student4 = createStudent("영훈", "2016112101", department);

            studentSet.add(student1);
            studentSet.add(student2);
            studentSet.add(student3);
            studentSet.add(student4);

            return department;
        }

        private Student createStudent(String name, String studentId, Department department){
            Student student = Student.builder()
                    .name(name)
                    .studentId(studentId)
                    .department(department)
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
                    .projectUserSet(new HashSet<>())
                    .scheduleTagSet(new HashSet<>())
                    .taskTagSet(new HashSet<>())
                    .build();

            return project;
        }

        private Professor createProfessor(String name, Department department){
            Professor professor = Professor.builder()
                    .name(name)
                    .department(department)
                    .subjectSet(new HashSet<>())
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

        private ScheduleTag createScheduleTag(String name, Integer color, Project project) {
            ScheduleTag scheduleTag = ScheduleTag.builder()
                    .name(name)
                    .color(color)
                    .scheduleScheduleTagSet(new HashSet<>())
                    .project(project)
                    .build();

            return scheduleTag;
        }

        private TaskTag createTaskTag(String name, Integer color, Project project) {
            TaskTag taskTag = TaskTag.builder()
                    .name(name)
                    .color(color)
                    .project(project)
                    .taskTaskTagSet(new HashSet<>())
                    .build();

            return taskTag;
        }

        private File createFile(String name, Task task) {
            File file = File.builder()
                    .name(name)
                    .task(task)
                    .build();

            return file;
        }

        private Comment createComment(User user, Task task, LocalDateTime createDate, String content) {
            Comment comment = Comment.builder()
                    .user(user)
                    .task(task)
                    .createdDate(createDate)
                    .content(content)
                    .build();

            return comment;
        }

        private Checklist createChecklist(String name, Boolean state, Task task) {
            Checklist checklist = Checklist.builder()
                    .name(name)
                    .state(state)
                    .task(task)
                    .build();

            return checklist;
        }

        private Task createTask(String name, LocalDateTime dateTime, String description, Integer state, Project project, Set<UserTask> userTaskSet, Set<TaskTaskTag> taskTaskTagSet, Set<Checklist> checklistSet, Set<File> fileSet, Set<Comment> commentSet ) {
            Task task = Task.builder()
                    .name(name)
                    .deadline(dateTime)
                    .description(description)
                    .state(state)
                    .project(project)
                    .userTaskSet(userTaskSet)
                    .taskTaskTagSet(taskTaskTagSet)
                    .checklistSet(checklistSet)
                    .fileSet(fileSet)
                    .commentSet(commentSet)
                    .build();

            return task;
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

            ScheduleTag scheduleTag1 = createScheduleTag("scheduleTag1", 1, project1);
            ScheduleTag scheduleTag2 = createScheduleTag("scheduleTag2", 2, project1);
            em.persist(scheduleTag1);
            em.persist(scheduleTag2);

            TaskTag taskTag1 = createTaskTag("taskTag1", 1, project1);
            TaskTag taskTag2 = createTaskTag("taskTag2", 2, project1);
            em.persist(taskTag1);
            em.persist(taskTag2);

            Task task1 = createTask("task1", null, "할 일1", 0, project1, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
            em.persist(task1);

            File file1 = createFile("file1", task1);
            File file2 = createFile("file2", task1);
            em.persist(file1);
            em.persist(file2);

            Comment comment1 = createComment(user1, task1, null, "테스트 코멘트1");
            Comment comment2 = createComment(user2, task1, null, "테스트 코멘트2");
            Comment comment3 = createComment(user3, task1, null, "테스트 코멘트3");
            em.persist(comment1);
            em.persist(comment2);
            em.persist(comment3);

            Checklist checklist1 = createChecklist("checklist1", true, task1);
            Checklist checklist2 = createChecklist("checklist2", false, task1);
            Checklist checklist3 = createChecklist("checklist3", true, task1);
            em.persist(checklist1);
            em.persist(checklist2);
            em.persist(checklist3);
        }

    }
}