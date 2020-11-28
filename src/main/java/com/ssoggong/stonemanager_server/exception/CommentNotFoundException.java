package com.ssoggong.stonemanager_server.exception;

public class CommentNotFoundException extends  RuntimeException {
    public CommentNotFoundException(Long commentId){
        super("Invalid commentId Exception : " + commentId);
        System.out.println(getMessage());
    }
}
