package com.example.restfulapivehicle.validations;

import jakarta.validation.constraints.NotNull;

public class LoginValidation {
	
    @NotNull
	private String email;

	@NotNull
	private String password;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
