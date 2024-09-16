package com.jsp.rest.ets.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService {


    @Value("${my_app.jwt.secret}")
    private String secret;

}
