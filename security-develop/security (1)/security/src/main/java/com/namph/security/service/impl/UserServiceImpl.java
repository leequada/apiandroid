package com.namph.security.service.impl;

import com.namph.security.common.Constant;
import com.namph.security.domain.Loan;
import com.namph.security.domain.User;
import com.namph.security.dto.request.LoginDto;
import com.namph.security.dto.request.RegisterDto;
import com.namph.security.exception.CustomGlobalException;
import com.namph.security.repository.UserRepository;
import com.namph.security.service.UserService;
import com.namph.security.service.mapper.LoginMapper;
import com.namph.security.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    UserMapper userMapper;

    LoginMapper loginMapper;


    @Override
    public User register(RegisterDto registerDto) {
        User user= userMapper.toEntity(registerDto);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(registerDto.getPassword()));
        user.setRole(Constant.ROLE.USER);
        if(userRepository.findUserByUsername(user.getUsername()) != null){
            throw  new CustomGlobalException("Username is exsist");
        }
        return userRepository.save(user);
    }

    @Override
    public User signIn(LoginDto loginDto) {
        User user = loginMapper.toEntity(loginDto);

        return user;
    }

    @Override
    public Set<Loan> getLoan(String name) {
        User user = userRepository.findUserByName(name);
        if(user == null){
            throw new CustomGlobalException("User Not Found");
        }
        return user.getLoans();
    }
}
