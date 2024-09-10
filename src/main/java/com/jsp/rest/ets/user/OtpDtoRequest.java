package com.jsp.rest.ets.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpDtoRequest {

    private String email;
    private int otp;
}
