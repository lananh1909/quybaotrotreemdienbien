package com.hust.model;

import com.hust.entity.RoleEntity;
import com.hust.entity.VolunteerEntity;

import java.util.Date;

public class UserOutputModel extends BaseModel{
    private String username;
    private String email;
    private RoleEntity role;
    private VolunteerOutPutModel volunteer;

    public UserOutputModel(long id, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
        super(id, createdBy, createdDate, modifiedBy, modifiedDate);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public VolunteerOutPutModel getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(VolunteerOutPutModel volunteer) {
        this.volunteer = volunteer;
    }
}
