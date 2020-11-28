package com.ssoggong.stonemanager_server.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssoggong.stonemanager_server.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadTaskList_taskDto {
    private Long taskIndex;
    private String taskName;
    private List<ReadTaskList_task_assigneeDto> assigneeInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime taskDate;
    private List<ReadTaskList_task_tagDto> tagInfo;
    private Integer taskState;
    private Integer taskFileNumber;
    private Integer taskToDoChecklistNumber;
    private Integer taskCompleteChecklistNumber;
    private Integer taskCommentNumber;

    public static ReadTaskList_taskDto of(Task task) {

        List<ReadTaskList_task_assigneeDto> assigneeDtoList = new ArrayList<>();
        List<ReadTaskList_task_tagDto> tagDtoList = new ArrayList<>();

        for(UserTask userTask: task.getUserTaskSet()){
            User user = userTask.getUser();
            ReadTaskList_task_assigneeDto userDto = ReadTaskList_task_assigneeDto.of(user);
            assigneeDtoList.add(userDto);
        }

        for(TaskTaskTag taskTaskTag: task.getTaskTaskTagSet()) {
            TaskTag taskTag = taskTaskTag.getTaskTag();
            ReadTaskList_task_tagDto tagDto = ReadTaskList_task_tagDto.of(taskTag);
            tagDtoList.add(tagDto);
        }

        Integer ToDoNumber = 0;
        Integer CompleteNumber = 0;

        for(Checklist checklist: task.getChecklistSet()) {
            if(checklist.getState()){
                CompleteNumber ++;
            }
            else {
                ToDoNumber ++;
            }
        }

        return new ReadTaskList_taskDto(task.getIdx(), task.getName(), assigneeDtoList, task.getDeadline(), tagDtoList, task.getState(), task.getFileSet().size(), ToDoNumber, CompleteNumber, task.getCommentSet().size());
    }
}
