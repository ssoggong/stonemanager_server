package com.ssoggong.stonemanager_server.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssoggong.stonemanager_server.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadTaskDetail_commentDto {
    private Long commentIndex;
    private ReadTaskDetail_comment_authorDto commentAuthorInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime commentCreatedTime;
    private String commentContent;

    public static ReadTaskDetail_commentDto of(Comment comment) {
        ReadTaskDetail_comment_authorDto authorDto;
        authorDto = ReadTaskDetail_comment_authorDto.of(comment.getUser());
        return new ReadTaskDetail_commentDto(comment.getIdx(), authorDto,comment.getCreatedDate(), comment.getContent());
    }
}
