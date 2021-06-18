package com.namph.security.controller;

import com.namph.security.domain.Loan;
import com.namph.security.domain.MonthlyLoan;
import com.namph.security.domain.User;
import com.namph.security.dto.ResponseData;
import com.namph.security.dto.request.LoginDto;
import com.namph.security.dto.request.LoginFacebookDto;
import com.namph.security.dto.request.RegisterDto;

import com.namph.security.repository.UserRepository;
import com.namph.security.securityconfig.OAuth2.RestFB;
import com.namph.security.securityconfig.jwt.JwtTokenProvider;
import com.namph.security.service.MonthlyLoanService;
import com.namph.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class AuthenticationController {
  

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestFB restFB;

    @Autowired
    MonthlyLoanService monthlyLoanService;

    @PostMapping("auth/register")
    public ResponseEntity<ResponseData> registerUser(@Valid @RequestBody RegisterDto registerDto){
        User result = userService.register(registerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(ResponseData.ofSuccess("User create sucess",result));
    }

    @PostMapping("auth/login")
    public ResponseEntity<ResponseData> login(@Valid @RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(ResponseData.ofSuccess("Login sucess",jwt));
    }

    @PostMapping("auth/login-facebook")
    public ResponseEntity<ResponseData> loginFacebook( LoginFacebookDto loginFacebookDto) throws IOException {

//        com.restfb.types.User user = restFB.getUserInfo(loginFacebookDto.getAccessToken());
//        String username= restFB.getUserName(loginFacebookDto.getAccessToken());
        UserDetails userDetail = restFB.buildUser("Philipp Nam");
        Authentication auth = new AnonymousAuthenticationToken(null,userDetail,userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = tokenProvider.createToken(auth);
        return ResponseEntity.ok(ResponseData.ofSuccess("login facebook success",jwt));
    }
    @GetMapping("getUser")
    public User getUserById(@RequestParam String username ){
        return userRepository.findUserByUsername(username);
    }

    @GetMapping("user/getUser-loan")
    public Set<Loan> getUserLoan(@RequestParam String username ){
        return userService.getLoan(username);
    }
    @GetMapping("user/getMonthlyLoan")
    public Set<MonthlyLoan> getMonthlyLoan(@PathVariable Integer id ){
        return monthlyLoanService.getMonthlyLoan(id);
    }
    @PostMapping ("user/donglai")
    public MonthlyLoan donglai(@PathVariable Integer id ){
        return monthlyLoanService.dongLai(id);
    }


}
