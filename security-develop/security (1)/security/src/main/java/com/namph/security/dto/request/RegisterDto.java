package com.namph.security.dto.request;


import com.namph.security.common.Constant;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

public class RegisterDto {
    @NotBlank(message = Constant.MESSAGE_NOT_BLANK)
    private String name;
    @NotBlank(message = Constant.MESSAGE_NOT_BLANK)
    private String username;
    @NotBlank(message = Constant.MESSAGE_NOT_BLANK)
    private String password;
    private Date dob;
    private String address;
    private String phone;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
