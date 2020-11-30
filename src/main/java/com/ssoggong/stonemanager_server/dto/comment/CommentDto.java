package com.ssoggong.stonemanager_server.dto.comment;

import com.ssoggong.stonemanager_server.entity.Comment;
import com.ssoggong.stonemanager_server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long commentId;
    private String userName;
    private String content;
    private String createdDate;
    private String userImage;

    public static CommentDto of(Comment comment, User user){
        return new CommentDto(comment.getIdx(), user.getName(), comment.getContent(),
                comment.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")),
                user.getImage());
    }
}
