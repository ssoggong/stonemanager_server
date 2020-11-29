package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.CreateTaskResponse;
import com.ssoggong.stonemanager_server.dto.ReadScheduleTagResponse;
import com.ssoggong.stonemanager_server.dto.task.ReadTaskListResponse;
import com.ssoggong.stonemanager_server.dto.task.ReadTaskList_taskDto;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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

    public Task findById(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @Transactional
    public CreateTaskResponse createTask(Long userId, Long projectId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
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

    @Transactional
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

    @Transactional
    public void deleteTask(Long userId, Long projectId, Long taskId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

        taskRepository.delete(task);
    }

    public ReadTaskListResponse readTaskList(Long userId, Long projectId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));

        List<ReadTaskList_taskDto> taskDtoList = new ArrayList<>();
        for(Task task: project.getTaskSet()) {
            ReadTaskList_taskDto taskDto = ReadTaskList_taskDto.of(task);
            taskDtoList.add(taskDto);
        }

        return new ReadTaskListResponse(taskDtoList);
    }

    public ReadTaskListResponse readTaskListByUser(Long userId, Long projectId, Long assigneeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        User assignee = userRepository.findById(assigneeId).orElseThrow(() -> new UserNotFoundException(assigneeId));

        List<ReadTaskList_taskDto> taskDtoList = new ArrayList<>();
        for(UserTask userTask: userTaskRepository.findAllByUser(assignee)) {
            Task task = userTask.getTask();
            ReadTaskList_taskDto taskDto = ReadTaskList_taskDto.of(task);
            taskDtoList.add(taskDto);
        }

        return new ReadTaskListResponse(taskDtoList);
    }

    public ReadTaskListResponse readTaskListByTag(Long userId, Long projectId, Long tagId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        TaskTag taskTag = taskTagRepository.findById(tagId).orElseThrow(() -> new TaskTagNotFoundException(tagId));

        List<ReadTaskList_taskDto> taskDtoList = new ArrayList<>();
        for(TaskTaskTag taskTaskTag: taskTaskTagRepository.findAllByTaskTag(taskTag)) {
            Task task = taskTaskTag.getTask();
            ReadTaskList_taskDto taskDto = ReadTaskList_taskDto.of(task);
            taskDtoList.add(taskDto);
        }

        return new ReadTaskListResponse(taskDtoList);
    }

    public Task findByProjectAndTask(Project project, Long taskId){
        if(project.getTaskSet().stream()
                .filter(task -> task.getIdx() == taskId)
                .collect(Collectors.toSet()).size() == 1){
            return findById(taskId);
        }
        else throw new TaskNotFoundException(taskId);
    }


}
