package com.example.demoapi.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class LoginDto implements Serializable {
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SerializedName("username")
@Expose
private String username;
@SerializedName("password")
@Expose
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