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
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadTaskDetailDto {

    private String taskName;
    private List<ReadTaskDetail_assigneeDto> assigneeInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime taskDate;
    private List<ReadTaskDetail_tagDto> tagInfo;
    private String taskDescription;
    private List<ReadTaskDetail_checklistDto> checklistInfo;
    private List<ReadTaskDetail_fileDto> fileInfo;
    private List<ReadTaskDetail_commentDto> commentInfo;

    public static ReadTaskDetailDto of(Task task) {

        List<ReadTaskDetail_assigneeDto> assigneeDtoList = new ArrayList<>();
        List<ReadTaskDetail_tagDto> tagDtoList = new ArrayList<>();
        List<ReadTaskDetail_checklistDto> checklistDtoList = new ArrayList<>();
        List<ReadTaskDetail_fileDto> fileDtoList = new ArrayList<>();
        List<ReadTaskDetail_commentDto> commentDtoList = new ArrayList<>();

        Set<UserTask> UserTaskSet = task.getUserTaskSet();
        for(UserTask userTask: UserTaskSet) {
            User assignee = userTask.getUser();
            ReadTaskDetail_assigneeDto assigneeDto = ReadTaskDetail_assigneeDto.of(assignee);
            assigneeDtoList.add(assigneeDto);
        }

        Set<TaskTaskTag> taskTaskTagSet = task.getTaskTaskTagSet();
        for(TaskTaskTag taskTaskTag: taskTaskTagSet) {
            TaskTag taskTag = taskTaskTag.getTaskTag();
            ReadTaskDetail_tagDto tagDto = ReadTaskDetail_tagDto.of(taskTag);
            tagDtoList.add(tagDto);
        }

        Set<Checklist> checklistSet = task.getChecklistSet();
        for(Checklist checklist: checklistSet) {
            ReadTaskDetail_checklistDto checklistDto = ReadTaskDetail_checklistDto.of(checklist);
            checklistDtoList.add(checklistDto);
        }

        Set<File> fileSet = task.getFileSet();
        for(File file: fileSet) {
            ReadTaskDetail_fileDto fileDto = ReadTaskDetail_fileDto.of(file);
            fileDtoList.add(fileDto);
        }

        Set<Comment> commentSet = task.getCommentSet();
        for(Comment comment: commentSet) {
            ReadTaskDetail_commentDto commentDto = ReadTaskDetail_commentDto.of(comment);
            commentDtoList.add(commentDto);
        }

        return new ReadTaskDetailDto(task.getName(), assigneeDtoList, task.getDeadline(), tagDtoList,
                task.getDescription(), checklistDtoList, fileDtoList, commentDtoList);
    }
}
