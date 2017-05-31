package com.hongshen.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by a7289 on 2017/3/28 0028.
 */
@Slf4j
public class SranException extends Exception {
    public SranException(String msg){
        super(msg);
        log.error(msg);
    }
    public SranException(String msg,String errorParm){
        super(msg+errorParm);
        log.error(msg);
    }
    public SranException(String msg,Throwable cause){
        super(msg,cause);
        log.error(msg,cause);
    }
    public SranException(Throwable cause){
        super(cause.getMessage());
        log.error(cause.toString());
    }
}
