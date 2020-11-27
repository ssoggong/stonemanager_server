package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.project.DdayDto;
import com.ssoggong.stonemanager_server.dto.project.ProjectDetailResponse;
import com.ssoggong.stonemanager_server.dto.project.ProjectParticipateDto;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.exception.ProjectNotFoundException;
import com.ssoggong.stonemanager_server.repository.ProjectRepository;
import com.ssoggong.stonemanager_server.util.Constants;
import com.ssoggong.stonemanager_server.util.ParticipateCalculator;
import com.ssoggong.stonemanager_server.util.ProgressCalaulator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public void saveProject(Project project) { projectRepository.save(project); }


    public ProjectDetailResponse getProjectDetail(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
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

    // TODO : 3중포문..filter로 줄여보거나..다른 알고리즘 생각해보
    public List<ProjectParticipateDto> getParticipateInfo(Project project){
        Set<ProjectUser> members = project.getProjectUserSet();
        Set<Task> tasks = project.getTaskSet();
        boolean isComplete;
        for(Task task: tasks){
            isComplete = false;
            for(TaskTaskTag taskTaskTag: task.getTaskTaskTagSet()){
                if(taskTaskTag.getTaskTag().getIdx() == Constants.STATE_COMPLETE) isComplete = true;
            }
            if(!isComplete) tasks.remove(task);
        }
        Long totalDoneTask = 0L;
        for(Task task: tasks){
            totalDoneTask += task.getUserTaskSet().size();
        }
        List<ProjectParticipateDto> dtos = new ArrayList<>();
        Long memberDoneTask;
        for(ProjectUser user : members){
            memberDoneTask = 0L;
            for(Task task: tasks){
                for(UserTask userTask: task.getUserTaskSet()){
                    if(userTask.getUser().getIdx() == user.getUser().getIdx()) memberDoneTask++;
                }
            }
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
}
