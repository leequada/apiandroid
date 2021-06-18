package com.example.demoapi.model;

public class LoginFacebookDto {
    private String idFacebook;
    private String accessToken;

    public LoginFacebookDto(String idFacebook, String accessToken) {
        this.idFacebook = idFacebook;
        this.accessToken = accessToken;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
