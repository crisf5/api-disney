package com.disney.api.exception;

public class ParamNotFound extends RuntimeException{

    public ParamNotFound(String param) {
        super ("Error, " + param + " is not found.");
    }
}
