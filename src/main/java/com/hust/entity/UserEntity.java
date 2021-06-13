package com.hust.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user_account\"")
public class UserEntity extends BaseEntity{
    @Column
    private String username;

    @Column
    private String password;

    @Column
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private VolunteerEntity volunteer;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BlogEntity> blogs = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ActivityEntity> activities = new ArrayList<>();

    public List<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }

    public List<BlogEntity> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogEntity> blogs) {
        this.blogs = blogs;
    }

    public VolunteerEntity getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(VolunteerEntity volunteer) {
        this.volunteer = volunteer;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
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
