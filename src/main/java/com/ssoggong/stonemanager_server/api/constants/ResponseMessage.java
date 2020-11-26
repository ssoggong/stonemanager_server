package com.ssoggong.stonemanager_server.api.constants;


public class ResponseMessage {

    // 성공
    public static final String CREATE_USER = "회원가입 성공";
    public static final String UPDATE_USER = "유저 정보 변경 성공";
    public static final String LOGIN = "로그인 성공";
    public static final String UPDATE_PASSWORD = "비밀번호 변경 성공";

    public static final String CREATE_PROJECT_USER = "팀원 초대 성공";
    public static final String READ_PROJECT_USER = "팀원 조회 성공";
    public static final String DELETE_PROJECT_USER = "프로젝트 탈퇴 성공"; // 프로젝트 탈퇴

    public static final String READ_PROJECT_LIST = "프로젝트 목록 조회 성공";
    public static final String CREATE_PROJECT = "프로젝트 생성 성공";
    public static final String READ_PROJECT = "프로젝트 상세 조회 성공";
    public static final String UPDATE_PROJECT = "프로젝트 변겅 성공";

    public static final String READ_SCHEDULE_LIST = "일정 목록 조회 성공";
    public static final String CREATE_SCHEDULE = "일정 생성 성공";
    public static final String READ_SCHEDULE = "일정 조회 성공";
    public static final String UPDATE_SCHEDULE = "일정 변경 성공";
    public static final String DELETE_SCHEDULE = "일정 삭제 성공";

    public static final String READ_SCHEDULE_TAG_LIST = "일정 태그 목록 조회 성공";
    public static final String READ_SCHEDULE_TAG = "일정 태그 조회 성공";
    public static final String UPDATE_SCHEDULE_TAG = "일정 태그 변겅 성공";
    public static final String DELETE_SCHEDULE_TAG = "일정 태그 삭제 성공";

    public static final String READ_TASK_LIST = "할 일 목록 조회 성공";
    public static final String CREATE_TASK = "할 일 생성 성공";
    public static final String READ_TASK = "할 일 상세 조회 성공";
    public static final String UPDATE_TASK = "할 일 변경 성공";
    public static final String DELETE_TASK = "할 일 삭제 성공";

    public static final String CREATE_CHECKLIST  = "체크리스트 생성 성공";
    public static final String UPDATE_CHECKLIST = "체크리스트 변경 성공";
    public static final String DELETE_CHECKLIST = "체크리스트 삭제 성공";

    public static final String CREATE_COMMENT = "코멘트 생성 성공";
    public static final String DELETE_COMMENT = "코멘트 삭제 성공";
    public static final String UPDATE_COMMENT = "코멘트 변경 성공";

    public static final String CREATE_FILE = "파일 생성 성공";
    public static final String UPDATE_FILE = "파일 변경 성공";
    public static final String DELETE_FILE = "파일 삭제 성공";

    public static final String CREATE_TASK_TAG = "할 일 태그 생성 성공";
    public static final String UPDATE_TASK_TAG = "할 일 태그 변경 성공";
    public static final String DELETE_TASK_TAG = "할 일 태그 삭제 성공";

    // 실패
    public static final String NOT_FOUND_USER = "Invalid userIndex";
    public static final String NOT_FOUND_PROJECT = "Invalid projectIndex";
    public static final String NOT_FOUND_SCHEDULE = "Invalid scheduleIndex";
    public static final String NOT_FOUND_TASK = "Invalid taskIndex";

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
}
