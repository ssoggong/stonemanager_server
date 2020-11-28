package com.ssoggong.stonemanager_server.exception;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(Long commentId) {
        super("comment : " + commentId.toString());
    }
}
