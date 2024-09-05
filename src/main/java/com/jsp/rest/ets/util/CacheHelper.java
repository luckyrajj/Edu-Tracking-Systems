package com.jsp.rest.ets.util;

import com.jsp.rest.ets.user.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;


public class CacheHelper {

    @CachePut(cacheNames = "non-verified-user",key ="#user.email" )
    public User userCache(User user){
        return user;
    }

    @Cacheable(cacheNames = "otps",key = "#otp")
    public int otpCache(int otp){
        return otp;
    }
}
