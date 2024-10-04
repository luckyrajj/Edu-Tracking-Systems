package com.jsp.rest.ets.security;

import com.jsp.rest.ets.user.Privilege;
import com.jsp.rest.ets.user.UserRole;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jsp.rest.ets.security.JWTService;
import java.io.IOException;
import java.util.List;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    public JWTFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String  token=  request.getHeader("authorization");
      if(token!=null){
          token=token.substring(7);
          if(token!=""){
                Claims claims=jwtService.parseJwt(token);
                String email=claims.get("email",String.class);
                String role=claims.get("role",String.class);

                if(email!=null && role!=null){
                 UserRole  userRole= UserRole.valueOf(role);

                 List<SimpleGrantedAuthority> authorities= userRole.getPrivileges().stream()
                          .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
                          .toList();

                 UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(email,"null",authorities);
                 authenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
          }
      }
      filterChain.doFilter(request,response);
    }
}
