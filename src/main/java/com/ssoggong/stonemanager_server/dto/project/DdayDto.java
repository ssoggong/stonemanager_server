package com.ssoggong.stonemanager_server.dto.project;

import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.entity.TaskTaskTag;
import com.ssoggong.stonemanager_server.util.DDayCalculator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DdayDto {
    private String taskName;
    private Long dday;
    private List<String> tagNames;

    public static DdayDto of(Task task){
        List<String> tags = new ArrayList<>();
        for(TaskTaskTag tag: task.getTaskTaskTagSet()){
            tags.add(tag.getTaskTag().getName());
        }
        return new DdayDto(task.getName(), new DDayCalculator(task.getDeadline()).getDDay(), tags);
    }
}
