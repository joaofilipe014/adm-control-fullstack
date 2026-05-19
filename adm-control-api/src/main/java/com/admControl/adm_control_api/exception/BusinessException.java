package com.admControl.adm_control_api.exception;

public class BusinessException extends RuntimeException{

    public BusinessException (String message){
        super(message);
    }
}