package com.namph.security.service;


import com.namph.security.domain.Loan;
import com.namph.security.domain.User;
import com.namph.security.dto.request.LoginDto;
import com.namph.security.dto.request.RegisterDto;


import java.util.Set;

public interface UserService {
    User register(RegisterDto registerDto);
    User signIn(LoginDto loginDto);
    Set<Loan> getLoan(String name);


}
