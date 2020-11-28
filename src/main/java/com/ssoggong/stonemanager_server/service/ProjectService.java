package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.project.*;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.exception.ProjectNotFoundException;
import com.ssoggong.stonemanager_server.exception.UserNotFoundException;
import com.ssoggong.stonemanager_server.repository.ProjectRepository;
import com.ssoggong.stonemanager_server.util.Constants;
import com.ssoggong.stonemanager_server.util.ParticipateCalculator;
import com.ssoggong.stonemanager_server.util.ProgressCalaulator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public void saveProject(Project project) { projectRepository.save(project); }

    public Project findById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
    }

    public ProjectDetailResponse getProjectDetail(Long projectId, Long userId){
        Project project = findById(projectId);
        if(project.getProjectUserSet().stream()
                .filter(projectUser -> projectUser.getUser().getIdx() == userId)
                .collect(Collectors.toSet()).size() == 0){
            throw new UserNotFoundException(userId);
        }
        ProjectDetailResponse projectDetailResponse = new ProjectDetailResponse(
                project.getProjectName(), project.getTeamName(), project.getSubject().getName(),
                project.getSubject().getProfessor().getName(),
                getProgressRate(project), getParticipateInfo(project), getDdayInfo(project)
        );
        return projectDetailResponse;
    }

    public Double getProgressRate(Project project){
        Set<Task> tasks = project.getTaskSet();
        Long totalTaskCount = Long.valueOf(tasks.size());
        Long doneTaskCount = 0L;
        for(Task task :tasks){
            for(TaskTaskTag tasktag : task.getTaskTaskTagSet()){
                if(tasktag.getIdx() == Constants.STATE_COMPLETE) doneTaskCount++;
            }
        }
        return new ProgressCalaulator(totalTaskCount, doneTaskCount).getProgressRate();
    }

    public List<ProjectParticipateDto> getParticipateInfo(Project project){
        Set<ProjectUser> members = project.getProjectUserSet();
        Set<Task> tasks = project.getTaskSet();
        tasks = tasks.stream().filter(task -> task.getState() == Constants.STATE_COMPLETE).collect(Collectors.toSet());
        Long totalDoneTask = 0L;
        for(Task task: tasks){
            totalDoneTask += task.getUserTaskSet().size();
        }
        List<ProjectParticipateDto> dtos = new ArrayList<>();
        for(ProjectUser user:members){
            Long memberDoneTask = (long) tasks.stream()
                    .filter(task -> task.getUserTaskSet().contains(new UserTask(user.getUser(), task)))
                    .collect(Collectors.toSet()).size();
            dtos.add(ProjectParticipateDto.of(user,
                    new ParticipateCalculator(totalDoneTask, memberDoneTask).getParticipateRate()));
        }
        return dtos;
    }

    public List<DdayDto> getDdayInfo(Project project){
        Set<Task> tasks = project.getTaskSet();
        List<DdayDto> dtos = new ArrayList<>();
        for(Task task: tasks){
            dtos.add(DdayDto.of(task));
        }
        return dtos;
    }

    public ProjectMemberResponse readProjectMember(Long projectId, Long userId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        if(project.getProjectUserSet().stream()
                .filter(projectUser -> projectUser.getUser().getIdx() == userId)
                .collect(Collectors.toSet()).size() == 0){
            throw new UserNotFoundException(userId);
        }
        List<ProjectMemberDto> dtos = new ArrayList<>();
        for(ProjectUser member: project.getProjectUserSet()){
            dtos.add(ProjectMemberDto.of(member.getUser()));
        }
        return new ProjectMemberResponse(dtos);
    }
}
