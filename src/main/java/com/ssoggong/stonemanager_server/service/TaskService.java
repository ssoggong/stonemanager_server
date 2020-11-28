package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.CreateTaskResponse;
import com.ssoggong.stonemanager_server.entity.Project;
import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.entity.User;
import com.ssoggong.stonemanager_server.exception.ProjectNotFoundException;
import com.ssoggong.stonemanager_server.exception.TaskNotFoundException;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.ProjectRepository;
import com.ssoggong.stonemanager_server.repository.TaskRepository;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import com.ssoggong.stonemanager_server.dto.task.UpdateTaskRequest;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.exception.*;
import com.ssoggong.stonemanager_server.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskTaskTagRepository taskTaskTagRepository;
    private final TaskTagRepository taskTagRepository;
    private final UserTaskRepository userTaskRepository;
    private final FileRepository fileRepository;
    private final CommentRepository commentRepository;
    private final ChecklistRepository checklistRepository;

    @Transactional
    public void saveTask(Task task) { taskRepository.save(task); }

    @Transactional
    public CreateTaskResponse createTask(Long userId, Long projectId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Task task = Task.builder()
                .name("")
                .description("")
                .state(0)
                .deadline(null)
                .taskTaskTagSet(new HashSet<>())
                .userTaskSet(new HashSet<>())
                .commentSet(new HashSet<>())
                .fileSet(new HashSet<>())
                .checklistSet(new HashSet<>())
                .project(project)
                .build();
        Long taskId = taskRepository.save(task).getIdx();
        return new CreateTaskResponse(taskId);
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public void updateTask(Long userId, Long projectId, Long taskId, UpdateTaskRequest request) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);

        for(TaskTaskTag taskTaskTag: task.getTaskTaskTagSet()) {
            taskTaskTag.setTask(null);
            taskTaskTagRepository.delete(taskTaskTag);
        }
        for(UserTask userTask: task.getUserTaskSet()) {
            userTask.setTask(null);
            userTaskRepository.delete(userTask);
        }
        for(File file: task.getFileSet()) {
            file.setTask(null);
            fileRepository.delete(file);
        }
        for(Comment comment: task.getCommentSet()) {
            comment.setTask(null);
            commentRepository.delete(comment);
        }
        for(Checklist checklist: task.getChecklistSet()) {
            checklist.setTask(null);
            checklistRepository.delete(checklist);
        }

        task.setName(request.getTaskName());
        task.setDeadline(request.getTaskDueDate());
        task.setDescription(request.getTaskDescription());
        task.setState(request.getTaskState());

        for(Long taskTagId: request.getTaskTagIdList()) {
            TaskTag taskTag = taskTagRepository.findById(taskTagId).orElseThrow(TaskTagNotFoundException::new);
            TaskTaskTag taskTaskTag = TaskTaskTag.builder()
                    .task(task)
                    .taskTag(taskTag)
                    .build();
        }

        for(Long assigneeId: request.getTaskAssigneeIdList()) {
            User assignee = userRepository.findById(assigneeId).orElseThrow(UserNotFoundException::new);
            UserTask userTask = UserTask.builder()
                    .task(task)
                    .user(assignee)
                    .build();
        }

        for(Long commentId: request.getCommentIdList()) {
            Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            comment.setTask(task);
            task.getCommentSet().add(comment);
        }

        for(Long checklistId: request.getChecklistIdList()) {
            Checklist checklist = checklistRepository.findById(checklistId).orElseThrow(ChecklistNotFoundException::new);
            checklist.setTask(task);
            task.getChecklistSet().add(checklist);
        }

        for(Long fileId: request.getFileIdList()) {
            File file = fileRepository.findById(fileId).orElseThrow(FileNotFoundException::new);
            file.setTask(task);
            task.getFileSet().add(file);
        }

        saveTask(task);
    }
  
    public Task findById(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
