package com.ssoggong.stonemanager_server.dto.task;

import com.ssoggong.stonemanager_server.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadTaskDetail_fileDto {
    private Long fileIndex;
    private String fileName;

    public static ReadTaskDetail_fileDto of(File file) {
        return new ReadTaskDetail_fileDto(file.getIdx(), file.getName());
    }
}
