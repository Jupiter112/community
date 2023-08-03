package com.example.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001, "你查找的问题不存在."),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复."),
    NO_LOGIN(2003, "未登录，请登录后重试."),
    SYS_ERROR(2004, "服务器冒烟了，请稍后尝试"),
    TYPE_PARAM_WRONG(2005, "评论或回复类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "你查找的评论不存在");





    @Override
    public String getMessage(){return message;}

    @Override
    public Integer getCode(){return code;}
    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
