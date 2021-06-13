package com.hust.model;

import com.hust.entity.RoleEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class AddUserInputModel {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @Email
    private String email;
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long role_id) {
        this.roleId = role_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
