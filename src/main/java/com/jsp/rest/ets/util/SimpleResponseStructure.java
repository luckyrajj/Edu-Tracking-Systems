package com.jsp.rest.ets.util;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponseStructure {

    private int statusCode;
    private String message;

    public static SimpleResponseStructure create(int statusCode,String message){
        SimpleResponseStructure simpleResponseStructure=new SimpleResponseStructure();
        simpleResponseStructure.setStatusCode(statusCode);
        simpleResponseStructure.setMessage(message);
        return simpleResponseStructure;
    }

}
