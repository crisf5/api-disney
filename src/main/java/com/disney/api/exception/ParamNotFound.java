package com.disney.api.exception;

public class ParamNotFound extends RuntimeException{

    public ParamNotFound(String nameEntity, String param) {
        super ("Error, " + nameEntity  + " " + param + " is not found.");
    }
}
