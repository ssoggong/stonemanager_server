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

    public void updateTask(Long userId, Long projectId, Long taskId, UpdateTaskRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

        for(TaskTaskTag taskTaskTag: task.getTaskTaskTagSet()) {
            taskTaskTag.setTask(null);
            taskTaskTagRepository.delete(taskTaskTag);
        }
        for(UserTask userTask: task.getUserTaskSet()) {
            userTask.setTask(null);
            userTaskRepository.delete(userTask);
        }

        task.setName(request.getTaskName());
        task.setDeadline(request.getTaskDueDate());
        task.setDescription(request.getTaskDescription());
        task.setState(request.getTaskState());

        for(Long taskTagId: request.getTaskTagIdList()) {
            TaskTag taskTag = taskTagRepository.findById(taskTagId).orElseThrow(() -> new TaskTagNotFoundException(taskTagId));
            TaskTaskTag taskTaskTag = TaskTaskTag.builder()
                    .task(task)
                    .taskTag(taskTag)
                    .build();
        }

        for(Long assigneeId: request.getTaskAssigneeIdList()) {
            User assignee = userRepository.findById(assigneeId).orElseThrow(() -> new UserNotFoundException(userId));
            UserTask userTask = UserTask.builder()
                    .task(task)
                    .user(assignee)
                    .build();
        }
        saveTask(task);
    }
  
    public Task findById(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
