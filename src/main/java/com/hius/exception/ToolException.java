package com.hius.exception;


import com.hius.utils.StringUtil;

public class ToolException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ToolException(String msg){
        super(msg);
    }

    public ToolException(Throwable e){
        super(e.getMessage(), e);
    }

    public ToolException(String template, Object params){
        super(StringUtil.format(template, params));
    }
}
