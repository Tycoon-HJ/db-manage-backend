package org.jeecg.common.exception;

/**
 * 仅在数据字典上写错误时，进行异常抛出处理
 * @author mr.ahai
 */
public class DictException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DictException(String message){
        super(message);
    }
}
