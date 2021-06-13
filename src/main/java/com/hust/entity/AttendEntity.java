package com.hust.entity;

import com.hust.entity.id.AttendId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"attend\"")
@IdClass(AttendId.class)
@EntityListeners(AuditingEntityListener.class)
public class AttendEntity{
    @Id
    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private VolunteerEntity volunteer;

    @Id
    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ActivityEntity activity;

    @Column
    private String skill;

    @Column
    @CreatedDate
    private Date attendTime;

    @Column(columnDefinition = "integer default 0")
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }

    public VolunteerEntity getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(VolunteerEntity volunteer) {
        this.volunteer = volunteer;
    }

    public ActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(ActivityEntity activity) {
        this.activity = activity;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
