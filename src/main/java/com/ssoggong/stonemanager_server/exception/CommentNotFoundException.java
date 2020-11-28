package com.ssoggong.stonemanager_server.exception;

public class CommentNotFoundException extends  RuntimeException {
    public CommentNotFoundException() { super("Invalid CommentId Exception"); }
}
