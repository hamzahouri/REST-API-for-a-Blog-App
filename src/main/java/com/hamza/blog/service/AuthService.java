package com.hamza.blog.service;

import com.hamza.blog.payload.LoginDto;
import com.hamza.blog.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);
    String register (RegisterDto registerDto);
}
