package com.hust.model;

import com.hust.address.entity.Commune;

import java.util.Date;
import java.util.List;

public class VolunteerOutPutModel{
    private String fullName;
    private String phoneNum;
    private Date birthDate;
    private String gender;
    private Commune commune;
    private long numAttend;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public long getNumAttend() {
        return numAttend;
    }

    public void setNumAttend(long numAttend) {
        this.numAttend = numAttend;
    }
}
