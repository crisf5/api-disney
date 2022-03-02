package com.disney.api.exception;

public class IncorrectLogin extends RuntimeException{

    public IncorrectLogin(){super("Error, incorrect username or password");}
}
