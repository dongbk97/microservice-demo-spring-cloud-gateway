package com.example.gatewayservice.exceptionhandle;



import io.jsonwebtoken.JwtException;

;

public class JwtTokenMalformedException extends JwtException {


    private static final long serialVersionUID = 1L;

    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
