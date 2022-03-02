package com.disney.api.exception;

public class UserExist extends RuntimeException{

    public UserExist(){super("Error, username already registered");}
}
