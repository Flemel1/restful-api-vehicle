package com.example.restfulapivehicle.responses;

import java.util.List;

public class UserInfoResponse {
    private Integer id;
    private String email;
    private List<String> roles;

    public UserInfoResponse(Integer id, String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
