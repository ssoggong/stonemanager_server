package com.ssoggong.stonemanager_server.util;

public class Constants {
    // task state tag index
    public static final int STATE_TODO = 1;
    public static final int STATE_PROGRESS = 2;
    public static final int STATE_COMPLETE = 3;
    public static final int STATE_PENDING = 4;

    public static final String FROM_ADDRESS = "gounee@gmail.com";
    public static final String MAIL_TITLE = "STONE MANAGER 회원가입 메일 인증 코드입니다.";
    public static final String MAIL_CONTENT = "인증 코드는 아래와 같습니다.\n인증 코드 : ";
    public static final String MAIL_PW_TITLE = "STONE MANAGER 임시 비밀번호 발급 메일입니다.";
    public static final String MAIL_PW_CONTENT = "임시 비밀번호는 아래와 같습니다.\n임시 비밀번호 : ";

    public static final String MAIL_INVITE_TITLE = "STONE MANAGER 팀원 초대 메일입니다.";
    public static final String MAIL_INVITE_CONTENT = "팀에 초대되셨습니다. 수락하시려면 아래 링크를 클릭하세요.\n";
}
