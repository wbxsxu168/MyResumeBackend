package com.sunx.mysprmdbapp1.services;
import com.sunx.mysprmdbapp1.reqdto.signinAccountDto;
import com.sunx.mysprmdbapp1.reqdto.signupAccountDto;

public interface JwtAuthService {
    String login(signinAccountDto loginDto);
    String register(signupAccountDto registerDto);
}