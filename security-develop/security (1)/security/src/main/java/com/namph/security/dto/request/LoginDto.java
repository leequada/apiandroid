package com.namph.security.dto.request;

import com.namph.security.common.Constant;

import javax.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank(message = Constant.MESSAGE_NOT_BLANK)
    private String username;
    @NotBlank(message = Constant.MESSAGE_NOT_BLANK)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
