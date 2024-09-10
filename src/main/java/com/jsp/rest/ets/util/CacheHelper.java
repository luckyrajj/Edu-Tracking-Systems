package com.jsp.rest.ets.util;


import com.jsp.rest.ets.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheHelper {

    @CachePut(cacheNames = "non-verified-user",key ="#user.email" )
    public User userCache(User user){
        return user;
    }

    @CachePut(cacheNames = "otps",key = "#email")
    public Integer otpCache(int otp,String email){
        log.info("caching otp");
        return otp;
    }

    @Cacheable(cacheNames = "non-verified-user",key = "#email")
    public User getRegisteringUser(String email){

       return new User();
    }

    @Cacheable(cacheNames ="otps",key = "#email")
    public Integer getOtpToVerify(String email){
        log.info("retreiving otp");
        return null ;
    }
}
